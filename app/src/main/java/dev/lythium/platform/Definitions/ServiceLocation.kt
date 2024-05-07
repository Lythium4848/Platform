package dev.lythium.platform.Definitions

import kotlinx.serialization.Serializable

@Serializable
class ServiceLocation(
    val locationName: String,
    val crs: String,
    val via: String? = null,
    val futureChangeTo: String? = null,
    val assocIsCancelled: Boolean?,
)