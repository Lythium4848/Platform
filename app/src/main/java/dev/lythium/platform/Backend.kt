package dev.lythium.platform

import dev.lythium.platform.Definitions.StationBoardWithDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

@Serializable
data class Station(
    val crs: String,
    val name: String
)

@Serializable
data class StationResponse(
    val stations: List<Station>
)

@Serializable
data class DepartureToResponse(
    val type: String,
    val data: StationBoardWithDetails
)


object Backend {
    fun getStations(): List<Station> {
        return runBlocking {
            val client = HttpClient(Android)

            val response: HttpResponse = client.get("https://platform-backend.lythium.dev/stations")
            if (response.status.value !in 200 .. 299) {
                return@runBlocking emptyList<Station>()
            }

            val stringBody: String = response.body()

            try {
                val json = Json { ignoreUnknownKeys = true }
                val stations = json.decodeFromString<List<Station>>(stringBody)
                return@runBlocking stations
            } catch (e: Exception) {
                println("Error Decoding json")
                println(e)
                return@runBlocking emptyList<Station>()
            }
        }
    }

    fun getTrainsTo(fromCRS: String, toCRS: String): StationBoardWithDetails? {
        return runBlocking {
            val client = HttpClient(Android)

            val response: HttpResponse =
                client.get("https://platform-backend.lythium.dev/departure/$fromCRS/to/$toCRS?timeOffset=119&timeWindow=120")
            if (response.status.value !in 200 .. 299) {
                return@runBlocking null
            }

            val stringBody: String = response.body()

            val json = Json { ignoreUnknownKeys = true }
            val jsonElement = json.parseToJsonElement(stringBody)

            if (jsonElement is JsonObject && (jsonElement.containsKey("fault") || jsonElement.containsKey(
                    "Message"
                ))
            ) {
                return@runBlocking null
            } else {
                val jsonFormatted = json.decodeFromString<DepartureToResponse>(stringBody)
                return@runBlocking jsonFormatted.data
            }
        }
    }
}
