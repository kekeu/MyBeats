package com.dev.clevertonsantos.mybeats.ui.login

import com.dev.clevertonsantos.mybeats.R

fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply { func() }

class LoginRobot : BaseTestRobot() {

    fun setUser(user: String) = fillEditText(R.id.loginEditTextUser, user)

    fun setPassword(password: String) = fillEditText(R.id.loginEditTextPassword, password)

    fun clickLogin() = clickButton(R.id.loginButtonEnter)

    fun checkText(id: Int, text: String) = textDisplayed(id, text)

}