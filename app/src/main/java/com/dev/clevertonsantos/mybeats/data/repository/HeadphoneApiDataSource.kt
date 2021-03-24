package com.dev.clevertonsantos.mybeats.data.repository

import com.dev.clevertonsantos.mybeats.data.HeadphoneResult
import com.dev.clevertonsantos.mybeats.data.HeadphoneService
import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import com.dev.clevertonsantos.mybeats.data.request.UserRequest
import com.dev.clevertonsantos.mybeats.data.response.convertToListHeadphone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadphoneApiDataSource(private val headphoneService: HeadphoneService) : HeadphoneRepository {

    override fun getHeadphones(resultCallback: (result: HeadphoneResult) -> Unit) {
        headphoneService.listHeadphones().enqueue(object: Callback<List<HeadphoneResponse>> {
            override fun onResponse(
                call: Call<List<HeadphoneResponse>>,
                response: Response<List<HeadphoneResponse>>
            ) {
                if (response.isSuccessful) {
                    val headphones = response.body()?.convertToListHeadphone()
                    resultCallback(HeadphoneResult.Success(headphones))
                } else {
                    resultCallback(HeadphoneResult.ApiError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<HeadphoneResponse>>, t: Throwable) {
                resultCallback(HeadphoneResult.ServerError)
            }
        })
    }

    override fun login(
        email: String,
        password: String,
        resultCallback: (result: HeadphoneResult) -> Unit
    ) {
        headphoneService.login(UserRequest(email, password)).enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    resultCallback(HeadphoneResult.Success(null))
                } else {
                    resultCallback(HeadphoneResult.ApiError(response.message()))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                resultCallback(HeadphoneResult.ServerError)
            }
        })
    }
}