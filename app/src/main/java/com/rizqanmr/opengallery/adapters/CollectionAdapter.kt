package com.rizqanmr.opengallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizqanmr.opengallery.databinding.ItemCollectionBinding
import com.rizqanmr.opengallery.models.CollectionsItemModel

class CollectionAdapter : PagingDataAdapter<CollectionsItemModel, CollectionAdapter.CollectionViewHolder>(
    DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionsItemModel>() {
            override fun areItemsTheSame(oldItem: CollectionsItemModel, newItem: CollectionsItemModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CollectionsItemModel, newItem: CollectionsItemModel): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    private lateinit var collectionListener: CollectionListener

    fun setCollectionListener(collectionListener: CollectionListener) {
        this.collectionListener = collectionListener
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bindData(item, collectionListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    class CollectionViewHolder(private val binding: ItemCollectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: CollectionsItemModel?, listener: CollectionListener) {
            (binding as? ItemCollectionBinding)?.let { itemCollection ->
                itemCollection.item = item
                with(itemCollection) {
                    cvCollection.setOnClickListener { listener.onItemClick(itemCollection, item) }
                }
            }
        }
    }

    interface CollectionListener {
        fun onItemClick(itemCollectionBinding: ItemCollectionBinding, item: CollectionsItemModel?)
    }
}