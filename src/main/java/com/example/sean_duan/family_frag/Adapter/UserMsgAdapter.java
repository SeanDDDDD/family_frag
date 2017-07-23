package com.example.sean_duan.family_frag.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/20.
 */

public class UserMsgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList ;
    private List<String> titles ;
    public UserMsgAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList = fragmentList ;
        this.titles = titles ;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//更新滚动菜单的文字 不然无法显示
        return titles.get(position);
    }
}

