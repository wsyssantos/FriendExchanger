package com.list.contact.example.contactlist.contactlist;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.list.contact.example.contactlist.R;

/**
 * Created by wesley on 9/3/16.
 */
public class ContactListCardView extends CardView {

    private Integer viewFullHeight = 1050;

    public ContactListCardView(Context context) {
        super(context);
    }

    public ContactListCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactListCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewGroup.LayoutParams layoutParams = ContactListCardView.this.getLayoutParams();
                layoutParams.height = (int) ContactListCardView.this.getContext().getResources().getDimension(R.dimen
                        .contact_list_card_min_height);
                ContactListCardView.this.setLayoutParams(layoutParams);

                return true;
            }
        });
    }

    public void toggleCardViewHeight(final ContactListViewHolder holder) {
        int minViewHeight = (int) getContext().getResources().getDimension(R.dimen.contact_list_card_min_height);

        if(this.getHeight() == minViewHeight) {
            ValueAnimator anim = ValueAnimator.ofInt(this.getMeasuredHeightAndState(), viewFullHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = ContactListCardView.this.getLayoutParams();
                    layoutParams.height = val;
                    ContactListCardView.this.setLayoutParams(layoutParams);
                    holder.hideViewContent();
                }
            });
            anim.start();
        } else {
            ValueAnimator anim = ValueAnimator.ofInt(this.getMeasuredHeightAndState(), minViewHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = ContactListCardView.this.getLayoutParams();
                    layoutParams.height = val;
                    ContactListCardView.this.setLayoutParams(layoutParams);
                    holder.showViewContent();
                }
            });
            anim.start();
        }
    }
}
