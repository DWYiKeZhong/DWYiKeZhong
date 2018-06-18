package com.example.yikezhong.ui.activity.upload;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

//上传头像，修改昵称
public class UpLoadActivity extends AppCompatActivity {
    @BindView(R.id.updata_image)
    CircleImageView updataImage;
    @BindView(R.id.edit_nickname)
    EditText editNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fanhui, R.id.updata_image, R.id.overBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;

            case R.id.updata_image:
                break;

            case R.id.overBtn:
                break;

            default:
                break;
        }
    }

//    @Override
//    public void getLoadingDataSuccess(ResponseBody responseBody) {
//        try {
//            String json = responseBody.string();
//            UpLoadBean upLoadBean = new Gson().fromJson(json, UpLoadBean.class);
//            if (upLoadBean.getMsg().equals("文件上传成功")){
//                mainPrester = new MainPrester(this);
//                Map<String, String> map=new HashMap<>();
//                map.put("mobile",SharedPreferencesUtils.getString("mobile"));
//                map.put("password",SharedPreferencesUtils.getString("password"));
//                mainPrester.GetDeng(Constant.denglu,map);
//                Toast.makeText(UpDateNameActivity.this,"更改成功",Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void paiZhao() {
//        Intent intent = new Intent();
//        //指定动作...拍照的动作 CAPTURE...捕获
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        //给相机传递一个指令,,,告诉他拍照之后保存..MediaStore.EXTRA_OUTPUT向外输出的指令,,,指定存放的位置
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pic_path)));
//
//        //拍照的目的是拿到拍的图片
//        startActivityForResult(intent, 1000);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000 && resultCode == RESULT_OK) {
//
//            //拍照保存之后进行裁剪....根据图片的uri路径
//            crop(Uri.fromFile(new File(pic_path)));
//        }
//        if (requestCode == 3000 && resultCode == RESULT_OK) {
//            //获取的是相册里面某一张图片的uri地址
//            Uri uri = data.getData();
//
//            //根据这个uri地址进行裁剪
//            crop(uri);
//        }
//
//        if (requestCode == 2000 && resultCode == RESULT_OK) {
//            //获取到裁剪完的图片
//            Bitmap bitmap = data.getParcelableExtra("data");
//
//            //拿到了bitmap图片 ..需要把bitmap图片压缩保存到文件中去..应该去做上传到服务器的操作了
//            File saveIconFile = new File(crop_icon_path);
//
//            if(saveIconFile.exists()){
//                saveIconFile.delete();
//            }
//
//            try {
//                //创建出新的文件
//                saveIconFile.createNewFile();
//
//                FileOutputStream fos = new FileOutputStream(saveIconFile);
//                //把bitmap通过流的形式压缩到文件中
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                fos.flush();
//                fos.close();
//
//                //保存到sd卡中之后再去把文件上传到服务器
//                //upLoadPicPresenter.uploadPic(ApiUtils.UPLOAD_ICON_URL,saveIconFile,CommonUtils.getString("uid"),"touxiang.jpg");
//                //通过file对象创建一个请求体
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), saveIconFile);
//                MultipartBody.Part part = MultipartBody.Part.createFormData("file", saveIconFile.getName(), requestFile);
//                String uid = SharedPreferencesUtils.getString("uid");
//                String token = SharedPreferencesUtils.getString("token");
//                Map<String,String> map = new HashMap<>();
//                map.put("uid",uid);
//                map.put("token",token);
//
//
//                loadingPresenter.LoadingPhone(map,part);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
}
