package com.ferechamitbeyli.complexfetchretrofitexample.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.api.load

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    this.scaleType = ImageView.ScaleType.FIT_CENTER
    this.load(uri) {
        placeholder(progressDrawable)
    }
}