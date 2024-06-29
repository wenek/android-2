package com.example.nietypowykalendarz

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String) // Zmień na odpowiednią strukturę odpowiedzi

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/user/session")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}