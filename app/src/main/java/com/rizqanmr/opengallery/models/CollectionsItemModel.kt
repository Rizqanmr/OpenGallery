package com.rizqanmr.opengallery.models

import com.google.gson.annotations.SerializedName

data class CollectionsItemModel(
    @SerializedName("private")
    val private: Boolean? = false,
    @SerializedName("media_count")
    val mediaCount: Int? = 0,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("videos_count")
    val videosCount: Int? = 0,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("photos_count")
    val photosCount: Int? = 0
) {
    fun getSizePhotos(): String {
        return "$photosCount photos"
    }
}