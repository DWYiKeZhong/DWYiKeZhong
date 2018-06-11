package com.example.yikezhong.ui.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.yikezhong.R;
import com.example.yikezhong.custom.ColorView;
import com.example.yikezhong.custom.GrayFilter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//自己做的本地图片滤镜效果
public class TakePhotoActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ButterKnife.bind(this);

        editTextArray = new EditText[20];
        colorArray = new float[20];
        for (int i = 0; i < 20; i++) {
            editTextArray[i] = (EditText) findViewById(EditTextID[i]);
        }

    }

    @OnClick({R.id.TiJiaobtn, R.id.lookImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
}
