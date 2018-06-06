package com.example.yikezhong.ui.video_fragment.nearby_fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.NearbyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Observable;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/6/6.
 * 视频 附近  Adapter
 */

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private NearbyBean bean;
    private int itemWidth;

    public NearbyAdapter(Context context, NearbyBean bean) {
        this.context = context;
        this.bean = bean;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        itemWidth = width / 2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hotvideo_item, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.hotvideoSdv.getLayoutParams();
        int itemHeight = 300;
        itemHeight = new Random().nextInt(500);
        if (itemHeight < 300) {
            itemHeight = 300;
        }

        params.width = itemWidth;
        params.height = itemHeight;

        holder.hotvideoSdv.setLayoutParams(params);
        holder.hotvideoSdv.setImageURI(bean.getData().get(position).getCover());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().size();
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotvideo_sdv)
        SimpleDraweeView hotvideoSdv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
