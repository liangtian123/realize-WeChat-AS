package com.henshuai.meitameifa.utils;

import android.content.Context;

public class DensityUtils {

    public static int dip2px(float dp, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    public static float px2dip(int px, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dip = px / density;
        return dip;
    }

}
