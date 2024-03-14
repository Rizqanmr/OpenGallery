package com.rizqanmr.opengallery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rizqanmr.opengallery.datasources.CollectionMediaPagingSource
import com.rizqanmr.opengallery.models.MediaItemModel
import com.rizqanmr.opengallery.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionMediaViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private var id: String = ""

    fun setCollectionId(id: String) {
        this.id = id
    }

    fun getCollectionMedia() : LiveData<PagingData<MediaItemModel>> {
        return Pager(PagingConfig(pageSize = 15)) {
            CollectionMediaPagingSource(id, appRepository)
        }.liveData.cachedIn(viewModelScope)
    }
}