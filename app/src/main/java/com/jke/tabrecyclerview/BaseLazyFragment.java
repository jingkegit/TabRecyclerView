package com.jke.tabrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Describes 懒加载基类
 * Created by 荆柯 on 2017/5/19 10:22
 */
public  abstract class BaseLazyFragment extends Fragment
        implements View.OnClickListener{

    protected View mRootView;
    public Context mContext;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    private boolean isLazyLoad;

    public BaseLazyFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewParent mParent = mRootView.getParent();
            if (mParent != null && mParent instanceof ViewGroup) {
                ((ViewGroup) mParent).removeView(mRootView);
            }
            isPrepared = true;
            isFirst = false;
            return mRootView;
        }
        mRootView = initView(inflater,container,savedInstanceState);
        isPrepared = true;
        lazyLoad();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void lazyLoad() {
        if(isLazyLoad)
            onVisible();
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        Log.e("TAG",getClass().getName()+"->initData()");
        initData();
        isFirst = false;
        isLazyLoad = true;
    }

    // 每次可见时调用
    protected void onVisible() {
    }

    protected void onInvisible() {

    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState);

    public abstract void initData();


    @Override
    public void onClick(View v) {

    }
}
