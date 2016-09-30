package com.list.contact.example.contactlist.util;

import android.content.Context;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by wesley on 9/16/16.
 */
public class PicassoCache {
    private static Picasso picassoInstance = null;

    public PicassoCache(Context context) {
        Downloader downloader = new OkHttpDownloader(context, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(downloader);
        picassoInstance = builder.build();
    }

    public static Picasso getInstance(Context context) {
        if(picassoInstance == null) {
            new PicassoCache(context);
        }
        return picassoInstance;
    }
}
