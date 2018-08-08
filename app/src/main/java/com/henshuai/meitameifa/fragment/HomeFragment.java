package com.henshuai.meitameifa.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.henshuai.meitameifa.R;
import com.henshuai.meitameifa.pages.BasePager;
import com.henshuai.meitameifa.pages.impl.FindPager;
import com.henshuai.meitameifa.pages.impl.HomePager;
import com.henshuai.meitameifa.pages.impl.MePager;
import com.henshuai.meitameifa.pages.impl.MessagePager;
import com.henshuai.meitameifa.pages.impl.OrderPager;
import com.henshuai.meitameifa.view.NoScrollViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * 主页面Fragment
 */
public class HomeFragment extends Fragment {

    public Activity mActivity;// 当做Context去使用, MainActivity
    public View mRootView;// fragment的根布局

    @ViewInject(R.id.vp_content)
    private NoScrollViewPager mViewPager;
    @ViewInject(R.id.rg_group)
    private RadioGroup rgGroup;

    private ArrayList<BasePager> mList;// 5个标签页的集合

    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();// 获取fragment所依赖的activity的对象
    }

    // 初始化fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = initViews();
        return mRootView;
    }

    // fragment所在的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    // 初始化布局
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        ViewUtils.inject(this, view); // 注入view和事件

//         mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
//         rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);

        return view;
    }

    // 初始化数据
    public void initData() {
        // 初始化5个标签页面对象
        mList = new ArrayList<BasePager>();
        mList.add(new HomePager(mActivity));
        mList.add(new FindPager(mActivity));
        mList.add(new MessagePager(mActivity));
        mList.add(new OrderPager(mActivity));
        mList.add(new MePager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        // 首页
                        // mViewPager.setCurrentItem(0);
                        mViewPager.setCurrentItem(0, false);// 去掉页面切换的动画
                        break;
                    case R.id.rb_find:
                        // 新闻中心
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_message:
                        // 智慧服务
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_order:
                        // 政务
                        mViewPager.setCurrentItem(3, false);
                        break;
                    case R.id.rb_me:
                        // 设置
                        mViewPager.setCurrentItem(4, false);
                        break;

                    default:
                        break;
                }
            }
        });
        // 监听ViewPager页面切换事件, 初始化当前页面数据
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mList.get(position);
                pager.initData();

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // 手动初始化第一个页面的数据
        mList.get(0).initData();

    }


    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获取当前页面对象
            BasePager pager = mList.get(position);

            // 此方法导致每次都提前加载下一页数据,浪费流量和性能, 不建议在此处初始化数据
            // pager.initData();//初始化布局(给帧布局添加布局对象), 以子类实现为准

            // 布局对象
            // pager.mRootView:当前页面的根布局
            container.addView(pager.mRootView);

            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }


}
