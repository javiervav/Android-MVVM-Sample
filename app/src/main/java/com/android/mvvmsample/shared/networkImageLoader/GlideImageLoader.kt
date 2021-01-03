package com.android.mvvmsample.shared.networkImageLoader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.android.mvvmsample.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class GlideImageLoader(private val context: Context) : NetworkImageLoader {

    private val roundedCornerRadius = context.resources.getDimensionPixelSize(R.dimen.default_corner_radius)

    override fun loadImageUrl(imageUrl: String?, thumbnailUrl: String?, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .thumbnail(getThumbnail(context, thumbnailUrl))
            .transform(CenterCrop(), RoundedCorners(roundedCornerRadius))
            .into(imageView)
    }

    private fun getThumbnail(context: Context, thumbnailUrl: String?): RequestBuilder<Drawable> =
        Glide.with(context)
            .load(thumbnailUrl)
            .transform(CenterCrop(), RoundedCorners(roundedCornerRadius))
}
