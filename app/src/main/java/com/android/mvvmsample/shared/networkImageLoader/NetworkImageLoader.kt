package com.android.mvvmsample.shared.networkImageLoader

import android.widget.ImageView

interface NetworkImageLoader {

    fun loadImageUrl(imageUrl: String?, thumbnailUrl: String?, imageView: ImageView)
}
