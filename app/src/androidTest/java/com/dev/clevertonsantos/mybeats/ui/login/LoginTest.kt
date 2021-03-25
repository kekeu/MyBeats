package com.dev.clevertonsantos.mybeats.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.di.apiDataSourceModule
import com.dev.clevertonsantos.mybeats.di.homeModule
import com.dev.clevertonsantos.mybeats.di.loginModule
import com.dev.clevertonsantos.mybeats.di.networkModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val koinRule = KoinTestRule(listOf(apiDataSourceModule, loginModule,
            homeModule, networkModule))

    @Test
    fun loginCheckSucess() {
        login {
            withLoginSucess(
                    "teste@teste.com",
                    "1234")
            withListHeadphoneApiDataSource()
            withLoginViewModel()
            withHomeViewModel()
        } launchActivityLogin {
            setUser("teste@teste.com")
            setPassword("1234")
            clickLogin()
        } check {
            checkText(R.id.homeButtonAdd,"Adicionar")
        }
    }

    @Test
    fun loginCheckError() {
        login {
            withLoginError(
                    "teste@teste.com",
                    "123")
            withLoginViewModel()
        } launchActivityLogin {
            setUser("teste@teste.com")
            setPassword("123")
            clickLogin()
        } check {
            checkText(R.id.loginButtonEnter,"Entrar")
        }
    }

    @Test
    fun loginCheckScreen() {
        login {
        } launchActivityLogin {
        } check {
            checkText(R.id.loginTextViewMyBeats,"Meu Beats")
            checkText(R.id.loginTextViewSpecializes,"Especializados em fones para deejay")
            checkText(R.id.loginButtonEnter,"Entrar")
            checkText(R.id.loginTextViewRegistered,"Ainda não é cadastrado?")
            checkText(R.id.loginTextViewRegister,"Inscreva-se")
        }
    }

}