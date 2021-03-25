package com.dev.clevertonsantos.mybeats.data

import com.dev.clevertonsantos.mybeats.data.request.UserRequest
import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HeadphoneService {

    @GET("beats")
    suspend fun listHeadphones(): Response<List<HeadphoneResponse>>

    @POST("login")
    suspend fun login(@Body user: UserRequest): Response<Void>
}