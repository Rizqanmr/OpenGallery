package com.rizqanmr.opengallery.models

import com.google.gson.annotations.SerializedName

data class CollectionMediaModel(
    @SerializedName("next_page")
    val nextPage: String = "",
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("media")
    val media: List<MediaItemModel>?,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("total_results")
    val totalResults: Int = 0
)