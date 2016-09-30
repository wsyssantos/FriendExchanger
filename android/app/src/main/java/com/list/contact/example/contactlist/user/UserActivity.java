package com.list.contact.example.contactlist.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.base.BaseActivity;
import com.list.contact.example.contactlist.contactlist.ContactListActivity;
import com.list.contact.example.contactlist.history.HistoryActivity;
import com.list.contact.example.contactlist.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class UserActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_act);
        ButterKnife.bind(UserActivity.this);

        configureToolbar();
        configureFragment();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.title_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history : {
                int[] location = new int[] { 1022, 63 };
                Intent contactListIntent = new Intent(UserActivity.this, HistoryActivity.class);

                contactListIntent.putExtra(BaseActivity.REVEAL_X, location[0]);
                contactListIntent.putExtra(BaseActivity.REVEAL_Y, location[1]);

                startActivity(contactListIntent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void configureFragment() {
        UserFragment userFragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (userFragment == null) {
            userFragment = new UserFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), userFragment, R.id.contentFrame);
        }
    }
}
