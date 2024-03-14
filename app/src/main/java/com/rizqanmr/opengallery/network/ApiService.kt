package com.rizqanmr.opengallery.network

import com.rizqanmr.opengallery.models.CollectionModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("collections/featured")
    suspend fun getCollectionFeatured(
        @Query("page") page: Int
    ) : CollectionModel
}