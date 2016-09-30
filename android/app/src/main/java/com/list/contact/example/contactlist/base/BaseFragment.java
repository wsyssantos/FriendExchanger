package com.list.contact.example.contactlist.base;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;

import com.list.contact.example.contactlist.customview.DialogSpinner;
import com.list.contact.example.contactlist.util.DialogUtil;

/**
 * Created by wesley on 9/3/16.
 */
public class BaseFragment extends Fragment {

    protected void showDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        loadingDialog.show(getActivity().getFragmentManager(), "loading");
    }

    protected void hideDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        loadingDialog.dismiss();
    }

    protected void showErrorDialog(Throwable e) {
        DialogUtil.showError(getActivity(), e.getLocalizedMessage());
    }
}
