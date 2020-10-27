package com.jgduckie.socialing.utils

import android.util.Log
import com.google.gson.Gson
import com.jgduckie.socialing.model.user.SingUserDto
import com.jgduckie.socialing.model.user.UserAuthDto
import com.jgduckie.socialing.model.user.UserDataResponse
import com.jgduckie.socialing.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtils {


    fun loginUser(userAuthDto: UserAuthDto, onresult: (UserDataResponse?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST().endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        var userDataResponse: UserDataResponse? = null

        val call =
            service.loginUser(userAuthDto)
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                userDataResponse = response.body()
                onresult(userDataResponse)
                Log.i("respuesta", Gson().toJson(userDataResponse))


            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                userDataResponse = null
                onresult(userDataResponse)

            }


        })


    }

    fun signUpUser(singUser: SingUserDto, onresult: (UserDataResponse?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(HOST().endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        var userDataResponse: UserDataResponse? = null

        val call =
            service.signUp(singUser)
        call.enqueue(object : Callback<UserDataResponse> {
            override fun onResponse(
                call: Call<UserDataResponse>,
                response: Response<UserDataResponse>
            ) {
                userDataResponse = response.body()
                onresult(userDataResponse)
                Log.i("respuesta", Gson().toJson(userDataResponse))


            }

            override fun onFailure(call: Call<UserDataResponse>, t: Throwable) {
                userDataResponse = null
                onresult(userDataResponse)
                Log.i("respuesta", t.localizedMessage)
                Log.i("respuesta", t.message.toString())


            }


        })


    }


}