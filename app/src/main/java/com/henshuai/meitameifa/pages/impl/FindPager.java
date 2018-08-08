package com.henshuai.meitameifa.pages.impl;


import android.app.Activity;
import android.view.View;

import com.henshuai.meitameifa.R;
import com.henshuai.meitameifa.pages.BasePager;

public class FindPager extends BasePager {


    public FindPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initViews() {

        View view = View.inflate(mActivity, R.layout.pages_find, null);

        return view;
    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub
        super.initData();
    }


}
