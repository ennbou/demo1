package com.ennbou.mvvm.api

import com.ennbou.mvvm.entities.UserDetails
import retrofit2.Call
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class UserService {
    companion object{
        fun fetchUsers() : Call<List<UserDetails>>{
            val retrofit = Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val userService = retrofit.create(UserAPI::class.java)

            return userService.getAll()

        }
    }
}

interface UserAPI {
    @GET("/users")
    fun getAll() : Call<List<UserDetails>>
}