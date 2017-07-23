package com.example.sean_duan.family_frag.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sean_duan.family_frag.R;
import com.example.sean_duan.family_frag.Bean.User_info;

import java.util.List;

/**
 * Created by sean-duan on 2017/7/20.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener{
    private Context context ;//上下文
    private List<User_info> mlist;
    private RecyclerViewOnItemClickListener onItemClickListener;
    private RecyclerViewOnItemLongClickListener onItemLongClickListener;

    private int deleteId  ;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View menuView ;
        ImageView menuImage;
        TextView infoName ;//用户姓名
        TextView infoData;//发送日期
        TextView infoContent;//内容
        TextView infoTitle;//标题
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            menuView = itemView ;
            menuImage = (ImageView) itemView.findViewById(R.id.imageView);
            infoName = (TextView) itemView.findViewById(R.id.info_name);
            infoData = (TextView)  itemView.findViewById(R.id.info_data);
            infoContent = (TextView) itemView.findViewById(R.id.info_content);
            infoTitle = (TextView) itemView.findViewById(R.id.info_title);
            button = (Button) itemView.findViewById(R.id.button_delete);

        }
    }

    public MyRecyclerAdapter(List<User_info> mlist,Context context) {
        this.context = context;
        this.mlist = mlist ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_msg,parent,false);
        ViewHolder holder = new ViewHolder(view);
        //为Item设置点击事件
        view.setOnClickListener(this);//设置按钮点击事件
        view.setOnLongClickListener(this);

//        holder.menuImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //点击图片事件
//            }
//        });
//
//        holder.infoName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //点击姓名处理事件
//            }
//        });
        return holder ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  int position) {
        User_info userInfo = mlist.get(position) ;
        holder.menuImage.setImageResource(userInfo.getImageView());
        holder.infoContent.setText(userInfo.getInfoContent());
        holder.infoData.setText(userInfo.getInfoDate());
        holder.infoName.setText(userInfo.getInfoName());
        holder.menuView.setTag(position);//设置Tag

        if(mlist.get(position).getFlag()==true){
            deleteId = position;
            holder.button.setVisibility(View.VISIBLE);
        }
        if(holder.button.getVisibility()==View.VISIBLE){
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeData(deleteId);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClickListener(v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClickListener(v, (Integer)v.getTag());
    }
    /*设置点击事件*/
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /*设置长按事件*/
    public void setOnItemLongClickListener(RecyclerViewOnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }



    public void addData(int position,User_info user_info){//增加数据
        mlist.add(position,user_info);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mlist.size());
    }
    public void removeData(int position){//移除数据
        mlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mlist.size());
    }
    public interface RecyclerViewOnItemClickListener{
        void onItemClickListener(View view, int position);
    }
    public interface RecyclerViewOnItemLongClickListener{
        boolean onItemLongClickListener(View view, int position);
    }
    public void changeList(List<User_info> mlist){//更新适配器的数据
        this.mlist.clear();//清除
        this.mlist = mlist;//添加
        notifyDataSetChanged();//刷新数据
    }
}
