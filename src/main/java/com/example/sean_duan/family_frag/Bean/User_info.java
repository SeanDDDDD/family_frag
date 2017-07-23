package com.example.sean_duan.family_frag.Bean;

import java.security.PrivateKey;

/**
 * Created by sean-duan on 2017/7/21.
 */

public class User_info {
    private int imageView;//用户头像
    private String infoName;//用户ID
    private String infoDate;//时间
    private String infoContent;//漂流瓶内容
    private String infoTitle ;//帖子或者漂流瓶的标题
    private boolean flag = false ; //定义状态字标记被选中与否
    private String adress ;//地点
    private String longitude;//经度
    private String latitude;//维度

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public User_info(){}

    public User_info(int imageView, String infoName, String infoDate, String infoContent, String infoTitle) {
        this.imageView = imageView;
        this.infoName = infoName;
        this.infoDate = infoDate;
        this.infoContent = infoContent;
        this.infoTitle = infoTitle ;
    }
    public  boolean getFlag(){
        return flag ;
    }
    public void setFlag(boolean is){
        this.flag = is ;
    }
    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }


}
