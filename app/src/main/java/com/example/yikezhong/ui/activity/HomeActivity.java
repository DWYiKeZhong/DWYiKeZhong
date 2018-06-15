package com.example.yikezhong.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.MainActivity;
import com.example.yikezhong.R;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.ui.activity.collection.CollectionActivity;
import com.example.yikezhong.ui.activity.contract.UpdateHeaderContract;
import com.example.yikezhong.ui.activity.follow.FollowActivity;
import com.example.yikezhong.ui.activity.presenter.UpdatePresenter;
import com.example.yikezhong.ui.activity.search.SearchActivity;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.duanzi_fragment.Duanzi_Fragment;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.tuijian_fragment.TuiJian_Fragment;
import com.example.yikezhong.ui.utils.net_util.NetUtils;
import com.example.yikezhong.ui.video_fragment.Video_Fragment;
import com.hjm.bottomtabbar.BottomTabBar;
import com.kyleduo.switchbutton.SwitchButton;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

//Fragment页面分类显示
public class HomeActivity extends BaseActivity<UpdatePresenter> implements UpdateHeaderContract.View {
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
    @BindView(R.id.left)
    LinearLayout left;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.switchButton)
    SwitchButton switchButton;
    @BindView(R.id.home_top_rl)
    RelativeLayout homeTopRl;
    @BindView(R.id.left_main_menu)
    CircleImageView leftMainMenu;
    @BindView(R.id.home_follow_rl)
    RelativeLayout homeFollowRl;
    @BindView(R.id.home_collection_rl)
    RelativeLayout homeCollectionRl;
    @BindView(R.id.home_search_rl)
    RelativeLayout homeSearchRl;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
    private String imgPath;
    private long exitTime = 0;            //退出主程序时间
    private int theme = R.style.AppTheme;
    private int currentNightMode;
    private  int curren;
    private int flag=1;
    private String uid = (String) SharedPreferencesUtils.getParam(HomeActivity.this, "uid", "");
    private String token = (String) SharedPreferencesUtils.getParam(HomeActivity.this, "token", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        int flga= (Integer)SharedPreferencesUtils.getParam(HomeActivity.this,"flag",1);
        curren= (Integer)SharedPreferencesUtils.getParam(HomeActivity.this,"position",0);
        if (curren==Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if (flga==1){
            switchButton.setChecked(true);
            flag=2;
        }else {
            switchButton.setChecked(false);
            flag=1;
        }
        imgPath = getExternalCacheDir() + File.separator + "header.jpg";
        Drawable drawableFirst = getResources().getDrawable(R.drawable.raw_1499947056);
        drawableFirst.setBounds(0, 0, 50, 50);
        rb1.setCompoundDrawables(null, drawableFirst, null, null);
        Drawable drawableSearch = getResources().getDrawable(R.drawable.raw_1499947157);
        drawableSearch.setBounds(0, 0, 50, 50);
        rb2.setCompoundDrawables(null, drawableSearch, null, null);

        homeFollowRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
                    Toast.makeText(HomeActivity.this, "请登录后再来进行关注哦！", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(HomeActivity.this, FollowActivity.class));
                }
            }
        });
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
                .addTabItem("推荐", R.drawable.raw_1500085367,R.drawable.raw_1500083878, TuiJian_Fragment.class)
                .addTabItem("段子", R.drawable.raw_1500085899,R.drawable.raw_1500085327, Duanzi_Fragment.class)
                .addTabItem("视频", R.drawable.raw_1500086067,R.drawable.raw_1500083686, Video_Fragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);

                        if (position == 0) {
                            mainText.  setText("推荐");
                        } else if (position == 1) {
                            mainText.setText("段子");
                        } else if (position == 2) {
                            mainText.setText("视频");
                        }
                    }
                });

        homeTopRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, OtherActivity.class));
            }
        });
        leftMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChoosePicDialog();
            }
        });
        homeCollectionRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
                    Toast.makeText(HomeActivity.this, "请登录后再来查看收藏哦！", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(HomeActivity.this, CollectionActivity.class));
                }
            }
        });
        homeSearchRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
                    Toast.makeText(HomeActivity.this, "请登录后再来搜索哦！", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                }

            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {       //点击切换 日/夜间模式
                currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (currentNightMode == Configuration.UI_MODE_NIGHT_NO){
                    switchButton.setChecked(true);
//                    mode.setText("夜间模式");
//                    moon.setImageResource(R.drawable.moon);
                    SharedPreferencesUtils.setParam(HomeActivity.this,"flag",flag);
                    flag=2;
                }else {
                    switchButton.setChecked(false);
                    mode.setText("日间模式");
                    moon.setImageResource(R.drawable.sun);
                    SharedPreferencesUtils.setParam(HomeActivity.this,"flag",flag);
                    flag=1;
                }
                SharedPreferencesUtils.setParam(HomeActivity.this,"position",currentNightMode);
                getDelegate().setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO
                        ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // 同样需要调用recreate方法使之生效
                recreate();
            }
        });
    }

    @OnClick({R.id.main_menu, R.id.fabiao, R.id.text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_menu:    //点击头像，跳出侧滑菜单
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.fabiao:      //发表创作

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
                    Toast.makeText(HomeActivity.this, "请登录后再发表哦！", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(HomeActivity.this, FaBuActivity.class));
                }
                break;

            case R.id.text_view:   //点击链接网络
                NetUtils.showNoNetWorkDlg(HomeActivity.this);
                break;

            default:
                break;
        }
    }

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


    /**
     * 显示修改图片的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("添加图片");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "temp_image.jpg"));
                        // 将拍照所得的相片保存到SD卡根目录
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == HomeActivity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            //这里图片是方形的，可以用一个工具类处理成圆形（很多头像都是圆形，这种工
            // 具类网上很多不再详述）
            leftMainMenu.setImageBitmap(mBitmap);//显示图片
            //  mPresenter.updateHeader("3834",imgPath,"68927D4A5729568EF66D5AC1787227E7");
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void updateSuccess(String code) {
        if ("0".equals(code)) {
            toast("上传成功");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
