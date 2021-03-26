package com.dev.clevertonsantos.mybeats

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MyBeatsApp::class.java.name, context)
    }
}