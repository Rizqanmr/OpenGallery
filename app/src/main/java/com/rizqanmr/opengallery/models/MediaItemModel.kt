package com.rizqanmr.opengallery.models

import com.google.gson.annotations.SerializedName

data class MediaItemModel(
    @SerializedName("src")
    val srcModel: SrcModel?,
    @SerializedName("width")
    val width: Int? = 0,
    @SerializedName("avg_color")
    val avgColor: String? = "",
    @SerializedName("alt")
    val alt: String? = "",
    @SerializedName("photographer")
    val photographer: String? = "",
    @SerializedName("photographer_url")
    val photographerUrl: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("photographer_id")
    val photographerId: Int? = 0,
    @SerializedName("liked")
    val liked: Boolean? = false,
    @SerializedName("height")
    val height: Int? = 0
)