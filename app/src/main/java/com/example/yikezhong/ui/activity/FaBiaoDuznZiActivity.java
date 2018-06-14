package com.example.yikezhong.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.FaBiaoDuanBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.contract.FaBiaoDuanContract;
import com.example.yikezhong.ui.activity.presenter.FaBiaoDuanPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.utils.ImageUtils;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//发布段子内容
public class FaBiaoDuznZiActivity extends BaseActivity<FaBiaoDuanPresenter> implements FaBiaoDuanContract.View {
    @BindView(R.id.qx_text)
    TextView qxText;
    @BindView(R.id.fabiaoText)
    TextView fabiaoText;
    @BindView(R.id.duanzi_id)
    EditText duanziId;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.di)
    View bottom;
    private PopupWindow popupWindow;
    private View parent;
    //本类是放进某个实体类执行，与ImageUtils配合使用。下面是一些操作符
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private String content;

    @Override
    public int getContentLayout() {
        return R.layout.activity_fabiao_duznzi;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    public void getFaBiaoDuanSuccess(FaBiaoDuanBean faBiaoDuanBean) {
        if (faBiaoDuanBean.getCode().equals("0")) {           //发表成功,跳转 成功分享
            Toast.makeText(FaBiaoDuznZiActivity.this, faBiaoDuanBean.getMsg(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FaBiaoDuznZiActivity.this, FaBuSuccessActivity.class);
            intent.putExtra("content",content);
            startActivity(intent);
            finish();
        }
        else if (faBiaoDuanBean.getCode().equals("1")) {     //发表失败
            Toast.makeText(FaBiaoDuznZiActivity.this, faBiaoDuanBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        //初始化 PopWindow
        initPopWindow1();

    }

    //主要是 点击上传的加号 后提示
    private void initPopWindow1() {
        //PopWindow 窗口
        parent = View.inflate(FaBiaoDuznZiActivity.this, R.layout.activity_fabiao_duznzi, null);
        View contentView1 = View.inflate(FaBiaoDuznZiActivity.this, R.layout.upload_pop_layout, null);
        TextView  text_camera = contentView1.findViewById(R.id.text_camera);
        TextView  text_local_pic = contentView1.findViewById(R.id.text_local_pic);
        TextView  text_cancel = contentView1.findViewById(R.id.text_cancel);
        text_camera.setOnClickListener(new View.OnClickListener() {   //相机拍照
            @Override
            public void onClick(View v) {
                takePicture();
                popupWindow.dismiss();
            }
        });
        text_local_pic.setOnClickListener(new View.OnClickListener() {    //本地上传
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                //打开pictures画面Type设置为image
                intent.setType("image/*");

                //使用Intent.ACTION_GET_CONTENT 这个Action
                intent.setAction(Intent.ACTION_GET_CONTENT);

                //取得像片后返回本画面
                startActivityForResult(intent, 1);
                popupWindow.dismiss();
            }
        });
        text_cancel.setOnClickListener(new View.OnClickListener() {   //关闭 popupWindow
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //一些属性
        popupWindow.setFocusable(true);//可获取焦点
        popupWindow.setTouchable(true);//可触摸屏幕

        popupWindow.setOutsideTouchable(true);  //设置外部区域是否可点击
        popupWindow.setBackgroundDrawable(new BitmapDrawable());  //设置背景
    }

    @OnClick({R.id.qx_text, R.id.fabiaoText, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qx_text:     //取消，弹出 是否提示保存

                initPopWindow2();

                break;

            case R.id.fabiaoText:       //发表段子内容  "4416"      "46FB809A1FFEE06DEDED783742F363CA"
                content = duanziId.getText().toString();
                String uid = (String) SharedPreferencesUtils.getParam(FaBiaoDuznZiActivity.this, "uid", "");
                String token = (String) SharedPreferencesUtils.getParam(FaBiaoDuznZiActivity.this, "token", "");

                if (uid == null || token == null || uid.length() <= 0 || token.length() <= 0) {
                    Toast.makeText(FaBiaoDuznZiActivity.this, "请登录后再发表哦！", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.getFaBiaoDuanP(uid, token, content);
                }


                break;

            case R.id.add:  //点击大加号，底部 弹出 popupWindow
                popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
                break;

            default:
                break;
        }
    }

    //提示保存
    private void initPopWindow2() {
        //PopupWindow对象
        View contentView2 = View.inflate(FaBiaoDuznZiActivity.this, R.layout.popupwindow_layout, null);
        TextView save = contentView2.findViewById(R.id.save);
        TextView cancelSave = contentView2.findViewById(R.id.cancelSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FaBiaoDuznZiActivity.this, "亲，已保存哦 ~ ", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        cancelSave.setOnClickListener(new View.OnClickListener() {        //不保存
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        popupWindow = new PopupWindow(contentView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //一些属性
        popupWindow.setFocusable(true);//可获取焦点

        popupWindow.setOutsideTouchable(true); //设置外部区域是否可点击

//    	ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
        ColorDrawable colorDrawable = new ColorDrawable(-0000);
        popupWindow.setBackgroundDrawable(colorDrawable);//设置透明背景

        //显示方法
        popupWindow.showAtLocation(bottom, Gravity.BOTTOM, 0, 0);//显示popupwindow在某个控件下方,并且可以设置偏移量

        /**
         * parent:父布局
         * gravity：调整popupwinddow在parent中的位置
         */
        // popupWindow.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP, 20, 80);//Gravity重力
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(FaBiaoDuznZiActivity.this, "com.lt.uploadpicdemo.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
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
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");

            photo = ImageUtils.toRoundBitmap(photo); // 这个时候的图片已经被处理成圆形的了
            add.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了

            Log.d("TAG", "imagePath:" + imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
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
}
