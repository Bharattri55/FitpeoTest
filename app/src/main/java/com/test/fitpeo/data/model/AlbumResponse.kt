package com.test.fitpeo.data.model
import com.google.gson.annotations.SerializedName


data class AlbumResponse(
    @SerializedName("albumId")
    val albumId: Int, // 1
    @SerializedName("id")
    val id: Int, // 9
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String, // https://via.placeholder.com/150/51aa97
    @SerializedName("title")
    val title: String, // qui eius qui autem sed
    @SerializedName("url")
    val url: String // https://via.placeholder.com/600/51aa97
)