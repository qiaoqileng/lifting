package com.qql.lifting.impl;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qql.lifting.option.GlideOptions;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    private static final long serialVersionUID = 4562995479081901823L;

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).asBitmap().load(path).apply(GlideOptions.defaultOption()).into(imageView);
    }
}
