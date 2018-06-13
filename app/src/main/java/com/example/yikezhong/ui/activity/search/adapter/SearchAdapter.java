package com.example.yikezhong.ui.activity.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.SearchBean;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/6/12.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private SearchBean bean;

    public SearchAdapter(Context context, SearchBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.search_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        holder.searchsdv.getHierarchy().setRoundingParams(roundingParams);
        holder.searchsdv.setImageURI((String) bean.getData().get(position).getIcon());
        holder.name.setText(bean.getData().get(position).getNickname());
    }

    @Override
    public int getItemCount() {
        return bean==null?0:bean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_sdv)
        SimpleDraweeView searchsdv;                //头像
        @BindView(R.id.search__item_name)
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
