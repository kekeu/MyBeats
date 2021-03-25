package com.dev.clevertonsantos.mybeats.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val dataSource: HeadphoneRepository) : ViewModel() {

    val loginLiveData: MutableLiveData<Pair<Boolean, String?>> = MutableLiveData()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = dataSource.login(email, password)
            if (response.isSuccessful) {
                loginLiveData.value = Pair(true, null)
            } else {
                loginLiveData.value = Pair(false, response.message())
            }
        }
    }
}