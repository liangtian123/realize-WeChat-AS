package com.henshuai.meitameifa.pages;

import android.app.Activity;
import android.view.View;

public class BasePager {

    public View mRootView;//当前页面的根布局

    public Activity mActivity;

    public BasePager(Activity activity) {
        mActivity = activity;
        //在页面对象创建时就初始化了布局
        mRootView = initViews();
    }

    //初始化布局
    public View initViews() {

        return null;
    }


    //初始化数据
    public void initData() {

    }
}
