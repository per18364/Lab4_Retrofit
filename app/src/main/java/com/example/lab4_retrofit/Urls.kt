package com.example.lab4_retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Urls {

    @GET
    suspend fun getPokemonByName(@Url url:String):Response<ApiResponse>

}