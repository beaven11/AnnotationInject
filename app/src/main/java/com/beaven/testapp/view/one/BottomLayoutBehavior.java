package com.beaven.testapp.view.one;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 创建时间: 2018/03/29 11:27<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/29 11:27<br>
 * 描述:
 */
public class BottomLayoutBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private static final String TAG = "BottomLayoutBehavior";

    private boolean isScroller = true;

    public BottomLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
            @NonNull LinearLayout child, @NonNull View directTargetChild, @NonNull View target,
            int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
            @NonNull final LinearLayout child, @NonNull View target, int dxConsumed, int dyConsumed,
            int dxUnconsumed, int dyUnconsumed, int type) {
        if (!isScroller) {
            return;
        }
        isScroller = false;
        if (dyConsumed > 0) {
            offsetChild(child, child.getHeight());
        } else {
            offsetChild(child, 0);
        }
    }

    private void offsetChild(View view, float offsetEnd) {
        ObjectAnimator animator =
                ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), offsetEnd)
                        .setDuration(500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isScroller = true;
            }
        });
        animator.start();
    }
}
