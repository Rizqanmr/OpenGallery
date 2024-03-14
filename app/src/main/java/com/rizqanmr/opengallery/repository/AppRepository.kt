package com.rizqanmr.opengallery.repository

import com.rizqanmr.opengallery.datasources.RemoteDataSource
import com.rizqanmr.opengallery.models.CollectionMediaModel
import com.rizqanmr.opengallery.models.CollectionModel
import javax.inject.Inject

class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getCollectionFeatured(page: Int) : CollectionModel? {
        return remoteDataSource.getCollectionFeatured(page)
    }

    suspend fun getCollectionMedia(id: String, page: Int) : CollectionMediaModel? {
        return remoteDataSource.getCollectionMedia(id, "photos", page)
    }
}