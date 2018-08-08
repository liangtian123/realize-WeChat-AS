package com.henshuai.meitameifa.pages.impl;

import android.app.Activity;
import android.view.View;

import com.henshuai.meitameifa.R;
import com.henshuai.meitameifa.pages.BasePager;

public class OrderPager extends BasePager {


    public OrderPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.pages_order, null);

        return view;
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        super.initData();
    }

}
