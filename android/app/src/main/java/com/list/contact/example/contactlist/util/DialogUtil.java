package com.list.contact.example.contactlist.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.list.contact.example.contactlist.R;

/**
 * Created by wesley on 9/3/16.
 */
public class DialogUtil {
    public static void showError(Context context, String... params) {
        String errorMessage = String.format(context.getString(R.string.error), params);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(errorMessage)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
