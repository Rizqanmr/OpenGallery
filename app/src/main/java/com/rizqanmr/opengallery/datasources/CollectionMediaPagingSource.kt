package com.rizqanmr.opengallery.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.opengallery.models.MediaItemModel
import com.rizqanmr.opengallery.repository.AppRepository

class CollectionMediaPagingSource(
    private val id: String,
    private val appRepository: AppRepository
) : PagingSource<Int, MediaItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, MediaItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaItemModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = appRepository.getCollectionMedia(id, nextPageNumber)

            LoadResult.Page(
                data = response?.media.orEmpty(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response?.media?.isEmpty() == true) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}