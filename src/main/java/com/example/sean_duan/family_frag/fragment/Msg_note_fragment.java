package com.example.sean_duan.family_frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sean-duan on 2017/7/20.
 */
//历史记录中帖子记录界面
public class Msg_note_fragment extends Fragment implements OnResponseListener<String>{
    private View view ;
    private RecyclerView recyclerView ;
    private LinearLayoutManager linearLayoutManager;
    private History_msg_Activity historyMsgActivity ;
    private MyRecyclerAdapter myRecyclerAdapter ;
    private List<User_info> mlist = new ArrayList<>();
    private Bundle bunle = new Bundle() ;
    private RequestQueue requestQueue ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msg_note,container,false);
        historyMsgActivity = (History_msg_Activity) getActivity();
        //初始化数据
        initData();
        //初始化视图
        initView();
        //初始化适配器
        initAdapter();
        //监听事件
        initListner();
        //从服务器中请求数据
        getDatabyServet();
        return view;
    }
    private void getDatabyServet() {//从服务器端获取数据
        String url = DataNetHome.urlGetTieZiHistory;//获得便签历史纪录
        Request<String> request = new StringRequest(url, RequestMethod.GET);//传递一个get请求
        request.add("userID","用户id");//传递一个用户ID
        requestQueue.add(0,request,this);//把这个请求放在请求队列中
    }

    private void initData() {
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
        mlist.add(new User_info(R.drawable.ic_dashboard_black_24dp,"fdhdfhdfhd","fdhdfdd","dfhdfhd","dfdhfdhdf"));
    }

    private void initAdapter() {
        myRecyclerAdapter  = new MyRecyclerAdapter(mlist,historyMsgActivity);
        linearLayoutManager = new LinearLayoutManager(historyMsgActivity) ;
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(historyMsgActivity,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myRecyclerAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_note);
        requestQueue = NoHttp.newRequestQueue();

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

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        if(what==1){//what只是一个标记
            List<User_info> newList = new ArrayList<>();
            newList = JsonData.getTieziHistory(response.get().toString());//把成功获得的请求数据解析后返回一个newList
            if(newList!=null){
                myRecyclerAdapter.changeList(newList);
            }
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    @Override
    public void onFinish(int what) {

    }
}
