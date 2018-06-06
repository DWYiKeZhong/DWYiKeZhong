package com.example.yikezhong.ui.video_fragment.nearby_fragment;

import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.NearbyBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.ui.activity.VideoDetail.VideoDetailActivity;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.utils.LocationUtils;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.adapter.NearbyAdapter;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.contract.NearbyContract;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.presenter.NearbyPresenter;

/**
 * Created by lenovo on 2018/6/6.
 */

public class Nearby_Fragment extends BaseFragment<NearbyPresenter> implements NearbyContract.View {
    private RecyclerView Nearby_Rv;

    @Override
    public int getContentLayout() {
        return R.layout.nearby_fragment;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        Nearby_Rv=view.findViewById(R.id.Nearby_Rv);
        Location location = LocationUtils.getInstance(getActivity()).showLocation();
        Double latitude= location.getLatitude();
        Double longitude= location.getLongitude();
        mPresenter.getNearby("100",latitude,longitude);
    }

    @Override
    public void getNearbySuccess(final NearbyBean bean) {
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        Nearby_Rv.setLayoutManager(manager);
        NearbyAdapter adapter=new NearbyAdapter(getActivity(),bean);
        Nearby_Rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setItemClickListener(new NearbyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(), VideoDetailActivity.class);
                intent.putExtra("wid",bean.getData().get(position).getWid()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
