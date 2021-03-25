package com.dev.clevertonsantos.mybeats.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.clevertonsantos.mybeats.data.HeadphoneResult
import com.dev.clevertonsantos.mybeats.data.HeadphoneService
import com.dev.clevertonsantos.mybeats.data.request.UserRequest
import com.dev.clevertonsantos.mybeats.data.response.HeadphoneResponse
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success

class HeadphoneApiDataSourceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val headphoneApiDataSource = mockk<HeadphoneApiDataSource>()

    private val service = mockk<HeadphoneService>(relaxed = true)

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testLogin() {

        val response = success(null)

        every {
            runBlocking {
                headphoneApiDataSource.login("", "")
            }
        } returns success(null)

        verify(exactly = 1) {
            runBlocking {
                headphoneApiDataSource.login("", "")
            }
        }

        confirmVerified(headphoneApiDataSource)

    }

}

