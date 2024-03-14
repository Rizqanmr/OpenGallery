package com.rizqanmr.opengallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizqanmr.opengallery.R
import com.rizqanmr.opengallery.databinding.ItemMediaBinding
import com.rizqanmr.opengallery.models.MediaItemModel
import com.rizqanmr.opengallery.utils.setFitImageUrl

class MediaAdapter : PagingDataAdapter<MediaItemModel, MediaAdapter.MediaViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MediaItemModel>() {
            override fun areItemsTheSame(oldItem: MediaItemModel, newItem: MediaItemModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MediaItemModel, newItem: MediaItemModel): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    private lateinit var mediaListener: MediaListener

    fun setMediaListener(mediaListener: MediaListener) {
        this.mediaListener = mediaListener
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bindData(item, mediaListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    class MediaViewHolder(private val binding: ItemMediaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: MediaItemModel?, listener: MediaListener) {
            (binding as? ItemMediaBinding)?.let { itemMediaBinding ->
                itemMediaBinding.item = item
                with(itemMediaBinding) {
                    ivMedia.setFitImageUrl(item?.srcModel?.tiny, R.drawable.ic_broken_image)
                    ivMedia.setOnClickListener { listener.onItemClick(itemMediaBinding, item) }
                }
            }
        }
    }

    interface MediaListener {
        fun onItemClick(itemMediaBinding: ItemMediaBinding, item: MediaItemModel?)
    }
}