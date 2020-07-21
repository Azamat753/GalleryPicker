package com.example.gallerypicker

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GalleryAdapter.GalleryViewHolder.GalleryListener {

    val selectedImages = mutableListOf<GalleryImage>()
    private val PERMISSION_REQUEST_CODE = 100

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isHasPermission()
        confirmAction()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun isHasPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            setupAdapter()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun  setupAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        val list = FileHelper().getFolderImages(this)
        recycler_view.adapter = GalleryAdapter(list, selectedImages, this)
        Log.e("ololo", "setupAdapter: ${FileHelper().getFolderImages(this)}" )
    }

    override fun onItemClick(image: GalleryImage) {
//        if (image.isSelected) {
//            selectedImages.add(image)
//        } else {
//            selectedImages.remove(image)
//        }
//        updateFloatingPanel()
        Toast.makeText(this,"${image.imagePath}",Toast.LENGTH_SHORT).show()
    }

    private fun updateFloatingPanel() {
        count.text = "Выбрано ${selectedImages.size} фотографий"
    }

    private fun confirmAction() {
        confirm.setOnClickListener {
            SelectedImagesActivity.start(this, selectedImages)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupAdapter()
            }
        }
    }

}
