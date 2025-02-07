package com.johncelso.inocente_comp304_001_hands_on_test2_f24.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {

    private const val baseURL = "http://gd.geobytes.com/"
    private const val weatherBaseURL = "https://api.openweathermap.org/data/2.5/"
    val api: WeatherAPIInterface by lazy{
        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build()
        retrofit.create(WeatherAPIInterface::class.java)
    }

    val weatherApi : WeatherAPIInterface by lazy {
        val retrofit =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(weatherBaseURL)
            .build()
        retrofit.create(WeatherAPIInterface::class.java)
    }
}