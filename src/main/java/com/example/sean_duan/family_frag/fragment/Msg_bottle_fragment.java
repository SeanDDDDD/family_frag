package com.example.sean_duan.family_frag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sean_duan.family_frag.Adapter.MyRecyclerAdapter;
import com.example.sean_duan.family_frag.Bean.DataNetHome;
import com.example.sean_duan.family_frag.Bean.MySerizal;
import com.example.sean_duan.family_frag.activity.History_msg_Activity;
import com.example.sean_duan.family_frag.JsonParse.JsonData;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.Bean.User_info;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean-duan on 2017/7/20.
 */
//历史记录中漂流瓶记录的界面
public class Msg_bottle_fragment extends Fragment implements OnResponseListener<String>{
    private View view ;
    private RecyclerView recyclerView ;
    private LinearLayoutManager linearLayoutManager;
    private History_msg_Activity historyMsgActivity ;
    private MyRecyclerAdapter myRecyclerAdapter ;
    private List<User_info> mlist = new ArrayList<>();
    private Bundle bunle = new Bundle();
    private RequestQueue requestQueue ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msg_bottle,container,false);
        historyMsgActivity = (History_msg_Activity) getActivity();
        //初始化数据
        initData();
        //初始化视图
        initView();
        //初始化适配器
        initAdapter();
        //初始化监听器
        initListner();
        //从服务器中请求数据
        getDatabyServet();
        return view;
    }

    private void getDatabyServet() {//从服务器端获取数据
        String url = DataNetHome.urlGetBottleHistory;//获得漂流瓶历史消息
        Request<String> request = new StringRequest(url, RequestMethod.GET);//传递一个get请求
        request.add("userID","用户id");//传递一个用户ID
        requestQueue.add(0,request,this);//把这个请求放在请求队列中
    }

    private void initListner() {
        myRecyclerAdapter.setRecyclerViewOnItemClickListener(new MyRecyclerAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
              //  Toast.makeText(historyMsgActivity, "点击成功", Toast.LENGTH_SHORT).show();
                if(MySerizal.ischoice == true){
                    int i = bunle.getInt("ischoice");
                    mlist.get(position).setFlag(false);
                    myRecyclerAdapter.notifyItemChanged(i);
                    MySerizal.ischoice = false ;
                    bunle.clear();
                }else{
                    Toast.makeText(historyMsgActivity, "进去", Toast.LENGTH_SHORT).show();
                    //跳到getbottlefragment界面
                }
            }
        });
        myRecyclerAdapter.setOnItemLongClickListener(new MyRecyclerAdapter.RecyclerViewOnItemLongClickListener() {
            @Override
            public boolean onItemLongClickListener(View view, int position) {
                Toast.makeText(historyMsgActivity, "点击长按成功", Toast.LENGTH_SHORT).show();

                if(MySerizal.ischoice == true){
                    int i = bunle.getInt("ischoice");
                    mlist.get(position).setFlag(false);
                    myRecyclerAdapter.notifyItemChanged(i);
                    MySerizal.ischoice = false ;
                }else{
                    mlist.get(position).setFlag(true);
                    myRecyclerAdapter.notifyItemChanged(position);
                    MySerizal.ischoice = true ;
                    bunle.putInt("ischoice",position);
                }
                return true;
            }
        });

    }

    private void initData() {
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"ddd","dddddd","dddddd","dddddd"));


    }

    private void initAdapter() {
        myRecyclerAdapter  = new MyRecyclerAdapter(mlist,historyMsgActivity);
        linearLayoutManager = new LinearLayoutManager(historyMsgActivity) ;
        recyclerView.setLayoutManager(linearLayoutManager);//给recyclerView添加布局管理器
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(historyMsgActivity,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myRecyclerAdapter);

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_bottle);
        requestQueue = NoHttp.newRequestQueue();

    }
//实现接口方法
    @Override
    public void onStart(int what) {//开始请求

    }

    @Override
    public void onSucceed(int what, Response<String> response) {//请求成功
        Log.e("succeed","ldlldkdjfhfhfhfhff");
        if(what==1){//what只是一个标记
            List<User_info> newList = new ArrayList<>();
            newList = JsonData.getBottleHistory(response.get().toString());//把成功获得的请求数据解析后返回一个newList
            if(newList!=null){
               myRecyclerAdapter.changeList(newList);
            }
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {//请求失败
        Log.e("failed","ldlldkdjfhfhfhfhff");
    }

    @Override
    public void onFinish(int what) {//请求完成

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();//销毁后摧毁请求队列
    }
}
