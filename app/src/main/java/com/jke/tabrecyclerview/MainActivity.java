package com.jke.tabrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ColumnTabAdapter tabAdapter;
//    private String[] columns = {"要闻","娱乐","NBA","科技","体育","电影","旅游","电竞"};
    private List<String> titles = new ArrayList<>();
    private List<String> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        titles.add("要闻");
        titles.add("娱乐");
        titles.add("NBA");
        titles.add("科技");
        titles.add("体育");
        titles.add("电影");
        titles.add("旅游");
        titles.add("电竞");
        titles.add("图片");
        titles.add("历史");
        titles.add("动漫");
        titles.add("国际");
        titles.add("佛学");
        tabAdapter =  new ColumnTabAdapter(this,getSupportFragmentManager(),titles);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);


        news.add("健康");
        news.add("美容");
        news.add("生活");
        news.add("星座");
        news.add("搏击");
        news.add("传媒");
        news.add("美食");
        news.add("足球");
        news.add("宠物");

        ImageView sort_img = (ImageView) findViewById(R.id.sort_img);
        sort_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SortColumnActivity.class);
                intent.putStringArrayListExtra("old_columns", (ArrayList<String>) titles);
                intent.putStringArrayListExtra("new_columns", (ArrayList<String>) news);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_top_in,0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventAllColumn event) {
        titles.clear();
        titles.addAll(event.getColums());
        tabAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageNewEvent(EventNewColumn event) {
        news.clear();
        news.addAll(event.getColums());
    }

}
