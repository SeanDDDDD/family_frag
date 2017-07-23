package com.example.sean_duan.family_frag;

import com.example.sean_duan.family_frag.Bean.DataNetHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean-duan on 2017/7/22.
 */

public class JsonData {
    private DataNetHome dataNetHome;
    public JsonData() {
        dataNetHome = new DataNetHome();
    }
    public static List<User_info> getBottleHistory(String data){//解析获得的漂流瓶历史信息
        List<User_info> mlist = new ArrayList<>();
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));
        return mlist;
    }
    public static List<User_info> getTieziHistory(String data){//解析获得的帖子历史记录
        List<User_info> mlist = new ArrayList<>();

        return mlist;
    }
    public static User_info getBottle(String data){//解析获得的瓶子信息
        User_info user_info = new User_info();

        return user_info;
    }
}
