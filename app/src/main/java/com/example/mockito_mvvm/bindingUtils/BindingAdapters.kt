package com.example.mockito_mvvm.bindingUtils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView?, url: String?, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}