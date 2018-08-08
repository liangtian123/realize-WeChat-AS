package com.henshuai.meitameifa;

//import com.meituanmeifa.com.henshuai.meitameifa.fragment.HomeFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.henshuai.meitameifa.fragment.HomeFragment;

public class HomeActivity extends FragmentActivity {
    private static final String TAG_CONTENT = "TAG_CONTENT";
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragment();

    }

    private void initFragment() {
        //获取fragment管理器
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//开始一个事务
        //使用fragment替换现有布局. 参1:当前布局的id;参2:要替换的fragment
        transaction.replace(R.id.activity_home_xml, new HomeFragment(), TAG_CONTENT);//参3:打一个标记,方便以后找到该Fragment对象
        transaction.commit();//提交事务

        //通过tag找到fragment
        //ContentFragment com.henshuai.meitameifa.fragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);

    }
    //实现双击返回退出应用程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
