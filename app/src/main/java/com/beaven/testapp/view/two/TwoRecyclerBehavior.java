package com.beaven.testapp.view.two;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import java.util.List;

/**
 * 创建时间: 2018/03/29 15:10<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/29 15:10<br>
 * 描述:
 */
public class TwoRecyclerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    private static final String TAG = "TwoRecyclerBehavior";

    public TwoRecyclerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof ImageView;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child,
            int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        List<View> viewList = parent.getDependencies(child);
        for (View view : viewList) {
            if (view instanceof ImageView) {
                int imageViewHeight = view.getHeight();
                child.layout(0, imageViewHeight, child.getRight(), child.getBottom());
            }
        }
        return true;
    }
}
