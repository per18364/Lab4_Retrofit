package com.example.lab4_retrofit

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("object") var datos: List<String>)