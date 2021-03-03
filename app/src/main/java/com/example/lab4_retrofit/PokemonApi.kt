package com.example.lab4_retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("/{id}")
    fun getPokemonById(@Path("id") id:String): Call<JsonObject>

}