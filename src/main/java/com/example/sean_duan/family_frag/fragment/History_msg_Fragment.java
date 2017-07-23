package com.example.sean_duan.family_frag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sean_duan.family_frag.Adapter.UserMsgAdapter;
import com.example.sean_duan.family_frag.activity.MainActivity;
import com.example.sean_duan.family_frag.R;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/20.
 */

public class History_msg_Fragment extends Fragment {
    private View view ;
    private TabLayout tabMsg ;
    private ViewPager mViewPager ;
    private List<Fragment> fragments ;
    private MainActivity mainActivity ;
    private String[] title ;
    private List<String> titles ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_msg,container,false);
        //初始化数据
        initData();
        //初始化视图
        initView();
        //初始化适配器
        initAdapter();
        //绑定
        bind();
        return view;
    }

    private void bind() {
        tabMsg.setupWithViewPager(mViewPager);
    }

    private void initAdapter() {
        UserMsgAdapter adapter = new UserMsgAdapter(mainActivity.getSupportFragmentManager(),fragments,titles);
    }

    private void initData() {
        title = new String[]{"漂流瓶","帖子"};
        for (int i = 0; i < title.length; i++) {
            titles.add(title[i]);
        }
    }


    private void initView() {
        tabMsg = (TabLayout) view.findViewById(R.id.tab_msg);
        mainActivity =(MainActivity) getActivity();
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
