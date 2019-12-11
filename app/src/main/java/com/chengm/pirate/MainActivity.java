package com.chengm.pirate;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : ChenWJ
 * date : 2019/12/7 10:43
 * description :
 */
public class MainActivity extends Activity {

    private RecyclerView mRvBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mRvBanner = findViewById(R.id.recycler_view);
        BannerAdapter adapter = new BannerAdapter(this, getBannerData());
        mRvBanner.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRvBanner.setLayoutManager(manager);

        PagerSnapHelper bannerPageSnapHelper = new PagerSnapHelper();
        bannerPageSnapHelper.attachToRecyclerView(mRvBanner);
    }

    // 数据
    private List<Banner> getBannerData() {
        List<Banner> datas = new ArrayList<>();

        Banner banner = new Banner();
        banner.setResId(R.mipmap.image01);
        datas.add(banner);

        banner = new Banner();
        banner.setResId(R.mipmap.image02);
        datas.add(banner);

        banner = new Banner();
        banner.setResId(R.mipmap.image03);
        datas.add(banner);

        banner = new Banner();
        banner.setResId(R.mipmap.image04);
        datas.add(banner);

        return datas;
    }
}
