package com.dev.clevertonsantos.mybeats.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneRepository
import com.dev.clevertonsantos.mybeats.data.response.convertToListHeadphone
import kotlinx.coroutines.launch

class HomeViewModel(private val dataSource: HeadphoneRepository) : ViewModel() {

    val headphonesLiveData: MutableLiveData<List<Headphone>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()

    fun getHeadphones() {
        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_LOADING, null)

        viewModelScope.launch {
            val response = dataSource.getHeadphones()
            if (response.isSuccessful) {
                headphonesLiveData.value = response.body()?.convertToListHeadphone()
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_HEADPHONES, null)
            } else {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, response.message())
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_LOADING = 0
        private const val VIEW_FLIPPER_HEADPHONES = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}