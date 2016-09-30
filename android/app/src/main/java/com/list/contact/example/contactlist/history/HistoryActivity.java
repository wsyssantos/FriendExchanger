package com.list.contact.example.contactlist.history;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.base.BaseActivity;
import com.list.contact.example.contactlist.contactlist.ContactListFragment;
import com.list.contact.example.contactlist.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class HistoryActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rootViewLayout) LinearLayout rootViewLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_act);
        ButterKnife.bind(HistoryActivity.this);

        configureToolbar();
        configureFragment();

        showRevealEffect(savedInstanceState, rootViewLayout);
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.title_history);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void configureFragment() {
        HistoryFragment userFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (userFragment == null) {
            userFragment = new HistoryFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), userFragment, R.id.contentFrame);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
