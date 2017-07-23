package com.example.sean_duan.family_frag.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.sean_duan.family_frag.Adapter.UserMsgAdapter;
import com.example.sean_duan.family_frag.Bean.MySerizal;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.fragment.Msg_bottle_fragment;
import com.example.sean_duan.family_frag.fragment.Msg_note_fragment;

import java.util.ArrayList;
import java.util.List;
//历史消息弹出的活动
public class History_msg_Activity extends AppCompatActivity {
    private View view ;
    private TabLayout tabMsg ;
    public ViewPager mViewPager ;
    private List<Fragment> fragments ;
    private String[] title ;
    private List<String> titles;
    public BottomNavigationView bottomNavigationView ;
    private boolean isstartchoice = false ;

    public boolean isstartchoice() {
        return isstartchoice;
    }

    public void setIsstartchoice(boolean isstartchoice) {
        this.isstartchoice = isstartchoice;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history_msg:

                    return true;
                case R.id.back_bottle:
                    Intent intent = new Intent(History_msg_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.user_interface:
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_msg);
        //初始化数据
        initData();
        //初始化视图
        initView();
        //初始化适配器
        initAdapter();
        //绑定
        bind();
        //初始化监听器
        initListener();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(isstartchoice()){


                    MySerizal.ischoice = false ;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    setIsstartchoice(false);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(isstartchoice()){
                    MySerizal.ischoice = false ;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    setIsstartchoice(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(isstartchoice()){
                    MySerizal.ischoice = false ;
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    setIsstartchoice(false);
                }
            }
        });
    }

    private void bind() {
        tabMsg.setupWithViewPager(mViewPager);
    }

    private void initAdapter() {
        UserMsgAdapter adapter = new UserMsgAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPager.setAdapter(adapter);

        addTab();
    }

    private void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>() ;

        title = new String[]{"漂流瓶","帖子"};
        for (int i = 0; i < title.length; i++) {
            titles.add(title[i]);
        }

        fragments.add(new Msg_bottle_fragment());
        fragments.add(new Msg_note_fragment());
    }


    private void initView() {
        tabMsg = (TabLayout) findViewById(R.id.tab_msg);
        mViewPager = (ViewPager) findViewById(R.id.vp_msg_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_history) ;
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //添加Tab到TabLayout布局中
        addTab();
    }

    private void addTab() {
        for (int i = 0; i < title.length; i++) {
            Log.e("dffff","lalallalala");
            tabMsg.addTab(tabMsg.newTab().setText(title[i]));
        }
    }
}
