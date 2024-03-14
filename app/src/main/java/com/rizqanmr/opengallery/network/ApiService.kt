package com.rizqanmr.opengallery.network

import com.rizqanmr.opengallery.models.CollectionMediaModel
import com.rizqanmr.opengallery.models.CollectionModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("collections/featured")
    suspend fun getCollectionFeatured(
        @Query("page") page: Int
    ) : CollectionModel

    @GET("collections/{id}")
    suspend fun getCollectionMedia(
        @Path("id") id: String,
        @Query("type") type: String,
        @Query("page") page: Int
    ) : CollectionMediaModel
}