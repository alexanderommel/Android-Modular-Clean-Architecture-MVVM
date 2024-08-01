package com.alexandersw.common

sealed class UseCaseResponse<out T: Any> {
    data class Error(val failure: BusinessRuleFailure) : UseCaseResponse<Nothing>()
    data class Success<T: Any>(val data: T) : UseCaseResponse<T>()
}
