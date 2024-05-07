package dev.lythium.platform.Definitions

import kotlinx.serialization.Serializable

@Serializable
class CallingPoint(
    val locationName: String,
    val crs: String,
    val st: String,
    val et: String? = null,
    val at: String? = null,
    val isCancelled: Boolean,
    val length: Int? = 0,
    val detachFront: Boolean = false,
    val adhocAlerts: List<String>? = null,
    val uncertanty: UncertantyType? = null,
    val affectedBy: String? = null,

    )