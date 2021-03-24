package com.dev.clevertonsantos.mybeats.ui.login

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dev.clevertonsantos.mybeats.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {


    @get:Rule
    val mActivityTestRule: ActivityScenarioRule<LoginActivity> =
            ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginCheck() {
        login {
            checkText(R.id.loginTextViewMyBeats,"Meu Beats")
            checkText(R.id.loginTextViewSpecializes,"Especializados em fones para deejay")
            checkText(R.id.loginButtonEnter,"Entrar")
            checkText(R.id.loginTextViewRegistered,"Ainda não é cadastrado?")
            checkText(R.id.loginTextViewRegister,"Inscreva-se")
        }
    }

    @Test
    fun loginError() {
        login {
            setUser("cleverton@invilllia.com")
            setPassword("1234")
            clickLogin()
        }
    }

    @Test
    fun loginSuccess() {
        login {
            setUser("cleverton@invilllia.com")
            setPassword("123")
            clickLogin()
        }
    }

}