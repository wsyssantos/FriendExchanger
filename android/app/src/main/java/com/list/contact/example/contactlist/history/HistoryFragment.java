package com.list.contact.example.contactlist.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.list.contact.example.contactlist.R;
import com.list.contact.example.contactlist.base.BaseFragment;
import com.list.contact.example.contactlist.model.Transfer;
import com.list.contact.example.contactlist.storage.UserStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/3/16.
 */
public class HistoryFragment extends BaseFragment implements HistoryContract.View {

    private HistoryContract.Presenter presenter;
    private HistoryListAdapter historyListAdapter;

    @BindView(R.id.listViewTransfers) ListViewCompat listViewTransfers;
    @BindView(R.id.emptyResultMessage) RelativeLayout emptyResultMessage;
    @BindView(R.id.imgNoResult) ImageView imgNoResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.history_list_frag, container, false);
        ButterKnife.bind(this, rootView);

        configurePresenter();
        return rootView;
    }

    private void configurePresenter() {
        setPresenter(new HistoryPresenter(this));
        UserStorage userStorage = UserStorage.getInstance();
        presenter.getHistory(userStorage.getToken(getActivity()));
    }

    @Override
    public void historyListReceived(List<Transfer> transferList) {
        if(transferList != null && transferList.size() > 0) {
            historyListAdapter = new HistoryListAdapter(transferList, getActivity());
            listViewTransfers.setAdapter(historyListAdapter);
            emptyResultMessage.setVisibility(View.INVISIBLE);
            listViewTransfers.setVisibility(View.VISIBLE);
        } else {
            listViewTransfers.setVisibility(View.INVISIBLE);
            Animation growAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.grow_center_anim);
            imgNoResult.startAnimation(growAnim);
            emptyResultMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void errorReceived(Throwable e) {
        showErrorDialog(e);
    }
}
