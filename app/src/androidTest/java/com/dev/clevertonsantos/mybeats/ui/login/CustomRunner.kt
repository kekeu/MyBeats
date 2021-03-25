package com.dev.clevertonsantos.mybeats.ui.login

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomRunner:AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}
class TestApplication:Application(){
    override fun onCreate() {
        super.onCreate()
    }
}