package com.lvc.loginsimulator.model

data class AuthenticationData(val login: String, val password: String) {

    fun isValid(): Boolean = login.isNotEmpty() && password.isNotEmpty()

}
