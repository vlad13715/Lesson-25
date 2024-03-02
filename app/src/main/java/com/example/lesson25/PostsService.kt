package com.example.lesson25

import retrofit2.http.GET

interface PostsService {

    @GET("v1/sample-data/photos")
    suspend fun getPicture():MainPosts
}