package dev.lythium.platform.Definitions

import kotlinx.serialization.Serializable

@Serializable
data class StationBoardWithDetails(
    val generatedAt: String,
    val locationName: String,
    val crs: String,
    val filterLocationName: String? = null,
    val filtercrs: String? = null,
    val filterType: String,
    val nrccMessages: List<NrccMessage>? = null,
    val platformAvailable: Boolean? = null,
    val areServicesAvailable: Boolean? = null,
    val trainServices: List<ServiceItemWithCallingPoints>? = null,
    val busServices: List<ServiceItemWithCallingPoints>? = null,
    val ferryServices: List<ServiceItemWithCallingPoints>? = null
) : java.io.Serializable

