package com.jke.tabrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Describes 栏目子页面
 * Created by 荆柯 on 2017/6/2.
 */

public class ColumnFragment extends BaseLazyFragment {

    private String title;
    private TextView content_tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_column,container,false);
        content_tv = (TextView) view.findViewById(R.id.content_tv);
        content_tv.setText(title+"---栏目内容");
        return view;
    }

    @Override
    public void initData() {
        content_tv.setText(title+"---栏目内容");
    }

    public String getTitle(){
        return title;
    }
}
