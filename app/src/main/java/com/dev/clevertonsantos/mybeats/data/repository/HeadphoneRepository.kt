package com.dev.clevertonsantos.mybeats.data.repository

import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import retrofit2.Response

interface HeadphoneRepository {

    suspend fun getHeadphones() : Response<List<HeadphoneResponse>>
    suspend fun login(email: String, password: String) : Response<Void>

}