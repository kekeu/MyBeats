package com.dev.clevertonsantos.mybeats.data.repository

import com.dev.clevertonsantos.mybeats.data.HeadphoneService
import com.dev.clevertonsantos.mybeats.data.request.UserRequest

class HeadphoneApiDataSource(private val headphoneService: HeadphoneService) : HeadphoneRepository {

    override suspend fun getHeadphones() = headphoneService.listHeadphones()

    override suspend fun login(email: String, password: String) = headphoneService
        .login(UserRequest(email, password))

}