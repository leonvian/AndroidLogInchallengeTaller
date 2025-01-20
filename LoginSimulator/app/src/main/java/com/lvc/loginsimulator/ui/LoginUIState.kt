package com.lvc.loginsimulator.ui

sealed class LoginUIState {

    class Loading() : LoginUIState()

    class Error(exception: Exception) : LoginUIState()

    class Success(val authenticated: Boolean) : LoginUIState()

}