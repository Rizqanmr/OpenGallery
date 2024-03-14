package com.rizqanmr.opengallery.models

import com.google.gson.annotations.SerializedName

data class SrcModel(
    @SerializedName("small")
    val small: String? = "",
    @SerializedName("original")
    val original: String? = "",
    @SerializedName("large")
    val large: String? = "",
    @SerializedName("tiny")
    val tiny: String? = "",
    @SerializedName("medium")
    val medium: String? = "",
    @SerializedName("large2x")
    val largeX: String? = "",
    @SerializedName("portrait")
    val portrait: String? = "",
    @SerializedName("landscape")
    val landscape: String? = ""
)