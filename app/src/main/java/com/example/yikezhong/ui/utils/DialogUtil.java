package com.example.yikezhong.ui.utils;

import android.app.ProgressDialog;
import android.content.Context;

//网络数据加载慢时，提示
public class DialogUtil {
    private static ProgressDialog progressDialog;

    //网络加载提示与隐藏
    public static void showProgressDialog(Context context,String title, String message) {
        if (progressDialog == null) {

            progressDialog = ProgressDialog.show(context, title,
                    message, true, false);

        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);

        }
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
