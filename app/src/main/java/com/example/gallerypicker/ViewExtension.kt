package com.example.gallerypicker

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition

fun ImageView.loadImage(context: Context, url: String?) {
    val imageView = this
    Glide.with(context)
        .asBitmap()
        .load(url)
        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).priority(Priority.HIGH))
        .into(object : BitmapImageViewTarget(this) {
            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                val viewWidthToBitmapWidthRatio = imageView.width.toDouble() / bitmap.width.toDouble()
                imageView.layoutParams.height = (bitmap.height * viewWidthToBitmapWidthRatio).toInt()
                imageView.setImageBitmap(bitmap)
            }
        })
}