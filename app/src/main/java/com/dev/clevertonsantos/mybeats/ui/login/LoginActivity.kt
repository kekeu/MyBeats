package com.dev.clevertonsantos.mybeats.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.dev.clevertonsantos.mybeats.MainActivity
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.extensions.isEmailValid
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        defineFullScreen()
        setContentView(R.layout.activity_login)

        setUp()
    }

    private fun defineFullScreen() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        actionBar?.hide()
    }

    private fun setUp() {
        loginTextViewRegister.setOnClickListener {}
        loginButtonEnter.setOnClickListener {
            viewModel.login(loginEditTextUser.text.toString(), loginEditTextPassword.text.toString())
        }

        viewModel.loginLiveData.observe(this, {
            it?.let {
                if (it.first) {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,
                            getString(it.second),
                            Toast.LENGTH_SHORT).show()
                }
            }
        })

        loginEditTextUser.addTextChangedListener {
            if (loginEditTextUser.text.toString().isEmailValid()) {
                loginEditTextUser.error = null
            } else {
                loginEditTextUser.error = getString(R.string.login_edit_text_error_invalid_email)
            }
        }

        loginEditTextPassword.addTextChangedListener {
            if (loginEditTextPassword.text.toString().isNotEmpty()) {
                loginEditTextPassword.error = null
            } else {
                loginEditTextPassword.error = getString(R.string.login_edit_text_error_invalid_password)
            }
        }
    }

}