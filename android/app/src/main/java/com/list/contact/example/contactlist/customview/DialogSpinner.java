package com.list.contact.example.contactlist.customview;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.list.contact.example.contactlist.R;

/**
 * Created by wesley on 9/3/16.
 */
public class DialogSpinner extends DialogFragment {

    private static DialogSpinner instance;

    public DialogSpinner() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);

        return dialog;
    }

    public static DialogSpinner getInstance() {
        if(instance == null) {
            instance = new DialogSpinner();
        }
        return instance;
    }
}

