package com.example.yikezhong.ui.video_fragment.nearby_fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yikezhong.R;
import com.example.yikezhong.bean.NearbyBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.ui.base.BaseFragment;
import com.example.yikezhong.ui.utils.LocationUtils;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.adapter.NearbyAdapter;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.contract.NearbyContract;
import com.example.yikezhong.ui.video_fragment.nearby_fragment.presenter.NearbyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    public void getNearbySuccess(NearbyBean bean) {
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        Nearby_Rv.setLayoutManager(manager);
        NearbyAdapter adapter=new NearbyAdapter(getActivity(),bean);
        Nearby_Rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
