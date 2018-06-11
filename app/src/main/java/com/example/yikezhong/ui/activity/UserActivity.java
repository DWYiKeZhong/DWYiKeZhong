package com.example.yikezhong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GetUserVideosBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.adapter.GetUserVideosAdapter;
import com.example.yikezhong.ui.activity.contract.GetUserVideosContract;
import com.example.yikezhong.ui.activity.presenter.GetUserVideosPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.utils.DialogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//对应ID用户详情作品集页面
public class UserActivity extends BaseActivity<GetUserVideosPresenter> implements GetUserVideosContract.View {
    @BindView(R.id.zuopin)
    TextView zuopin;
    @BindView(R.id.btn_guanzhu_user)
    Button btnGuanzhuUser;
    @BindView(R.id.zanText)
    TextView zanText;
    @BindView(R.id.userXrecyclerView)
    RecyclerView userXrecyclerView;
    @BindView(R.id.headImage_User)
    SimpleDraweeView headImageUser;
    private Handler handler = new Handler();
    private GetUserVideosAdapter adapter;
    int num = 17;
    int page = 1;

    @Override
    public int getContentLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initView();
    }

    public void initView() {

        //获取传递的数据,在本页面设置显示
        Intent intent = getIntent();
        int uid = intent.getIntExtra("uid", 3881);
        String name = intent.getStringExtra("name");
        String headImage = intent.getStringExtra("headImage");
        headImageUser.setImageURI(headImage);
        System.out.println("uid = " + uid+" name = "+name+" headImage = "+headImage);

        //添加参数,设置布局管理器以及数据适配器
        List<GetUserVideosBean.DataBean> list = new ArrayList<>();
        mPresenter.getUserVideosP(Integer.toString(uid), Integer.toString(page));
        LinearLayoutManager manager = new LinearLayoutManager(UserActivity.this, LinearLayoutManager.VERTICAL, false);
        userXrecyclerView.setLayoutManager(manager);
        userXrecyclerView.addItemDecoration(new DividerItemDecoration(UserActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new GetUserVideosAdapter(UserActivity.this,  name, headImage);
        userXrecyclerView.setAdapter(adapter);

        //网络加载慢时，提示
        DialogUtil.showProgressDialog(UserActivity.this, "提示", "正在加载......");
    }

    @Override
    public void getUserVideosSuccess(GetUserVideosBean getUserVideosBean) {
        if (adapter != null) {
            adapter.addData(getUserVideosBean.getData());
            zuopin.setText(getUserVideosBean.getData().size()+"");

            DialogUtil.hideProgressDialog();     //在此提示数据加载完毕，隐藏提示框
            System.out.println("getUserVideosBean数据不为空："+getUserVideosBean.getData().toString());
        }else {
            System.out.println("getUserVideosBean数据为空："+getUserVideosBean.getData().toString());
        }
    }

    @OnClick({R.id.back_user, R.id.share_user, R.id.talk_user, R.id.headImage_User, R.id.btn_guanzhu_user, R.id.zan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_user:   //返回上一步
                finish();
                break;

            case R.id.share_user:
                Toast.makeText(UserActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;

            case R.id.talk_user:
                Toast.makeText(UserActivity.this, "评论", Toast.LENGTH_SHORT).show();
                break;

            case R.id.headImage_User:
                break;

            case R.id.btn_guanzhu_user:
                btnGuanzhuUser.setText("已关注");
                break;

            case R.id.zan:
                Toast.makeText(UserActivity.this, " 赞 + 1", Toast.LENGTH_SHORT).show();
                zanUser();
                break;

            default:
                break;
        }
    }

    //设置更新任务，更新赞的数量
    public void zanUser() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 为TextView控件赋值
                zanText.setText(Integer.toString(num));
                num++;
            }
        });
    }
}
