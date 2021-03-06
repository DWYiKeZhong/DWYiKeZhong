package com.example.yikezhong.ui.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.yikezhong.R;
import com.example.yikezhong.custom.ColorView;
import com.example.yikezhong.custom.GrayFilter;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;

import com.example.yikezhong.ui.utils.ImageUtils;
import com.example.yikezhong.wxapi.MobActivity;

import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//自己做的本地图片滤镜效果+拍照
public class TakePhotoActivity extends MobActivity {
    @BindView(R.id.myColorView)
    ColorView myColorView;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.TiJiaobtn)
    Button TiJiaobtn = null;
    private EditText[] editTextArray = null;
    private float colorArray[] = null;
    private int[] EditTextID = {R.id.Edit1, R.id.Edit2, R.id.Edit3, R.id.Edit4, R.id.Edit5,
            R.id.Edit6, R.id.Edit7, R.id.Edit8, R.id.Edit9, R.id.Edit10,
            R.id.Edit11, R.id.Edit12, R.id.Edit13, R.id.Edit14, R.id.Edit15,
            R.id.Edit16, R.id.Edit17, R.id.Edit18, R.id.Edit19, R.id.Edit20};
    private Bitmap mBitmap;
    //本类是放进某个实体类执行，与ImageUtils配合使用。下面是一些操作符
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    private  int curren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ButterKnife.bind(this);
        curren= (Integer) SharedPreferencesUtils.getParam(TakePhotoActivity.this,"position",0);
        if (curren== Configuration.UI_MODE_NIGHT_NO){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //设置输入内容
        editTextArray = new EditText[20];
        colorArray = new float[20];
        for (int i = 0; i < 20; i++) {
            editTextArray[i] = (EditText) findViewById(EditTextID[i]);
        }

    }

    @OnClick({R.id.backText,R.id.back, R.id.take, R.id.TiJiaobtn, R.id.lookImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backText:
                finish();
                break;

            case R.id.back:
                finish();
                break;

            case R.id.take:     //拍照
                showChoosePicDialog(view);

                break;

            case R.id.TiJiaobtn:        //设置图片颜色矩阵
                for (int i = 0; i < 20; i++) {
                    colorArray[i] = Float.valueOf(editTextArray[i].getText().toString().trim());
                    System.out.println("i = " + i + ":" + editTextArray[i].getText().toString().trim());
                }
                myColorView.setColorArray(colorArray);

                break;

            case R.id.lookImage:  //查看图片滤镜后效果
                String pattern = editText.getText().toString();
                int i = 0;
                if (TextUtils.isEmpty(pattern)) {
                    i = 0;
                } else {
                    i = Integer.parseInt(pattern);
                }

                Resources res = getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.image_01);
                Bitmap newBitmap = GrayFilter.changeToGray(bitmap, i);

                //把添加滤镜后的效果显示在imageview上
                image.setBackground(new BitmapDrawable(getResources(), newBitmap));

                break;

            default:
                    break;
        }
    }

    /**
     * 显示修改头像的对话框
     */
    public void showChoosePicDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置图片");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
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
            tempUri = FileProvider.getUriForFile(TakePhotoActivity.this, "com.lt.uploadpicdemo.fileProvider", file);
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
            myColorView.setImageBitmap(photo);
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
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了

            Log.d("TAG","imagePath:"+imagePath);
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
}
