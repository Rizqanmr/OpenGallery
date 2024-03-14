package com.rizqanmr.opengallery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rizqanmr.opengallery.adapters.CollectionAdapter
import com.rizqanmr.opengallery.adapters.LoadingStateAdapter
import com.rizqanmr.opengallery.databinding.ActivityMainBinding
import com.rizqanmr.opengallery.databinding.ItemCollectionBinding
import com.rizqanmr.opengallery.models.CollectionsItemModel
import com.rizqanmr.opengallery.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val collectionAdapter by lazy { CollectionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupViewPage()
    }

    private fun setupObservers() {
        viewModel.getCollectionFeatured().observe(this) {
            collectionAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupViewPage() {
        binding.rvCollection.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            setHasFixedSize(true)
            adapter = collectionAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter { collectionAdapter.retry() }
            )
        }

        collectionAdapter.setCollectionListener(object : CollectionAdapter.CollectionListener {
            override fun onItemClick(
                itemCollectionBinding: ItemCollectionBinding,
                item: CollectionsItemModel?
            ) {
                //navigate to detail
            }

        })
    }
}