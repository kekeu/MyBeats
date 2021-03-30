package com.dev.clevertonsantos.mybeats.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneRepository
import com.dev.clevertonsantos.mybeats.extensions.isEmailValid
import kotlinx.coroutines.launch

class LoginViewModel(private val dataSource: HeadphoneRepository) : ViewModel() {

    val loginLiveData: MutableLiveData<Pair<Boolean, Int>> = MutableLiveData()

    fun login(email: String, password: String) {
        if (isValidForm(email, password)) {
            viewModelScope.launch {
                try {
                    val response = dataSource.login(email, password)
                    if (response.isSuccessful) {
                        loginLiveData.value = Pair(true, R.string.app_name)
                    } else {
                        loginLiveData.value = Pair(false, R.string.user_password_invalid)
                    }
                } catch (t: Throwable) {
                    loginLiveData.value = Pair(false, R.string.error)
                }
            }
        } else {
            loginLiveData.value = Pair(false, R.string.user_password_invalid)
        }
    }

    private fun isValidForm(email: String, password: String): Boolean {
        if (!email.isEmailValid()) {
            return false
        }
        if (password.isEmpty()) {
            return false
        }
        return true
    }
}