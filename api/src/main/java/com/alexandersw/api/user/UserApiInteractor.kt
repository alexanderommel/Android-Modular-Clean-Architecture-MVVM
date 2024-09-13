package com.alexandersw.api.user

import com.alexandersw.common.Location

interface UserApiInteractor {
    suspend fun getUserPreferredDeliveryLocation(): Location?
    suspend fun isAuthenticated(): Boolean
    suspend fun getAuthenticatedUser(): User
}
