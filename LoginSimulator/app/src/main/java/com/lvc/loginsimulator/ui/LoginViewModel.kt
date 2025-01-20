package com.lvc.loginsimulator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvc.loginsimulator.model.AuthenticationData
import com.lvc.loginsimulator.repository.AuthenticationRepository
import com.lvc.loginsimulator.repository.remote.AuthenticationAPI
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // @TODO Change for Dependency Injection
    private val authenticationRepository = AuthenticationRepository(AuthenticationAPI())
    private val _loginStateLiveData = MutableLiveData<LoginUIState>()
    val loginStateLiveData: LiveData<LoginUIState>  = _loginStateLiveData

    fun signIn(login: String, password: String) {
        viewModelScope.launch {
            _loginStateLiveData.postValue(LoginUIState.Loading())

            // @TODO Improve this to not use try catch and actually used a more reusable solution.
            try {
                val response = authenticationRepository.signIn(AuthenticationData(login, password))
                _loginStateLiveData.postValue(LoginUIState.Success(response.authenticated))
            } catch (exception: Exception) {
                _loginStateLiveData.postValue(LoginUIState.Error(exception))
            }

        }
    }

}