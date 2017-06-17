package com.jke.tabrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Describes 设置 RecyclerView 间隔
 * Created by 荆柯 on 2017/6/4.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int mSpace;

    public SpaceItemDecoration(int space)
    {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.top = 0;
        outRect.right = mSpace;
        outRect.bottom = 2*mSpace;
    }
}
