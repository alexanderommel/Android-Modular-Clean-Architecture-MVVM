package com.alexandersw.testing

import com.alexandersw.api.user.User
import com.alexandersw.api.user.UserApiInteractor
import com.alexandersw.common.Location

class UserApiInteractorFaker: UserApiInteractor {
    override suspend fun getUserPreferredDeliveryLocation(): Location? {
        return Location("CCI Quito","","")
    }

    override suspend fun isAuthenticated(): Boolean {
        return true
    }

    override suspend fun getAuthenticatedUser(): User {
        return test_data_user1
    }
}