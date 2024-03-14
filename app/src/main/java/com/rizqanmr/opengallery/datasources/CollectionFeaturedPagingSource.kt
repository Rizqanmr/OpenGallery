package com.rizqanmr.opengallery.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.opengallery.models.CollectionsItemModel
import com.rizqanmr.opengallery.repository.AppRepository

class CollectionFeaturedPagingSource(
    private val appRepository: AppRepository
) : PagingSource<Int, CollectionsItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, CollectionsItemModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionsItemModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = appRepository.getCollectionFeatured(nextPageNumber)

            LoadResult.Page(
                data = response?.collections.orEmpty(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response?.collections?.isEmpty() == true) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}