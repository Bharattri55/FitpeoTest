package com.test.fitpeo.data.api

import com.test.fitpeo.data.model.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getAlbumList(): Response<List<AlbumResponse>>
}