package com.test.fitpeo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.fitpeo.data.model.AlbumResponse
import com.test.fitpeo.dependencyInjection.NetworkHelper
import com.test.fitpeo.repository.AlbumRepository
import com.test.fitpeo.util.NetworkResource
import com.test.fitpeo.util.NetworkResource.Companion.error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<NetworkResource<List<AlbumResponse>>>()
    val users: LiveData<NetworkResource<List<AlbumResponse>>>
        get() = _users

    init {
        fetchAlbumList()
    }

    fun fetchAlbumList() {
        viewModelScope.async {
            _users.postValue(NetworkResource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                albumRepository.getAlbumList().let {
                    if (it.isSuccessful) {
                        _users.postValue(NetworkResource.success(it.body()) as NetworkResource<List<AlbumResponse>>?)
                    } else _users.postValue(error(data = null, message = it.errorBody().toString()))
                }
            } else _users.postValue(error(null, message = "No Internet Connection"))
        }
    }

}