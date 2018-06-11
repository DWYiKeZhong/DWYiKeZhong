package com.example.yikezhong.ui.tuijian_fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanListBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created   by   Dewey .
 * 推荐 关注  多条目 展示 2种布局
 * 弹幕 学习：https://blog.csdn.net/u013366008/article/details/77160557
 */
public class GuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //定义 2 种不同的类型
    int ONE = 0;    //一张图片
    int TWO = 1;    //关注作品

    private Context context;
    private List<GuanListBean.DataBean> list;
    private ABigImageViewHolder viewHolder;

    public GuanAdapter(Context context) {
        this.context = context;
    }

    //添加数据的方法
    public void addData(List<GuanListBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //添加布局视图
        if (viewType == ONE) {
            View view = View.inflate(context, R.layout.tui_guan_image, null);
            viewHolder = new ABigImageViewHolder(view);
            return viewHolder;
        } else if (viewType == TWO) {
            View view = View.inflate(context, R.layout.guan_adapter, null);
            GuanViewHolder holder = new GuanViewHolder(view);
            return holder;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //如果类型是 ABigImageViewHolder ,加载一张大图
        if (holder instanceof ABigImageViewHolder) {
            ABigImageViewHolder holder1 = (ABigImageViewHolder) holder;
            String imgPath = "http://pic1.16pic.com/00/05/82/16pic_582918_b.jpg";
            Glide.with(context).load(imgPath).into(holder1.image);

            //如果类型是 GuanViewHolder ,加载Recyclerview布局
        } else if (holder instanceof GuanViewHolder) {
            GuanViewHolder holder2 = (GuanViewHolder) holder;

            //设置布局管理器以及数据适配器
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            holder2.guanRecyclerView.setLayoutManager(manager);
            GuanRecyclerViewAdapter guanRecyclerViewAdapter = new GuanRecyclerViewAdapter(context);
            guanRecyclerViewAdapter.addData(list);
            holder2.guanRecyclerView.setAdapter(guanRecyclerViewAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return 2 ;   //2种 条目类型
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {     //条目下标为0显示大图
            return ONE;
        } else {                 //其他显示Recyclerview布局
            return TWO;
        }
    }

    static class ABigImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ABigImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class GuanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guan_RecyclerView)
        RecyclerView guanRecyclerView;

        public GuanViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
