package com.rizqanmr.opengallery.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.rizqanmr.opengallery.R
import com.rizqanmr.opengallery.databinding.ActivityPhotoViewBinding
import com.rizqanmr.opengallery.utils.Constant
import com.rizqanmr.opengallery.utils.setFitPhotoUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoViewActivity : AppCompatActivity() {

    companion object {
        fun newIntent(activity: Activity, bundle: Bundle) {
            activity.startActivity(
                Intent(activity, PhotoViewActivity::class.java).apply {
                    putExtras(bundle)
                }
            )
        }
    }

    private lateinit var binding: ActivityPhotoViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
            photoView.setFitPhotoUrl(intent.getStringExtra(Constant.EXTRA_IMAGE_URI), R.drawable.ic_error)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}