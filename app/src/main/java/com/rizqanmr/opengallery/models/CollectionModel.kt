package com.rizqanmr.opengallery.models

import com.google.gson.annotations.SerializedName

data class CollectionModel(
    @SerializedName("next_page")
    val nextPage: String = "",
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("collections")
    val collections: List<CollectionsItemModel>?,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)