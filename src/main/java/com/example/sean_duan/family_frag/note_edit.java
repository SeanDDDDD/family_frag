package com.example.sean_duan.family_frag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class note_edit extends Fragment {
    private View view ;
    private  MainActivity mainActivity ;
    private Button button_noteback;
    private FragmentManager fragmentManager ;
    private FragmentTransaction transaction ;
    private EditText editText_note;
    private TextView textView_noteshow;
    private TextView textview_note_add;
    private TextView textview_note_time;
    private Button button_note_send;
    private StringBuffer note_text;

    public note_edit() {
        // Required empty public constructor
    }

    public static note_edit newInstance() {
        note_edit fragment = new note_edit();
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
        initListener();
        return view;
    }

    private void initView() {
        note_text = new StringBuffer();
        textview_note_add = (TextView) view.findViewById(R.id.note_add);
        textview_note_time = (TextView) view.findViewById(R.id.note_time);
        button_note_send = (Button) view.findViewById(R.id.button_note_send);
        button_noteback = (Button) view.findViewById(R.id.button_noteback);
        editText_note = (EditText) view.findViewById(R.id.editText_note);
        textView_noteshow = (TextView) view.findViewById(R.id.textView_note_send);
        mainActivity = (MainActivity)getActivity();
    }

    private void initListener() {
        button_noteback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destroyMyfragment(note_edit.this);
            }
        });
        button_note_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note_text.append(editText_note.getText());
                textView_noteshow.setText(note_text.toString());
                editText_note.setText("");
            }
        });
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
