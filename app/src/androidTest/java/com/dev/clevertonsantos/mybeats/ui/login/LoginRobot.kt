package com.dev.clevertonsantos.mybeats.ui.login

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneApiDataSource
import com.dev.clevertonsantos.mybeats.ui.home.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Response

fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply { func() }

class LoginRobot : BaseTestRobot(), KoinComponent {

    private val headphoneApiDataSource = mockk<HeadphoneApiDataSource>()
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var homeViewModel: HomeViewModel

    infix fun launchActivityLogin(func: LoginRobot.() -> Unit) : LoginRobot {
        ActivityScenario.launch<LoginActivity>(Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                LoginActivity::class.java
        ))
        return LoginRobot().apply(func)
    }

    fun withLoginError(email: String, password: String) {
        every {
            runBlocking {
                headphoneApiDataSource.login(email, password)
            }
        } returns Response.error(404, ResponseBody.create(null, "Error"))
    }

    fun withLoginSucess(email: String, password: String) {
        every {
            runBlocking {
                headphoneApiDataSource.login(email, password)
            }
        } returns Response.success(null)
    }

    fun withListHeadphoneApiDataSource() {
        every {
            runBlocking {
                headphoneApiDataSource.getHeadphones()
            }
        } returns Response.success(listOf())
    }

    fun withHomeViewModel() {
        homeViewModel = HomeViewModel(headphoneApiDataSource)
        loadKoinModules(module {
            viewModel(override = true) { homeViewModel }
        })
    }

    fun withLoginViewModel() {
        loginViewModel = LoginViewModel(headphoneApiDataSource)
        loadKoinModules(module {
            viewModel(override = true) { loginViewModel }
        })
    }

    fun setUser(user: String) = fillEditText(R.id.loginEditTextUser, user)

    fun setPassword(password: String) = fillEditText(R.id.loginEditTextPassword, password)

    fun clickLogin() = clickButton(R.id.loginButtonEnter)

    infix fun check(func: Result.() -> Unit) = Result().apply(func)

    inner class Result {

        fun checkText(id: Int, text: String) = textDisplayed(id, text)

    }

}