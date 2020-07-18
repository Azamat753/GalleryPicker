package com.example.gallerypicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class SelectedImagesActivity : AppCompatActivity(), GalleryAdapter.GalleryViewHolder.GalleryListener  {


    private var images = mutableListOf<GalleryImage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_images)
        setupAdapter()
    }

    private fun  setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = GalleryAdapter(selectedImages, images, this)
    }

    companion object {

        private var selectedImages = mutableListOf<GalleryImage>()
        fun start(activity: Activity?, selectedList: MutableList<GalleryImage>) {
            selectedImages = selectedList
            activity?.startActivity(Intent(activity, SelectedImagesActivity::class.java))
        }
    }

    override fun onItemClick(image: GalleryImage) {

    }

}
