package com.example.sean_duan.family_frag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sean_duan.family_frag.Bean.DataNetHome;
import com.example.sean_duan.family_frag.activity.MainActivity;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;


public class note_edit extends Fragment implements OnResponseListener<String>{
    private View view ;
    private View father_view;
    private MainActivity mainActivity ;
    private Button button_noteback;
    private LinearLayout choose_layout;
    private FragmentManager fragmentManager ;
    private FragmentTransaction transaction ;
    private EditText editText_note;
    private TextView textView_noteshow;
    private TextView textview_note_add;
    private TextView textview_note_time;
    private Button button_note_send;
    private StringBuffer note_text;
    private RequestQueue requestQueue ;//队列
    private note_edit note_edit ;
    private boolean isget  = false;
    public note_edit() {
        // Required empty public constructor
    }
    public note_edit(View view) {
        // Required empty public constructor
        father_view = view;
        this.note_edit  = this ;
    }
    public note_edit(View view,boolean b) {
        // Required empty public constructor
        father_view = view;
        this.note_edit = this ;
        this.isget = b ;
    }

    public static note_edit newInstance(View view) {
        note_edit fragment = new note_edit(view);

        return fragment;
    }

    public static note_edit newInstance(View view,boolean b) {
        note_edit fragment = new note_edit(view,b);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_note_edit, container, false);
        initView();
        //判断是否是别人所发的帖子
        initisget();
        initListener();
        return view;
    }

    private void initisget() {
        if(isget==true){
            //那么就网络请求 获取帖子
            Request<String> request = new StringRequest(DataNetHome.urlgetTiezi, RequestMethod.GET);
            //提交json表单 request.add   添加帖子ID
            requestQueue.add(1,request,this);
        }
    }

    private void initView() {
        note_text = new StringBuffer();
        choose_layout = (LinearLayout) father_view.findViewById(R.id.choose_layout);
        textview_note_add = (TextView) view.findViewById(R.id.note_add);
        textview_note_time = (TextView) view.findViewById(R.id.note_time);
        button_note_send = (Button) view.findViewById(R.id.button_note_send);
        button_note_send.setText("发送");
        button_noteback = (Button) view.findViewById(R.id.button_noteback);
        editText_note = (EditText) view.findViewById(R.id.editText_note);
        textView_noteshow = (TextView) view.findViewById(R.id.textView_note_send);
        mainActivity = (MainActivity)getActivity();
        requestQueue = NoHttp.newRequestQueue();
    }

    private void initListener() {
        editText_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                button_note_send.setText("完成");
                InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        editText_note.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager =  (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText_note, 0);

                return false;
            }
        });
        button_noteback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyMyfragment(note_edit.this);
                choose_layout.setVisibility(View.VISIBLE);
            }
        });
        button_note_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (button_note_send.getText() == "发送"){
                    if (textView_noteshow.getText() != "")
                        initAlerdialog();
                    else
                        Toast.makeText(mainActivity, "请先输入内容呦 (*^_^*)", Toast.LENGTH_SHORT).show();
                } else {
                    InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//输入法消失
                    inputManager.hideSoftInputFromWindow(editText_note.getWindowToken(),0);
                    button_note_send.getText();
                    note_text.append(editText_note.getText());
                    textView_noteshow.setText(note_text.toString());
                    editText_note.setText("");
                    button_note_send.setText("发送");
                }


            }
        });
    }
    private void initAlerdialog(){
        new AlertDialog.Builder(getContext()).setTitle("系统提示")//设置对话框标题  
                .setMessage("是否发送漂流瓶？")//设置显示的内容  
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
                        if(isget==true){
                        Request<String> request = new StringRequest(DataNetHome.urlSendTie,RequestMethod.GET);
                        //json content  t_userID time adress
                        requestQueue.add(0,request,note_edit);}else {//判断发送出去的是评论还是编辑的内容
                            Request<String> request = new StringRequest(DataNetHome.urlSendTie,RequestMethod.GET);
                            //tieID c_userID content time
                            requestQueue.add(2,request,note_edit);
                        }
                        // TODO Auto-generated method stub 
                        //瓶子发送
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮  
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件  
                // TODO Auto-generated method stub 
                //返回编辑页面

            }
        }).show();//在按键响应事件中显示此对话框  
    }

    private void destroyMyfragment(Fragment fragment){//回调销毁瓶子
//        darkenBackgroundx(1f);
        fragmentManager = mainActivity.getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.remove(fragment);
        transaction.commit();

        //销毁漂流瓶的方法 与数据库一齐销毁
    }

    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<String> response) {
//what 0查看帖子信息 1发帖子 2发评论
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }

    @Override
    public void onFinish(int what) {

    }
}
