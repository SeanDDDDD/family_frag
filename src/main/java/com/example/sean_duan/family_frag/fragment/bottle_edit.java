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
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class bottle_edit extends Fragment {
    private View view ;
    private View father_view;
    private MainActivity mainActivity ;
    private Button button_bottleback;
    private FragmentManager fragmentManager ;
    private FragmentTransaction transaction ;
    private EditText editText_bottle;
    private TextView textView_bottleshow;
    private Button button_bottle_send;
    private Spinner spinner_bottletype;
    private ArrayAdapter adapter_bottletype;
    private StringBuffer bottle_text;
    private LinearLayout bottle_note_layout;
    public bottle_edit(View view) {
        // Required empty public constructor
        father_view = view;
    }
    public bottle_edit() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static bottle_edit newInstance(View view) {
        bottle_edit fragment = new bottle_edit(view);
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bottle_edit, container, false);
        initView();
        initListener();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter_bottletype = ArrayAdapter.createFromResource(view.getContext(), R.array.bottle_style, R.layout.spinner_bottletype_item);
        adapter_bottletype.setDropDownViewResource(R.layout.spinner_bottletype_item);
        spinner_bottletype.setAdapter(adapter_bottletype);
    }




    private void initView() {
        bottle_text = new StringBuffer();
        bottle_note_layout = (LinearLayout) father_view.findViewById(R.id.choose_layout);
        spinner_bottletype = (Spinner) view.findViewById(R.id.spinner_bottlestyle);
        button_bottle_send = (Button) view.findViewById(R.id.button_bottle_send);
        button_bottle_send.setText("发送");
        button_bottleback = (Button) view.findViewById(R.id.button_bottleback);
        editText_bottle = (EditText) view.findViewById(R.id.editText_bottle);
        textView_bottleshow = (TextView) view.findViewById(R.id.textView_bottle_send);
        mainActivity = (MainActivity)getActivity();
    }
    private void initListener() {
        editText_bottle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                button_bottle_send.setText("完成");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        editText_bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = ( InputMethodManager) view.getContext( ).getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow( view.getApplicationWindowToken() , 0 );
                }
            }
        });
        button_bottleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyMyfragment(bottle_edit.this);
                bottle_note_layout.setVisibility(View.VISIBLE);
            }
        });
        button_bottle_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button_bottle_send.getText() == "发送"){
                    if (textView_bottleshow.getText() != ""){
                        initAlerdialog();
                    }
                    else
                        Toast.makeText(mainActivity, "请先输入内容呦 (*^_^*)", Toast.LENGTH_SHORT).show();
                } else {
                    bottle_text.append(editText_bottle.getText());
                    textView_bottleshow.setText(bottle_text.toString());
                    editText_bottle.setText("");
                    button_bottle_send.getText();
                    button_bottle_send.setText("发送");

                }

            }
        });
        //用不上
        spinner_bottletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initAlerdialog(){
        new AlertDialog.Builder(getContext()).setTitle("系统提示")//设置对话框标题  
                .setMessage("是否发送漂流瓶？")//设置显示的内容  
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
                        //发送数据给服务器
                        sendMsgtoService();
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

    private void sendMsgtoService() {
        //发送给服务器title userID type content time adress
        


    }

    private void destroyMyfragment(Fragment fragment){//回调销毁瓶子
//        darkenBackgroundx(1f);
        fragmentManager = mainActivity.getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.remove(fragment);
        transaction.commit();

        //销毁漂流瓶的方法 与数据库一齐销毁
    }

}
