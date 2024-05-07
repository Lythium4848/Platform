package dev.lythium.platform.Definitions

import kotlinx.serialization.Serializable

@Serializable
class ServiceItemWithCallingPointsPreviousCallingPoints(
    val callingPoint: List<CallingPoint>? = null,
    val serviceType: String,
    val serviceChangeRequired: Boolean,
    val assocIsCancelled: Boolean,
)

@Serializable
class ServiceItemWithCallingPointsSubsequentCallingPoints(
    val callingPoint: List<CallingPoint>? = null,
    val serviceType: String,
    val serviceChangeRequired: Boolean,
    val assocIsCancelled: Boolean,
)

@Serializable
class ServiceItemWithCallingPoints(
    val origin: List<ServiceLocation>,
    val destination: List<ServiceLocation>,
    val currentOrigins: List<ServiceLocation>? = null,
    val currentDestinations: List<ServiceLocation>? = null,
    val sta: String? = null,
    val eta: String? = null,
    val std: String? = null,
    val etd: String? = null,
    val platform: String? = null,
    val operator: String,
    val operatorCode: String,
    val isCircularRoute: Boolean,
    val isCancelled: Boolean,
    val filterLocationCancelled: Boolean?,
    val serviceType: String,
    val length: Int? = 0,
    val detachFront: Boolean? = null,
    val isReverseFormation: Boolean? = null,
    val cancelReason: String? = null,
    val delayReason: String? = null,
    val serviceID: String,
    val adhocAlerts: List<String>? = null,
    val previousCallingPoints: List<ServiceItemWithCallingPointsPreviousCallingPoints>? = null,
    val subsequentCallingPoints: List<ServiceItemWithCallingPointsSubsequentCallingPoints>? = null,
    val uncertantyType: UncertaintyType? = null,
    val affectedBy: List<String>? = null,
) {}