package com.test.fitpeo.data.api

import com.test.fitpeo.data.model.AlbumResponse
import com.test.fitpeo.util.NetworkResource
import retrofit2.Response

interface ApiHelper {
    suspend fun getAlbumList() : Response<List<AlbumResponse>>
}