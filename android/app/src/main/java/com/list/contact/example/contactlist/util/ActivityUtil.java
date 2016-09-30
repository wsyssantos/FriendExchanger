package com.list.contact.example.contactlist.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by wesley on 9/3/16.
 */
public class ActivityUtil {

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

}