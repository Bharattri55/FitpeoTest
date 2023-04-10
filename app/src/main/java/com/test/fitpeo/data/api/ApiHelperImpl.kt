package com.test.fitpeo.data.api

import com.test.fitpeo.data.model.AlbumResponse
import com.test.fitpeo.util.NetworkResource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {
    override suspend fun getAlbumList(): Response<List<AlbumResponse>> = apiService.getAlbumList()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ApiHelperModule{
    @Singleton
    @Binds
    abstract fun bindApiHelper(apiHelperImpl: ApiHelperImpl) : ApiHelper
}