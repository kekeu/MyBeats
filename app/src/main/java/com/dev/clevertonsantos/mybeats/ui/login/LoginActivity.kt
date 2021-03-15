package com.dev.clevertonsantos.mybeats.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dev.clevertonsantos.mybeats.MainActivity
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneApiDataSource
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = LoginViewModel.ViewModelFactory(HeadphoneApiDataSource())
        .create(LoginViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        actionBar?.hide()
        setContentView(R.layout.activity_login)

        val usuario = findViewById<TextInputEditText>(R.id.editTextUsuario)
        val senha = findViewById<TextInputEditText>(R.id.editTextSenha)
        val button = findViewById<Button>(R.id.buttonEntrar)
        val text = findViewById<TextView>(R.id.textViewInscrevase)

        text.setOnClickListener {}
        button.setOnClickListener {
            viewModel.login(usuario.text.toString(), senha.text.toString())
        }

        viewModel.loginLiveData.observe(this, {
            it?.let {
                if (it.first) {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}