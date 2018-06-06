package com.example.yikezhong.ui.video_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yikezhong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/6/5.
 */

public class Video_Fragment extends Fragment {
    @BindView(R.id.tvtablayout)
    TabLayout tvtablayout;
    @BindView(R.id.tvviewpager)
    ViewPager tvviewpager;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.video_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HotVideo_Fragment());
        fragments.add(new Nearby_Fragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        myPagerAdapter.setFragments(fragments);
        tvviewpager.setAdapter(myPagerAdapter);
        tvtablayout.addTab(tvtablayout.newTab());
        tvtablayout.addTab(tvtablayout.newTab());
        tvtablayout.setupWithViewPager(tvviewpager);
        tvtablayout.getTabAt(0).setText("头条");
        tvtablayout.getTabAt(1).setText("热点");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;

        public void setFragments(ArrayList<Fragment> fragments) {
            mFragmentList = fragments;
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = mFragmentList.get(position);

            return fragment;
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
    }
}
