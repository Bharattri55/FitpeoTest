package com.test.fitpeo.util


/*
* @Date : 04/10/2023
* @Created By : Bharat Tripathi
* @Description: This data class is create generic class to hold data response from network
* */
data class NetworkResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): NetworkResource<T> =
            NetworkResource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String?): NetworkResource<T> =
            NetworkResource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): NetworkResource<T> =
            NetworkResource(status = Status.LOADING, data = data, message = null)
    }
}
