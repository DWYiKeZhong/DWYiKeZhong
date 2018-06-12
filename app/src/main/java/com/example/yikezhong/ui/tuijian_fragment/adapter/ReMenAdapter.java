package com.example.yikezhong.ui.tuijian_fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.HotBean;
import com.example.yikezhong.custom.HeartLayout;
import com.example.yikezhong.custom.RotateTextView;
import com.example.yikezhong.ui.activity.UserVideos_DuanDetailActivity;
import com.example.yikezhong.net.Tui_GuanApi;
import com.example.yikezhong.net.Tui_GuanApiService;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created   by   Dewey .
 * 推荐 热门
 * 弹幕 学习：https://blog.csdn.net/u013366008/article/details/77160557
 */
public class ReMenAdapter extends RecyclerView.Adapter<ReMenAdapter.ReMenViewHolder>{
    private Context context;
    private List<HotBean.DataBean> list;
    private int flga=1;
    private  String wid;
    private  String followid1;
    Tui_GuanApiService service = new Tui_GuanApiService() {
        @Override
        public Observable<GuanBean> getGuan(String token, String uid, String followId) {
            return null;
        }
        //11111
        @Override
        public Observable<GuanListBean> getGuanList(String token, String uid) {
            return null;
        }

        @Override
        public Observable<GuanBean> getCollection(String token, String uid, String wid) {
            return null;
        }
    };
    private Tui_GuanApi tui_guanApi=Tui_GuanApi.getGuanApi(service);
    @Inject
    public ReMenAdapter(Tui_GuanApi tui_guanApi) {
        this.tui_guanApi = tui_guanApi;
    }

