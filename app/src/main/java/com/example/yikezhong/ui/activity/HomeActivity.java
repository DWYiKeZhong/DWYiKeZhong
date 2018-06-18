package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.activity.collection.CollectionActivity;
import com.example.yikezhong.ui.activity.follow.FollowActivity;
import com.example.yikezhong.ui.activity.login.LoginActivity;
import com.example.yikezhong.ui.activity.search.SearchActivity;
import com.example.yikezhong.ui.activity.setting.SettingActivity;
import com.example.yikezhong.ui.duanzi_fragment.Duanzi_Fragment;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.tuijian_fragment.TuiJian_Fragment;
import com.example.yikezhong.ui.utils.net_util.NetUtils;
import com.example.yikezhong.ui.video_fragment.Video_Fragment;
import com.example.yikezhong.wxapi.MobActivity;
import com.hjm.bottomtabbar.BottomTabBar;
import com.kyleduo.switchbutton.SwitchButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//Fragment页面分类显示
public class HomeActivity extends MobActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.main_menu)
    CircleImageView headImage;
    @BindView(R.id.main_text)
    TextView mainText;
    @BindView(R.id.mode)
    TextView mode;
    @BindView(R.id.fabiao)
    ImageView fabiao;
    @BindView(R.id.yl)
    ImageView moon;
    @BindView(R.id.text_view)
    TextView text_net;
    @BindView(R.id.activity_na)
    DrawerLayout drawerLayout;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.switchButton)
    SwitchButton switchButton;
    @BindView(R.id.left_main_menu)
    CircleImageView leftMainMenu;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.name)
    TextView userName;
    private long exitTime = 0;            //退出主程序时间
    private int theme = R.style.AppTheme;
    private int currentNightMode;
    private int curren;
    private int flag = 1;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        int flga = (Integer) SharedPreferencesUtils.getParam(HomeActivity.this, "flag", 1);
        curren = (Integer) SharedPreferencesUtils.getParam(HomeActivity.this, "position", 0);
        if (curren == Configuration.UI_MODE_NIGHT_NO) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (flga == 1) {
            switchButton.setChecked(true);
            flag = 2;
        } else {
            switchButton.setChecked(false);
            flag = 1;
        }


        Drawable drawableFirst = getResources().getDrawable(R.drawable.raw_1499947056);
        drawableFirst.setBounds(0, 0, 50, 50);
        rb1.setCompoundDrawables(null, drawableFirst, null, null);
        Drawable drawableSearch = getResources().getDrawable(R.drawable.raw_1499947157);
        drawableSearch.setBounds(0, 0, 50, 50);
        rb2.setCompoundDrawables(null, drawableSearch, null, null);


        initViews();

        indate();
    }

    private void initViews() {

        //显示没网
        int workType = NetUtils.getNetWorkType(this);
        if (workType == NetUtils.NETWORKTYPE_WIFI) {
        } else if (workType == NetUtils.NETWORKTYPE_INVALID) {    //没网
            text_net.setVisibility(View.VISIBLE);
            Toast.makeText(this, "网络连接失败，网络断开！", Toast.LENGTH_SHORT).show();
        }

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(66, 66)      //图片大小
                .setFontSize(15)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.BLUE, Color.DKGRAY)     //选项卡的选择颜色
                .addTabItem("推荐", R.drawable.raw_1500085367, R.drawable.raw_1500083878, TuiJian_Fragment.class)
                .addTabItem("段子", R.drawable.raw_1500085899, R.drawable.raw_1500085327, Duanzi_Fragment.class)
                .addTabItem("视频", R.drawable.raw_1500086067, R.drawable.raw_1500083686, Video_Fragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);

                        if (position == 0) {
                            mainText.setText("推荐");
                        } else if (position == 1) {
                            mainText.setText("段子");
                        } else if (position == 2) {
                            mainText.setText("视频");
                        }
                    }
                });

        //点击切换 日/夜间模式
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
                    switchButton.setChecked(true);
                    SharedPreferencesUtils.setParam(HomeActivity.this, "flag", flag);
                    flag = 2;
                } else {
                    mode.setText("夜间模式");
                    moon.setImageResource(R.drawable.moon);
                    switchButton.setChecked(false);
                    SharedPreferencesUtils.setParam(HomeActivity.this, "flag", flag);
                    flag = 1;
                }
                SharedPreferencesUtils.setParam(HomeActivity.this, "position", currentNightMode);
                getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
                        ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                recreate();
            }
        });
    }

    //判断登录状态，设置昵称，头像
    private void indate() {
        isLogin = SharedPreferencesUtils.getBoolean("isLogin");
        if (isLogin) {
            String nickname = SharedPreferencesUtils.getString("nickname");
            userName.setText(nickname);
            String icon = SharedPreferencesUtils.getString("icon");
            Glide.with(this).load(icon).into(headImage);
            Glide.with(this).load(icon).into(leftMainMenu);

            leftMainMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(HomeActivity.this, "已登录，跳转至个人信息页！ UserNameActivity ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(HomeActivity.this, UserNameActivity.class);
                    startActivity(intent);
                }
            });

        } else {
            leftMainMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            });
        }
    }

    @OnClick({R.id.left_main_menu,R.id.main_menu,R.id.home_follow_rl,R.id.home_collection_rl, R.id.home_search_rl, R.id.rb2, R.id.fabiao, R.id.text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_menu:    //点击头像，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

            case R.id.left_main_menu:    //点击头像，跳转登录
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            //我的关注
            case R.id.home_follow_rl:

                    if (isLogin) {
                        startActivity(new Intent(HomeActivity.this, FollowActivity.class));
                    } else {
                        Toast.makeText(HomeActivity.this, "请登录后再来进行关注哦！", Toast.LENGTH_SHORT).show();
                    }
                break;

            //我的收藏
            case R.id.home_collection_rl:

                    if (isLogin) {
                        startActivity(new Intent(HomeActivity.this, CollectionActivity.class));
                    } else {
                        Toast.makeText(HomeActivity.this, "请登录后再来查看收藏哦！", Toast.LENGTH_SHORT).show();
                    }
                break;

            //搜索钟友
            case R.id.home_search_rl:

                    if (isLogin) {
                        startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                    } else {
                        Toast.makeText(HomeActivity.this, "请登录后再来搜索哦！", Toast.LENGTH_SHORT).show();
                    }
                break;

            //跳转 设置
            case R.id.rb2:
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                break;

            //发表创作
            case R.id.fabiao:

                if (isLogin) {
                    startActivity(new Intent(HomeActivity.this, FaBuActivity.class));
                } else {
                    Toast.makeText(HomeActivity.this, "请登录后再发表哦！", Toast.LENGTH_SHORT).show();
                }
                break;

            //点击链接网络
            case R.id.text_view:
                NetUtils.showNoNetWorkDlg(HomeActivity.this);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //每次进入页面判断登录状态
        indate();
        getSharedValues();
    }

    //获取登录信息
    public void getSharedValues() {
        boolean isLogin = SharedPreferencesUtils.getBoolean("isLogin");
        if (isLogin) {
            String nickname = SharedPreferencesUtils.getString("nickname");
            if (nickname == null) {
                String name = SharedPreferencesUtils.getString("name");
                userName.setText(name);
            } else {
                userName.setText(nickname);
            }

        } else {
            userName.setText("请登陆");
        }
    }

    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

}
