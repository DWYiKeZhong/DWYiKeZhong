package com.example.yikezhong.ui.activity.follow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanListBean;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/6/9.
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {
    private Context context;
    private GuanListBean bean;

    public FollowAdapter(Context context, GuanListBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.follow_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.followItemSdw.setImageURI(bean.getData().get(position).getIcon());
        holder.followItemName.setText(bean.getData().get(position).getNickname());
        holder.followItemTime.setText(bean.getData().get(position).getCreatetime());
        holder.followItemDetailname.setText(bean.getData().get(position).getNickname());
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        holder.followItemSdw.getHierarchy().setRoundingParams(roundingParams);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.follow_item_sdw)
        SimpleDraweeView followItemSdw;
        @BindView(R.id.follow_item_name)
        TextView followItemName;
        @BindView(R.id.follow_item_detailname)
        TextView followItemDetailname;
        @BindView(R.id.follow_item_iv)
        ImageView followItemIv;
        @BindView(R.id.follow_item_time)
        TextView followItemTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
