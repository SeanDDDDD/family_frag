package com.example.sean_duan.family_frag.JsonParse;

import android.os.Bundle;

import com.example.sean_duan.family_frag.Bean.DataNetHome;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.Bean.User_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                User_info user_info = new User_info();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                user_info.setInfoName(jsonObject1.getString("userID"));
                user_info.setInfoTitle(jsonObject1.getString("title"));
                user_info.setInfoContent(jsonObject1.getString("content"));
                user_info.setAdress(jsonObject1.getString("adress"));
                mlist.add(user_info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mlist;
    }

    public static List<User_info> getTieziHistory(String data){//解析获得的帖子历史记录
        List<User_info> mlist = new ArrayList<>();

        return mlist;
    }
    public static Bundle getBottle(String data){//解析获得的瓶子信息
        Bundle bundle = new Bundle();
        try {

            JSONObject jsonObject = new JSONObject(data);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            bundle.putString("throwID",jsonObject1.getString("throwID"));
            bundle.putString("title",jsonObject1.getString("title"));
            bundle.putString("content",jsonObject1.getString("content"));
            bundle.putString("time",jsonObject1.getString("time"));
            bundle.putString("adress",jsonObject1.getString("adress"));
            bundle.putString("latitude",jsonObject1.getString("latitude"));
            bundle.putString("longitude",jsonObject1.getString("longitude"));
            bundle.putInt("type",jsonObject1.getInt("type"));
            bundle.putInt("visual",jsonObject1.getInt("visual"));



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bundle ;
    }
    public static List<User_info> getTieziMsg(String data){
        List<User_info> mlist = new ArrayList<>();

        return mlist ;
    }
}
