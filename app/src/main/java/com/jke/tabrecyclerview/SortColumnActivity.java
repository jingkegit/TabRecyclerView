package com.jke.tabrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes 排序栏目
 * Created by 荆柯 on 2017/6/4.
 */

public class SortColumnActivity extends AppCompatActivity implements View.OnClickListener,
        ColumnRecyclerAdapter.onItemClickListener{

    private RecyclerView old_lv;
    private RecyclerView new_lv;
    private ColumnRecyclerAdapter old_adapter;
    private ColumnRecyclerAdapter new_adapter;
    private List<String> olds = new ArrayList<>();
    private List<String> news = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_column);
        findViewById(R.id.close_img).setOnClickListener(this);
        old_lv = (RecyclerView) findViewById(R.id.old_column_rv);
        new_lv = (RecyclerView) findViewById(R.id.new_column_rv);
        // 已添加栏目
        olds = getIntent().getStringArrayListExtra("old_columns");
        news = getIntent().getStringArrayListExtra("new_columns");
        old_lv.setLayoutManager(new GridLayoutManager(this,4));
        old_adapter = new ColumnRecyclerAdapter(olds,this,true);
        old_adapter.setOnItemClickListener(this);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(old_adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(old_lv);
        old_lv.setAdapter(old_adapter);
        old_lv.addItemDecoration(new SpaceItemDecoration(10));

        // 新栏目
        new_lv.setLayoutManager(new GridLayoutManager(this,4));
        new_adapter = new ColumnRecyclerAdapter(news,this,false);
        new_adapter.setOnItemClickListener(this);
        new_lv.setAdapter(new_adapter);
        new_lv.addItemDecoration(new SpaceItemDecoration(10));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_img:
                finishSelf();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finishSelf();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishSelf() {
        if(olds != null && olds.size() > 0){
            EventAllColumn event = new EventAllColumn(olds);
            EventBus.getDefault().post(event);
        }
        if(news != null){
            EventNewColumn event = new EventNewColumn(news);
            EventBus.getDefault().post(event);
        }
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0,R.anim.anim_top_out);
    }

    @Override
    public void remove(int pos) {
        String title = olds.get(pos);
        old_adapter.removeData(pos);
        new_adapter.addData(title,0);
    }

    @Override
    public void add(int pos) {
        String title = news.get(pos);
        new_adapter.removeData(pos);
        old_adapter.addData(title,olds.size());
    }
}
