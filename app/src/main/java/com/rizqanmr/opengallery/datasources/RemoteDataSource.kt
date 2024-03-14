package com.rizqanmr.opengallery.datasources

import com.rizqanmr.opengallery.models.CollectionMediaModel
import com.rizqanmr.opengallery.models.CollectionModel
import com.rizqanmr.opengallery.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getCollectionFeatured(page: Int) : CollectionModel? {
        return withContext(coroutineContext) {
            try {
                val collection = apiService.getCollectionFeatured(page)
                collection
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getCollectionMedia(id: String, type: String, page: Int) : CollectionMediaModel? {
        return withContext(coroutineContext) {
            try {
                val media = apiService.getCollectionMedia(id, type, page)
                media
            } catch (e: Exception) {
                null
            }
        }
    }
}