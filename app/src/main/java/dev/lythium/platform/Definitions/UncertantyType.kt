package dev.lythium.platform.Definitions

import kotlinx.serialization.Serializable

enum class UncertantyType {
    DELAY,
    CANCELLATION,
    OTHER
}

@Serializable
class UncertaintyType(
    val status: UncertantyType,
    val reason: String,
) {}