    public ReMenAdapter(Context context, List<HotBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    //添加数据的方法
    public void addData(List<HotBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReMenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.remen_adapter, null);
        return new ReMenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReMenViewHolder holder, final int position) {
        //设置参数数据
        if (list.get(position).getWorkDesc() == null || list.get(position).getWorkDesc().length() < 0) {
            holder.title.setText("此用户暂无评论");
        } else {
            holder.title.setText(list.get(position).getWorkDesc());
        }

        if (list.get(position).getUser().getNickname() == null || list.get(position).getUser().getNickname().length() < 0) {
            holder.name.setText("匿名用户");
        } else {
            holder.name.setText(list.get(position).getUser().getNickname());
        }
        holder.time.setText(list.get(position).getCreateTime());

        //点击用户头像,传递用户id数据到跳转至用户页面
        holder.headImage.setImageURI(list.get(position).getUser().getIcon());
        holder.headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserVideos_DuanDetailActivity.class);
                intent.putExtra("uid",list.get(position).getUid());
                intent.putExtra("headImage",list.get(position).getUser().getIcon());
                intent.putExtra("name",list.get(position).getUser().getNickname());
                context.startActivity(intent);
            }
        });

        //普通页面播放视频，显示视频标题
        holder.videoPlayer.setUp(list.get(position).getVideoUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
        //为播放视频设置封面图
        Glide.with(context).load(list.get(position).getCover()).into(holder.videoPlayer.thumbImageView);

        //设置倾斜字体显示 神评
        holder.text.setDegrees(28);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class ReMenViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)                       //设置倾斜字体
        RotateTextView text;
        @BindView(R.id.headImage)
        SimpleDraweeView headImage;                //头像
        @BindView(R.id.name)
        TextView name;                             //评论内容
        @BindView(R.id.time)
        TextView time;                             //评论时间
        @BindView(R.id.biaoti)
        TextView title;                            //评论标题
        @BindView(R.id.videoPlayer)
        JZVideoPlayerStandard videoPlayer;         //视频
        private boolean flag = true;
        private boolean showDanmaku;
        private DanmakuContext danmakuContext;     //实例化弹幕上下文对象
        @BindView(R.id.danmaku_view)
        DanmakuView danmakuView;                   //弹幕
        @BindView(R.id.edit_text)
        EditText editText;                         //输入一条评论，添加到弹幕中
        @BindView(R.id.talk_item_floating_love)
        FloatingActionButton talkItemFloatingLove; //自定义view 爱心
        @BindView(R.id.talk_item_floating_talk)
        FloatingActionButton talkItemFloatingTalk; //自定义view 评论
        @BindView(R.id.talk_item_floating_fenxiang)
        FloatingActionButton talkItemFloatingFenxiang;   //自定义view 分享
        @BindView(R.id.talk_item_floating)
        FloatingActionsMenu talkItemFloating;
        @BindView(R.id.collection)
        ImageView collection;                     //收藏
        @BindView(R.id.operation_layout)
        LinearLayout operationLayout;
        @BindView(R.id.send_good)
        ImageView sendGood;                       //屏幕上 送爱心
        @BindView(R.id.heart_layout)
        HeartLayout heartLayout;                  //爱心布局

        public ReMenViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            //3. 设置监听
            initListner();
        }

        //2. 创建弹幕解析，为了简单此处使用默认解析
        BaseDanmakuParser parser = new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return new Danmakus();
            }
        };

        //[通过指定文件流生成弹幕解析, 本例使用的是B站Sample中的弹幕资源]
        private void generateSomeDanmaku() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (showDanmaku) {
                        //随机数间隔时间 添加 弹幕 内容
                        int time = new Random().nextInt(300);
                        String content = "" + time + time;
                        addDanmaku(content);
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        /**
         * [生成默认解析]
         *
         * @type {[type]}
         */
        public BaseDanmakuParser getDefaultDanmakuParser() {
            return new BaseDanmakuParser() {
                @Override
                protected IDanmakus parse() {
                    return new Danmakus();
                }
            };
        }

        @OnClick({R.id.collection, R.id.close, R.id.open, R.id.send, R.id.send_good, R.id.talk_item_floating_love, R.id.talk_item_floating_talk, R.id.talk_item_floating_fenxiang})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.collection:   //点击收藏
                    if (flag == false) {
                        flag = true;
                        collection.setImageResource(R.drawable.star_shi);
                        String token= (String) SharedPreferencesUtils.getParam(context,"token","");
                        String uid= (String) SharedPreferencesUtils.getParam(context,"uid","");
                        for (int i = 0; i <list.size() ; i++) {
                            wid= list.get(i).getWid()+"";
                        }

                        tui_guanApi.getCollection(token, uid, wid)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<GuanBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override

                                    public void onNext(GuanBean guanBean) {
                                        Toast.makeText(context, guanBean.getMsg().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }else {
                        flag = false;
                        collection.setImageResource(R.drawable.star_kong);
                    }

                    break;

                case R.id.close:   //隐藏弹幕
                    if (danmakuView.isShown()) {
                        danmakuView.hide();
                    }
                    break;

                case R.id.open:   //开启弹幕
                    if (!danmakuView.isShown()) {
                        danmakuView.show();
                    } else if (flag) {
                        // 初始化弹幕
                        showDanmaku = true;
                        danmakuView.start();  //开始播放
                        generateSomeDanmaku();
                        flag = false;
                    }
                    break;

                case R.id.send:   //发送评论，弹幕 + 1  点击发送按钮后逻辑的操作
                    String content = editText.getText().toString();
                    if (!TextUtils.isEmpty(content)) {
                        addDanmaku(content);
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                    }
                    break;

                case R.id.send_good:                      //送爱心动画
                    heartLayout.addFavor();
                    break;

                case R.id.talk_item_floating_love:        //点击关注
                    Toast.makeText(context, "关注", Toast.LENGTH_SHORT).show();

                    String token1= (String) SharedPreferencesUtils.getParam(context,"token","");
                    String uid1= (String) SharedPreferencesUtils.getParam(context,"uid","");
                    for (int i = 0; i <list.size() ; i++) {
                        followid1= list.get(i).getWid()+"";
                    }
                    tui_guanApi.getGuan(token1, uid1, followid1)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new Observer<GuanBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override

                                    public void onNext(GuanBean guanBean) {
                                        Toast.makeText(context, guanBean.getMsg().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                @Override
                                public void onComplete() {

                                }
                            });
                    break;

                case R.id.talk_item_floating_talk:        //点击谈论，显示输入框，输入内容
                    if (operationLayout.getVisibility() == View.GONE) {
                        operationLayout.setVisibility(View.VISIBLE);
                    } else {
                        operationLayout.setVisibility(View.GONE);
                    }
                    break;

                case R.id.talk_item_floating_fenxiang:
                    Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }

        //调用了setCallback()方法来设置回调函数
        private void initListner() {

            danmakuView.enableDanmakuDrawingCache(true);

            //设置显示最大行数
            Map<Integer, Integer> maxLines = new HashMap<>();
            maxLines.put(BaseDanmaku.TYPE_SCROLL_RL, 5);

            //设置是否显示重叠
            Map<Integer, Boolean> overMap = new HashMap<>();
            overMap.put(BaseDanmaku.TYPE_SCROLL_RL, true);
            overMap.put(BaseDanmaku.TYPE_FIX_TOP, true);

            //1. 创建一个DanmakuContext，用于配置弹幕的具体属性如：字体、显示行数、是否重叠等等
            danmakuContext = DanmakuContext.create();
            danmakuContext.setScaleTextSize(2.0f);             //弹幕字体缩放
            danmakuContext.setScrollSpeedFactor(1.2f);         //设置滚动速度因子
            danmakuContext.setDuplicateMergingEnabled(true);   //不可重复合并
            danmakuContext.setMaximumLines(maxLines);          //设置最大滚动行
            danmakuContext.preventOverlapping(overMap).setDanmakuMargin(40);

            //4. 弹幕准备，准备结束会在第3步回调，然后开始弹幕
            danmakuView.prepare(parser, danmakuContext);
        }

        //向弹幕view中添加一条弹幕
        private void addDanmaku(String content) {
            //设置显示最大行数
            BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
            Log.e("danmaku弹幕", danmaku + "");
            danmaku.text = content;
            danmaku.padding = 5;
            danmaku.textSize = 25;
            danmaku.textColor = Color.argb(new Random().nextInt(256), new Random().nextInt(256),
                    new Random().nextInt(256), new Random().nextInt(256));
            danmaku.setTime(danmakuView.getCurrentTime());
            danmakuView.addDanmaku(danmaku);
        }

    }
}
