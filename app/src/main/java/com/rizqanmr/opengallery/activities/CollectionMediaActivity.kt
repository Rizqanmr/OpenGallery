package com.rizqanmr.opengallery.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.rizqanmr.opengallery.R
import com.rizqanmr.opengallery.databinding.ActivityCollectionMediaBinding
import com.rizqanmr.opengallery.utils.Constant
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupViewPage()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        //observe
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
        }
    }
}