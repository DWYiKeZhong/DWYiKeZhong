package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
 * Created   by    Dewey
 * 推荐 页面
 */
public class TuiJian_Fragment extends Fragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tuijian_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);


        //添加Fragment
        list = new ArrayList<>();
        list.add(new Tui_Hot_Fragment());
        list.add(new Tui_Guan_Fragment());

        //设置使用ViewPager+Fragment显示数据布局的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //返回对应的fragment
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //动态添加选项卡
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        //TabLyout要与ViewPager关联显示
        tabLayout.setupWithViewPager(viewPager);

        //选项卡名字
        tabLayout.getTabAt(0).setText("热门");
        tabLayout.getTabAt(1).setText("关注");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
