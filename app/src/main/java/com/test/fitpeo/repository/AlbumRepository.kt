package com.test.fitpeo.repository

import com.test.fitpeo.data.api.ApiHelperImpl
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val apiHelperImpl: ApiHelperImpl) {
    suspend fun getAlbumList() = apiHelperImpl.getAlbumList()
}