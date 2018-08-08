package com.henshuai.meitameifa;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.henshuai.meitameifa.utils.PrefUtils;
import com.viewpagerindicator.CirclePageIndicator;

public class GuideActivity extends Activity {

    // 图片id集合
    private int[] mImageIds = new int[]{R.drawable.guide_1,
            R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> mImageViews;
    private ViewPager mGuideViewPage;
    private CirclePageIndicator mIndicator;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initView() {
        mGuideViewPage = findViewById(R.id.vp_guide);
        btnStart = findViewById(R.id.btn_start);

    }


    private void initData() {
        // 初始化三张图片的ImageView
        mImageViews = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViews.add(view);
        }
        mGuideViewPage.setAdapter(new GuideAdapter());

        // 把ViewPage和指示器关联
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mGuideViewPage);

        // 监听ViewPager滑动事件,最后一页出现开始体验按钮，由于关联了Indicator，所以得把监听事件设置给它
        mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == mImageIds.length - 1) {
                    // 最后一个页面显示开始体验按钮
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnStart.setVisibility(View.GONE);
                }
            }

            // 监听滑动事件
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                System.out.println("当前位置:" + position + ";偏移百分比:"
                        + positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // 设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        // 开始体验按钮点击
        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 在sp中记录访问过引导页的状态
                PrefUtils.putBoolean(getApplicationContext(), "is_guide_show",
                        true);

                // 跳到主页面
                startActivity(new Intent(getApplicationContext(),
                        HomeActivity.class));
                finish();
            }
        });
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 初始化布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // ImageView view = new ImageView(getApplicationContext());
            ImageView view = mImageViews.get(position);

            container.addView(view);

            return view;
        }

        // 销毁布局
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
