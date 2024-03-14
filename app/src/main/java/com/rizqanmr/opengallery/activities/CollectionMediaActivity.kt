package com.rizqanmr.opengallery.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.rizqanmr.opengallery.R
import com.rizqanmr.opengallery.adapters.LoadingStateAdapter
import com.rizqanmr.opengallery.adapters.MediaAdapter
import com.rizqanmr.opengallery.databinding.ActivityCollectionMediaBinding
import com.rizqanmr.opengallery.databinding.ItemMediaBinding
import com.rizqanmr.opengallery.models.MediaItemModel
import com.rizqanmr.opengallery.utils.Constant
import com.rizqanmr.opengallery.viewmodels.CollectionMediaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionMediaActivity : AppCompatActivity() {

    companion object {
        fun newIntent(activity: Activity, bundle: Bundle?) {
            activity.startActivity(
                Intent(activity, CollectionMediaActivity::class.java).apply {
                    bundle?.let { putExtras(it) }
                }
            )
        }
    }

    private lateinit var binding: ActivityCollectionMediaBinding
    private val viewModel by viewModels<CollectionMediaViewModel>()
    private val mediaAdapter by lazy { MediaAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setCollectionId(intent.getStringExtra(Constant.EXTRA_COLLECTION_ID).orEmpty())
        setupObservers()
        setupViewPage()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        viewModel.getCollectionMedia().observe(this) {
            mediaAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupViewPage() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)

                val upArrow = ContextCompat.getDrawable(this@CollectionMediaActivity, R.drawable.ic_arrow_back)
                upArrow?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                setHomeAsUpIndicator(upArrow)
                title = intent.getStringExtra(Constant.EXTRA_COLLECTION_TITLE)
            }

            rvMedia.apply {
                layoutManager = GridLayoutManager(this@CollectionMediaActivity, 3)
                setHasFixedSize(true)
                adapter = mediaAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter { mediaAdapter.retry() }
                )
            }

            mediaAdapter.setMediaListener(object : MediaAdapter.MediaListener {
                override fun onItemClick(
                    itemMediaBinding: ItemMediaBinding,
                    item: MediaItemModel?
                ) {
                    //open image
                }

            })
        }
    }
}