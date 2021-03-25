package com.dev.clevertonsantos.mybeats.ui.login

import androidx.test.core.app.ApplicationProvider
import io.mockk.unmockkAll
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import kotlin.jvm.Throws

class KoinTestRule(private val modules: List<Module> = emptyList(),
                   private val isStarted:Boolean = true) : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    if (isStarted){
                        startKoin {
                            androidContext(ApplicationProvider.getApplicationContext())
                            modules(modules)
                        }
                    }else{
                        loadKoinModules(modules)
                    }
                    base.evaluate()
                } finally {
                    unmockkAll()
                    if (isStarted) {
                        stopKoin()
                    } else {
                        unloadKoinModules(modules)
                    }
                }

            }
        }
    }
}