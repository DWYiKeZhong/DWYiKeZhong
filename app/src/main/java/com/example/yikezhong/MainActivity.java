package com.example.yikezhong;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yikezhong.ui.activity.HomeActivity;
import com.example.yikezhong.ui.shared.DaoHang_SharedUtils;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *
 *           佛祖保佑        永无BUG        事业家庭两丰收
 */
//引导页
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.time)
    TextView timetext;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                default:
                    break;
            }
        }
    };
    public static MainActivity instans=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        instans=this;
        //调用工具类判断保存的布尔值
        boolean b = DaoHang_SharedUtils.getBooleanData(MainActivity.this, "flag", false);

        if (b) {    //已经进入过，现在是第二次，进入fragment页面
            handler.sendEmptyMessageDelayed(0, 0);
        } else {        //现在是第一次，进入导航页面
            DaoHang_SharedUtils.savaBooleanData(MainActivity.this, "flag", true);
            TimeAppIntent();
        }
    }

    int time = 3;//首先声明时间初始值：3秒
    //设置定时器任务，更新时间,3秒后跳转页面
    public void TimeAppIntent(){
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                //发送消息，更新时间
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // 为TextView控件赋值
                        timetext.setText(time+"  秒");
                        time--;

                        //当时间为0秒时，执行跳转功能
                        if (time == 0) {
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();            //跳转后销毁当前页面
                        }
                    }
                });
            }
        },1000, 1000);
    }
}
