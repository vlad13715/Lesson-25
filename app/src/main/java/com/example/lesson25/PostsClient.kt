package com.example.lesson25

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsClient {


    private var retrofit:Retrofit?=null

    fun getPostsService():PostsService{

        if (retrofit ==null){

            retrofit = Retrofit.Builder()
                .baseUrl("https://api.slingacademy.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!.create(PostsService::class.java)
    }

}