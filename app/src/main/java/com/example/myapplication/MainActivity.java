package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //界面内容
    private FrameLayout mBodyLayout;
    //底部按钮栏
    public LinearLayout mBottomLayout;
    //底部按钮
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myInfo;
    //标题栏
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
    }

    /**
     * 设置初始选择
     */
    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }

    /**
     * 为每个按钮设置监听方法
     */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     * 获取底部导航栏上的控件
     */
    private void initBottomBar() {
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCourseBtn = (RelativeLayout) findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = (RelativeLayout) findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = (RelativeLayout) findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        iv_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        iv_exercises = (ImageView) findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
    }

    /**
     * 获取界面上的UI控件
     */
    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);//不显示返回按钮
        initBodyLayout();

    }

    /**
     * 界面内容
     */
    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    /**
     * 控件的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //课程的点击事件
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            //习题的点击事件
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            //我的点击事件
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            default:
                break;
        }
    }

    /**
     * 显示对应的页面
     * @param i
     */
    private void selectDisplayView(int i) {
        removeAllView();
        createView(i);
        setSelectedStatus(i);
    }

    /**
     * 设置底部按钮选中状态
     * @param i
     */
    private void setSelectedStatus(int i) {
        switch (i){
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.mipmap.yellow1);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.mipmap.yellow1);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("习题");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.mipmap.yellow1);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("我");
                break;
        }
    }



    /**
     * 选择视图
     * @param viewIndex
     */
    private void createView(int viewIndex) {
        switch (viewIndex){
            case 0:
                //TODO:课程界面
                break;
            case 1:
                //习题界面
                break;
            case 2:
                //我的界面
                break;
        }
    }

    /**
     * 移除不需要的视图
     */
    private void removeAllView() {
        for (int i = 0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));

        iv_course.setImageResource(R.drawable.ic_launcher_background);
        iv_exercises.setImageResource(R.drawable.ic_launcher_background);
        iv_myInfo.setImageResource(R.drawable.ic_launcher_background);

        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    protected long exitTime;//记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){//按返回键、按下动作
            if ((System.currentTimeMillis() - exitTime) >2000){//导入java.lang的包
                Toast.makeText(this,"再按一次E-Learning App",Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            }else {
                MainActivity.this.finish();
                if (readLoginStatus()){
                    //如果退出此应用时是登录状态，则需要清除登录状态，同时需清除登录时的用户名
                    clearLoginStatus();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 清除SharedPreferences中的登录状态
     */
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putBoolean("isLogin", false);//清除登录状态
        editor.putString("loginUserName", "");//清除登录时的用户名
        editor.commit();//提交修改
    }

    /**
     * 获取SharedPreferences中的登录状态
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }
}
