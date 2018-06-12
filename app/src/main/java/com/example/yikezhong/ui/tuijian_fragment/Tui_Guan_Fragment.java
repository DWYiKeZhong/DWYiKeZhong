package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created   by    Dewey
 * 推荐 关注 布局替换  登录与未登录
 */
public class Tui_Guan_Fragment extends Fragment {
    @BindView(R.id.viewPager01)
    ViewPager viewPager01;
    @BindView(R.id.xiaoren_weidenglu)
    ViewPager xiaorenWeidenglu;
    Unbinder unbinder;
    private String token;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tui_guan_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置使用ViewPager+Fragment显示关注 布局
        viewPager01.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                //判断所选的标题，进行传值显示,然后返回对应的fragment
                Tui_GuanContent_Fragment contentFragment = new Tui_GuanContent_Fragment();
                return contentFragment;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        //      ---------------------  没有登录时显示 哭的小人viewpager
        xiaorenWeidenglu.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            //去登陆fragment
            private GoToLogin_Fragment loginFragment;

            @Override
            public Fragment getItem(int position) {
                loginFragment = new GoToLogin_Fragment();
                return loginFragment;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        //sharedpreferences存取数据
        uid = (String) SharedPreferencesUtils.getParam(getActivity(), "uid", "");
        token = (String) SharedPreferencesUtils.getParam(getActivity(), "token", "");

        if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
            Toast.makeText(getActivity(), "请先登录哦！", Toast.LENGTH_SHORT).show();
            xiaorenWeidenglu.setVisibility(View.VISIBLE);       //设置显示哭的小人布局
            viewPager01.setVisibility(View.GONE);
        }
        else if (uid != null && token != null && uid.length() > 0 && token.length() > 0) {
            //如果已经登录,就显示数据
            Toast.makeText(getActivity(), "已登录", Toast.LENGTH_SHORT).show();
            viewPager01.setVisibility(View.VISIBLE);
            xiaorenWeidenglu.setVisibility(View.GONE);
        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //每次进入页面,都判断是否登录
        uid = (String) SharedPreferencesUtils.getParam(getActivity(), "uid", "");
        token = (String) SharedPreferencesUtils.getParam(getActivity(), "token", "");

        //如果没登录,就显示 哭的小人 ViewPager
        if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
            Toast.makeText(getActivity(), "请先登录哦！", Toast.LENGTH_SHORT).show();
            xiaorenWeidenglu.setVisibility(View.VISIBLE);       //设置显示哭的小人布局
            viewPager01.setVisibility(View.GONE);
        } else if (uid != null && token != null && uid.length() > 0 && token.length() > 0) {
            //如果已经登录,就显示数据
            Toast.makeText(getActivity(), "已登录", Toast.LENGTH_SHORT).show();
            viewPager01.setVisibility(View.VISIBLE);
            xiaorenWeidenglu.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
