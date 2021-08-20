package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServise {
    var retrofit: Retrofit? = null

    fun apiClint(): Api{
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("https://dummyapi.io/data/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!.create(Api::class.java)

    }
}