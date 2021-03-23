package com.dev.clevertonsantos.mybeats.di

import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneApiDataSource
import com.dev.clevertonsantos.mybeats.ui.home.HomeViewModel
import com.dev.clevertonsantos.mybeats.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiDataSourceModule = module {
    single {
        HeadphoneApiDataSource()
    }
}

val loginModule = module {
    viewModel {
        LoginViewModel(get<HeadphoneApiDataSource>())
    }
}

val homeModule = module {
    viewModel {
        HomeViewModel(get<HeadphoneApiDataSource>())
    }
}