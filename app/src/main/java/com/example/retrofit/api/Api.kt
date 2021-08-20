package com.example.retrofit.api

import com.example.retrofit.model.PostModel
import com.example.retrofit.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @Headers("app-id:611cf30f7a074baf8bea546d")
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>




    @Headers("app-id:611cf30f7a074baf8bea546d")
    @GET("post")
    fun getPosts(): Call<BaseResponse<List<PostModel>>>


    @Headers("app-id:611cf30f7a074baf8bea546d")
    @GET("user/{user_id}/post ")
    fun getPostByUser(@Path("user_id") id: String): Call<BaseResponse<List<PostModel>>>
}