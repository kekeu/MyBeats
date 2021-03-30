package com.dev.clevertonsantos.mybeats.di

import com.dev.clevertonsantos.mybeats.data.HeadphoneService
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneApiDataSource
import com.dev.clevertonsantos.mybeats.ui.home.HomeViewModel
import com.dev.clevertonsantos.mybeats.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiDataSourceModule = module {
    single {
        HeadphoneApiDataSource(get())
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

val networkModule = module {
    factory { provideHeadphoneService(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl("http://20.195.184.239/")
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun provideHeadphoneService(retrofit: Retrofit): HeadphoneService = retrofit
    .create(HeadphoneService::class.java)
