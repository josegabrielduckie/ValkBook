package com.jgduckie.socialing.service

import com.jgduckie.socialing.model.user.SingUserDto
import com.jgduckie.socialing.model.user.UserAuthDto
import com.jgduckie.socialing.model.user.UserDataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/auth/local")
   fun loginUser (@Body user: UserAuthDto) : Call<UserDataResponse>
    @Headers("Content-Type: application/json")
    @POST("/auth/local/register")
    fun signUp (@Body user: SingUserDto) : Call<UserDataResponse>

}