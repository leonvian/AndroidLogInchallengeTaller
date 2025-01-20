package com.lvc.loginsimulator.repository.remote

import com.lvc.loginsimulator.model.AuthenticationData
import kotlinx.coroutines.delay

class AuthenticationAPI {

    suspend fun sigIn(authenticationData: AuthenticationData) : LoginResponseData {
        delay(1000)
        return if (authenticationData.isValid()) {
            LoginResponseData(true)
        }  else {
            LoginResponseData(false)
        }
    }

}