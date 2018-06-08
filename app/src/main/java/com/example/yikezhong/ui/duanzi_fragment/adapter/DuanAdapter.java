package com.example.yikezhong.ui.duanzi_fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.DuanBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.HotBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

/**
 * Created   by   Dewey .
 * 段子
 */
public class DuanAdapter extends RecyclerView.Adapter<DuanAdapter.DuanViewHolder> {
    private Context context;
    private List<DuanBean.DataBean> list;

    public DuanAdapter(Context context, List<DuanBean.DataBean> list) {
    private List<HotBean.DataBean> list;

    public DuanAdapter(Context context, List<HotBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //添加数据的方法
    public void addData(List<DuanBean.DataBean> data) {
    public void addData(List<HotBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.duan_adapter, null);
        return new DuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuanViewHolder holder, int position) {
        //设置参数数据
        holder.content.setText(list.get(position).getContent());
        holder.time.setText(list.get(position).getCreateTime());
        holder.headImage.setImageURI(list.get(position).getUser().getIcon());

        //用户昵称
        if (list.get(position).getUser().getNickname() == null){
            holder.nickname.setText("匿名用户");
        }else {
            holder.nickname.setText(list.get(position).getUser().getNickname()+"");
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class DuanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headImage)
        SimpleDraweeView headImage;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.biaoti)
        TextView content;


        public DuanViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
