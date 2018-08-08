package com.henshuai.meitameifa.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.henshuai.meitameifa.R;
import com.henshuai.meitameifa.utils.DensityUtils;


/**
 * 作者：张风捷特烈
 * 时间：2018/3/28:6:30
 * 邮箱：1981462002@qq.com
 * 说明：自定义RadioButton 属性：rb_width  rb_height
 */
public class MyRadioButton extends android.support.v7.widget.AppCompatRadioButton {

    private float mImg_width;
    private float mImg_height;

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        mImg_width = t.getDimension(R.styleable.MyRadioButton_rb_width, DensityUtils.dip2px(25, getContext()));
        mImg_height = t.getDimension(R.styleable.MyRadioButton_rb_height, DensityUtils.dip2px(25, getContext()));
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //让RadioButton的图标可调大小 属性：
        Drawable drawableLeft = this.getCompoundDrawables()[0];//获得文字左侧图片
        Drawable drawableTop = this.getCompoundDrawables()[1];//获得文字顶部图片
        Drawable drawableRight = this.getCompoundDrawables()[2];//获得文字右侧图片
        Drawable drawableBottom = this.getCompoundDrawables()[3];//获得文字底部图片
        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, (int) mImg_width, (int) mImg_height);
            this.setCompoundDrawables(drawableLeft, null, null, null);
        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, (int) mImg_width, (int) mImg_height);
            this.setCompoundDrawables(null, null, drawableRight, null);
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, (int) mImg_width, (int) mImg_height);
            this.setCompoundDrawables(null, drawableTop, null, null);
        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, (int) mImg_width, (int) mImg_height);
            this.setCompoundDrawables(null, null, null, drawableBottom);
        }
    }
}