package com.example.sean_duan.family_frag.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.sean_duan.family_frag.fragment.Get_Bottle_Fragment;
import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.fragment.bottle_or_note;
import com.example.sean_duan.family_frag.note_edit;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

public class MainActivity extends AppCompatActivity {

    private Button button_1 ;
    private Button button_2 ;
    private Button button_choosetype;//吴悠 选择瓶子便签 +号按钮
    private RelativeLayout getbottle_layout;
    private FrameLayout choose_layout;
    private FragmentManager fragmentManager ;
    private FragmentTransaction transaction ;

    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.history_msg:
                    Intent intent = new Intent(MainActivity.this,History_msg_Activity.class);
                    startActivity(intent);
                    finish();
                    item.setIcon(R.drawable.history_msg_press);
                    Log.e("MainActivity", "onNavigationItemSelected: " );
                    return true;
                case R.id.back_bottle:
                    item.setIcon(R.drawable.bottle_press);
//                    Resources resource=getBaseContext().getResources();
//                    ColorStateList csl=resource.getColorStateList(R.drawable.icon_menu_change);
//                    navigation.setItemIconTintList(csl);
                    return true;
                case R.id.user_interface:
                    item.setIcon(R.drawable.user_interface_press);
//                    mTextMessage.setText(R.string.title_notifications);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                com.squareup.okhttp.Response response = com.zhy.http.okhttp.OkHttpUtils.get().url("http://www.baidu.com").build().execute();
//                                Log.e("d",response.body().string());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(30 * 1000) // 全局连接超时时间，单位毫秒。
                .setReadTimeout(30 * 1000) // 全局服务器响应超时时间，单位毫秒。
                .setNetworkExecutor(new OkHttpNetworkExecutor())//设置底层为Okhttp
        );
        initView();//初始化视图
        listener();
    }

    private void listener() {
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceRelativeLayoutFragment(getbottle_layout.getId(), Get_Bottle_Fragment.newInstance("80"));
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new note_edit().newInstance(v,true);
                fragmentManager = getSupportFragmentManager();//获取fragmentManager
                transaction = fragmentManager.beginTransaction();//开启事务
                transaction.add(R.id.container, fragment);
                transaction.commit();
            }
        });
        button_choosetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrameLayoutFragment(choose_layout.getId(), bottle_or_note.newInstance("1314","521"));
            }
        });
    }
    private void replaceRelativeLayoutFragment(int id, Fragment fragment) {
        RelativeLayout layuot  = (RelativeLayout) findViewById(R.id.content);
//        layuot.setAlpha(0.5f);
//        layuot.setBackgroundColor();
//        darkenBackgroundx(0.9f);
        fragmentManager = getSupportFragmentManager();//获取fragmentManager

        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.add(id, fragment);
        transaction.commit();
    }
    private void initView() {
        choose_layout = (FrameLayout) findViewById(R.id.first_fragment);
        getbottle_layout = (RelativeLayout) findViewById(R.id.content);
//        choose_layout = (FrameLayout) findViewById(R.id.choose_fragment);
        button_1 = (Button) findViewById(R.id.button_1);
        button_choosetype = (Button) findViewById(R.id.button_choosetype);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(( R.id.back_bottle));

        fragmentManager = getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
    }

    private void replaceFrameLayoutFragment(int id,Fragment fragment){
        FrameLayout layout = (FrameLayout) findViewById(id);
        layout.setVisibility(View.VISIBLE);
        fragmentManager = getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.add(id, fragment);
        transaction.commit();
       // transaction.replace(R.id.0)
    }
    public void destroyMyfragment(Fragment fragment){//回调销毁瓶子
        choose_layout.setVisibility(View.GONE);
        fragmentManager = getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.remove(fragment);
        transaction.commit();
        //销毁漂流瓶的方法
    }

    public void backMyfragment(Fragment fragment){//回调调用扔回瓶子
        fragmentManager = getSupportFragmentManager();//获取fragmentManager
        transaction = fragmentManager.beginTransaction();//开启事务
        transaction.remove(fragment);
        transaction.commit();
    }
}
