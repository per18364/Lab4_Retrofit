package com.example.lab4_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit2() {

    private var urls: PokemonApi

    var Urls = Urls()

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Urls.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        urls = retrofit.create(PokemonApi::class.java)
    }

    fun getService(): PokemonApi{
        return urls
    }
}