package com.list.contact.example.contactlist.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by wesley on 9/4/16.
 */
public class BitmapCache {
    private static LruCache<Integer, Bitmap> mMemoryCache;

    static {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<Integer, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static void addBitmapToMemoryCache(Integer key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(Integer key) {
        return mMemoryCache.get(key);
    }
}
