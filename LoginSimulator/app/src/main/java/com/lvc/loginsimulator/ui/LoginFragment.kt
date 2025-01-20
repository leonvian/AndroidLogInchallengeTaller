package com.lvc.loginsimulator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lvc.loginsimulator.R
import androidx.fragment.app.viewModels
import com.lvc.loginsimulator.databinding.LoginScreenBinding

class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: LoginScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignIn.setOnClickListener {
            val login = binding.editTextLogin.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.signIn(login, password)
        }

        observe()
    }

    private fun observe() {
        viewModel.loginStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginUIState.Loading -> {
                    showLoading()
                }
                is LoginUIState.Success -> {
                    hideLoading()
                    showSuccessToast(state.authenticated)
                }
                is LoginUIState.Error -> {
                    hideLoading()
                    showErrorToast()
                }
            }
        }
    }

    private fun hideLoading() {
        binding.loadingIndicator.visibility = View.INVISIBLE
    }
    private fun showLoading() {
        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun showErrorToast() {
        showToast(R.string.login_generic_error_message)
    }

    private fun showSuccessToast(authenticated: Boolean) {
        val message = if (authenticated) R.string.sign_successful_message else R.string.login_generic_error_message
        showToast(message)
    }

    private fun showToast(message: Int) {
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_LONG).show()
    }
}