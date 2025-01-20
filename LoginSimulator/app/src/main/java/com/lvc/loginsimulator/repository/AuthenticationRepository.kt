package com.lvc.loginsimulator.repository

import com.lvc.loginsimulator.model.AuthenticationData
import com.lvc.loginsimulator.repository.remote.AuthenticationAPI
import com.lvc.loginsimulator.repository.remote.LoginResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepository(private val authenticationAPI: AuthenticationAPI) {

    suspend fun signIn(authenticationData: AuthenticationData)  : LoginResponseData {
        return withContext(Dispatchers.IO) { authenticationAPI.sigIn(authenticationData) }
    }

}