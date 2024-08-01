package com.alexandersw.common

sealed class BusinessRuleFailure {
    object ServiceUnavailable : BusinessRuleFailure()
    object NotAuthorized : BusinessRuleFailure()
    object AccountRestrictedPermissions : BusinessRuleFailure()
    object ServerInMaintenance : BusinessRuleFailure()
    object NoInternetConnection : BusinessRuleFailure()
    object LocationNotFound : BusinessRuleFailure()
    data class InputMissing(val missingField: String) : BusinessRuleFailure()
    object RemoteDataChanged : BusinessRuleFailure()

    fun handleBusinessRuleFailure(): String {
        return when (this) {
            is ServiceUnavailable -> "http.service_unavailable"
            is NotAuthorized -> "http.not_authorized"
            is AccountRestrictedPermissions -> "http.restricted"
            is ServerInMaintenance -> "http.server_down"
            is NoInternetConnection -> "network.no_internet"
            is InputMissing -> "missing.${this.missingField}"
            is RemoteDataChanged -> "network.data_changed"
            is LocationNotFound -> "location.not_found"
        }
    }
}
