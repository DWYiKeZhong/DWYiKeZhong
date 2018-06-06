package com.example.yikezhong.ui.video_fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.HotVideoBean;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/6/5.
 */

public class HotVideoAdapter extends RecyclerView.Adapter<HotVideoAdapter.ViewHoilder> {
    private Context context;
    private HotVideoBean bean;
    private int itemWidth;

    public HotVideoAdapter(Context context, HotVideoBean bean) {
        this.context = context;
        this.bean = bean;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        itemWidth = width / 2;
    }

    @NonNull
    @Override
    public ViewHoilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //引入布局
        View view = View.inflate(context, R.layout.hotvideo_item, null);
        //01000000000000
        return new ViewHoilder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoilder holder, int position) {
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
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().size();
    }

    public class ViewHoilder extends RecyclerView.ViewHolder {
        @BindView(R.id.hotvideo_sdv)
        SimpleDraweeView hotvideoSdv;
        public ViewHoilder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
