package com.example.sean_duan.family_frag.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sean_duan.family_frag.Bean.DataNetHome;
import com.example.sean_duan.family_frag.JsonParse.JsonData;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.Bean.User_info;
import com.example.sean_duan.family_frag.activity.MainActivity;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

//从地图上点击漂流瓶弹出的界面
public class Get_Bottle_Fragment extends Fragment implements OnResponseListener<String> {
    private Button msg_btn_exit;//消息退出
    private Button msg_btn_back;//漂流瓶扔回
    private Button msg_btn_destory;//漂流瓶销毁
    private Button msg_btn_recall ;//漂流瓶回复
    private View view ;
    private MainActivity mainActivity ;
    private RequestQueue requestQueue ;
    private TextView content ;//显示内容
    private TextView bottleType ;//漂流瓶类别
//    // TODO: Rename and change types and number of parameters
    public static Get_Bottle_Fragment newInstance(String param1) {
        Get_Bottle_Fragment fragment = new Get_Bottle_Fragment();
        Bundle args = new Bundle();
        args.putString("lala",param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView() {
        msg_btn_exit = (Button) view.findViewById(R.id.button_exit);
        msg_btn_back = (Button) view.findViewById(R.id.bottle_back);
        msg_btn_destory = (Button) view.findViewById(R.id.bottle_destory);
        msg_btn_recall = (Button) view.findViewById(R.id.bottle_recall);
        content = (TextView) view.findViewById(R.id.textView_content);
        bottleType = (TextView) view.findViewById(R.id.textView_stye_bottle);
        mainActivity = (MainActivity) getActivity();
        requestQueue = NoHttp.newRequestQueue() ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_get_bottle_msg, container, false);
        //初始化视图
        initView();
        //初始化监听器
        initListenr();
        //从服务器中请求数据
        getDatabyServet();
        return view ;
    }

    private void getDatabyServet() {
        String url = DataNetHome.urlGetBottle;//获得漂流瓶历史消息
        Request<String> request = new StringRequest(url, RequestMethod.GET);//传递一个get请求
        request.add("bottle","瓶子ID");//瓶子ID
        requestQueue.add(0,request,this);//把这个请求放在请求队列中
    }

    private void initListenr() {
        msg_btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.destroyMyfragment(Get_Bottle_Fragment.this);
            }
        });
        msg_btn_destory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.destroyMyfragment(Get_Bottle_Fragment.this);
            }
        });
        msg_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.backMyfragment(Get_Bottle_Fragment.this);
            }
        });
        msg_btn_recall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用聊天消息界面

            }
        });
    }

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.e("succeed","ldlldkdjfhfhfhfhff");
        if(what==1){//what只是一个标记
            //User_info user_info = JsonData.getBottle(response.get().toString());
            //在下面更新bottle信息
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    @Override
    public void onFinish(int what) {

    }
}
