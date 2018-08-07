package com.henshuai.meitameifa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.henshuai.meitameifa.utils.PrefUtils;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去title，新版activity继承AppCompatActivity，不能用老方法，区别（经验证：目前就发现去title方式改变了，其他无任何改变）
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去状态栏，和旧版一样
//        //旧版继承自activity去除title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去掉Activity上面的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        enterHome();
    }

    private void enterHome() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //判断有没有展示过引导页
                boolean isGuideShow = PrefUtils.getBoolean(
                        getApplicationContext(), "is_guide_show", false);
                if (!isGuideShow) {
                    //跳到新手引导页
                    startActivity(new Intent(getApplicationContext(),
                            GuideActivity.class));
                } else {
                    //跳到主页面
                    startActivity(new Intent(getApplicationContext(),
                            HomeActivity.class));
                }
                finish();
            }

            ;
        }.start();
    }

    //禁用回退键
    @Override
    public void onBackPressed() {
    //        super.onBackPressed();//要去掉这句，否则会结束当前Activity，无法起到屏蔽的作用
        //处理自己的逻辑
    }

}
