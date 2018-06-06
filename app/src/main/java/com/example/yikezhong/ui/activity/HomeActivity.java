package com.example.yikezhong.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.duanzi_fragment.Duanzi_Fragment;
import com.example.yikezhong.ui.tuijian_fragment.TuiJian_Fragment;
import com.example.yikezhong.ui.utils.net_util.NetUtils;
import com.example.yikezhong.ui.video_fragment.Video_Fragment;
import com.hjm.bottomtabbar.BottomTabBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//Fragment页面分类显示
public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.main_menu)
    CircleImageView headImage;
    @BindView(R.id.main_text)
    TextView mainText;
    @BindView(R.id.fabiao)
    ImageView fabiao;
    @BindView(R.id.text_view)
    TextView text_net;
    @BindView(R.id.activity_na)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //侧滑菜单的实现
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //显示没网
        int workType = NetUtils.getNetWorkType(this);
        if (workType == NetUtils.NETWORKTYPE_WIFI){
        }else if (workType == NetUtils.NETWORKTYPE_INVALID){    //没网
            text_net.setVisibility(View.VISIBLE);
            Toast.makeText(this, "网络连接失败，网络断开！", Toast.LENGTH_SHORT).show();
        }

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(66, 66)      //图片大小
                .setFontSize(15)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.BLUE, Color.DKGRAY)     //选项卡的选择颜色
                .addTabItem("推荐", R.drawable.raw_1500083878, TuiJian_Fragment.class)
                .addTabItem("段子", R.drawable.raw_1500085327, Duanzi_Fragment.class)
                .addTabItem("视频", R.drawable.raw_1500083686, Video_Fragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);

                        if (position == 0){
                            mainText.setText("推荐");
                        }else if (position == 1){
                            mainText.setText("段子");
                        }else if (position == 2){
                            mainText.setText("视频");
                        }
                    }
                });

    }

    @OnClick({R.id.main_menu, R.id.fabiao,R.id.text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_menu:    //点击头像，跳出侧滑菜单

                break;
            case R.id.fabiao:
                break;

            case R.id.text_view:   //点击链接网络
                NetUtils.showNoNetWorkDlg(HomeActivity.this);
                break;

            default:
                break;
        }
    }
}
