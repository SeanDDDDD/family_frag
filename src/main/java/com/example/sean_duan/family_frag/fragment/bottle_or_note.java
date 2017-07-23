package com.example.sean_duan.family_frag.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.sean_duan.family_frag.activity.MainActivity;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.note_edit;


public class bottle_or_note extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view ;
    private MainActivity mainActivity ;
    private ImageButton button_bottle;
    private ImageButton button_note;
    private ImageButton button_reback;
    private LinearLayout choose_layout;
    private FragmentManager fragmentManager ;
    private FragmentTransaction transaction ;
    private FrameLayout layout;
    public bottle_or_note() {
        // Required empty public constructor
    }


    public static bottle_or_note newInstance(String param1, String param2) {
        bottle_or_note fragment = new bottle_or_note();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bottle_or_note, container, false);
        //初始化视图
        initView();
        //初始化监听器
        initListen();
        return view;
    }

    private void initListen() {
        button_bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new bottle_edit().newInstance(view);
                fragmentManager = mainActivity.getSupportFragmentManager();//获取fragmentManager
                transaction = fragmentManager.beginTransaction();//开启事务
                transaction.add(layout.getId(), fragment);
                transaction.commit();
                choose_layout.setVisibility(View.GONE);

            }
        });
        button_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new note_edit().newInstance(view);
                fragmentManager = mainActivity.getSupportFragmentManager();//获取fragmentManager
                transaction = fragmentManager.beginTransaction();//开启事务
                transaction.add(layout.getId(), fragment);
                transaction.commit();
                choose_layout.setVisibility(View.GONE);
            }
        });
        button_reback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.destroyMyfragment(bottle_or_note.this);
            }
        });
    }

    private void initView() {
        choose_layout = (LinearLayout) view.findViewById(R.id.choose_layout);
        layout = (FrameLayout) view.findViewById(R.id.bottle_note_layout);
        button_bottle = (ImageButton) view.findViewById(R.id.button_bottle);
        button_note = (ImageButton) view.findViewById(R.id.button_note);
        button_reback = (ImageButton) view.findViewById(R.id.button_reback);
        mainActivity = (MainActivity)getActivity();
    }


}
