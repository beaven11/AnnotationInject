package com.beaven.testapp.view.two;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 创建时间: 2018/03/29 15:27<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/29 15:27<br>
 * 描述:
 */
public class TwoImageBehavior extends CoordinatorLayout.Behavior<ImageView> {

    private static final String TAG = "TwoImageBehavior";

    private int imageMaxHeight = 0;
    private int imageMinHeight = 0;

    //0 无操作 1，缩放操作，2，校正操作
    private int layoutFlags = 0;

    public TwoImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
            @NonNull ImageView child, @NonNull View directTargetChild, @NonNull View target,
            int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
            @NonNull ImageView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed,
            int type) {
        if (target.canScrollVertically(-1)) {
            return;
        }
        if (imageMaxHeight == 0 && imageMinHeight == 0) {
            imageMaxHeight = child.getHeight();
            imageMinHeight = imageMaxHeight / 3;
        }
        int childBottom = child.getBottom() - dy;
        int height = childBottom - child.getTop();
        if (height > imageMinHeight && height < imageMaxHeight) {
            Log.i(TAG, "onNestedPreScroll: 进行了操作--" + height);
            layout(child, child.getTop(), childBottom);
            layout(target, target.getTop() - dy, target.getBottom());
            consumed[1] = dy;
            layoutFlags = 1;
        } else if (layoutFlags == 1) {
            Log.i(TAG, "onNestedPreScroll: 校正操作");
            if (dy > 0) {
                layout(child, child.getTop(), child.getTop() + imageMinHeight);
            } else {
                layout(child, child.getTop(), child.getTop() + imageMaxHeight);
            }
            layout(target, child.getBottom(), target.getBottom());
            layoutFlags = 2;
        }
    }

    private void layout(View view, int top, int bottom) {
        view.layout(view.getLeft(), top, view.getRight(), bottom);
    }
}
