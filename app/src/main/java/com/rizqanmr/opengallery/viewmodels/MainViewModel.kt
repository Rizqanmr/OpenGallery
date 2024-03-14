package com.rizqanmr.opengallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rizqanmr.opengallery.datasources.CollectionFeaturedPagingSource
import com.rizqanmr.opengallery.models.CollectionsItemModel
import com.rizqanmr.opengallery.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    fun getCollectionFeatured() : LiveData<PagingData<CollectionsItemModel>> {
        return Pager(PagingConfig(pageSize = 15)) {
            CollectionFeaturedPagingSource(appRepository)
        }.liveData.cachedIn(viewModelScope)
    }
}