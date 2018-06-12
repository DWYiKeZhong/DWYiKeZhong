package com.example.yikezhong.ui.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GetUserVideos_DuanDetailBean;
import com.example.yikezhong.custom.HeartLayout;
import com.example.yikezhong.custom.RotateTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * 对应ID用户详情作品集页面  段子详情 Adapter
 */
public class GetUserVideos_DuanDetailAdapter extends RecyclerView.Adapter<GetUserVideos_DuanDetailAdapter.GetUserVideosViewHolder> {
    private Context context;
    private String userName;
    private String image;
    private List<GetUserVideos_DuanDetailBean.DataBean>  list;

    public GetUserVideos_DuanDetailAdapter(Context context, String userName, String image) {
        this.context = context;
        this.userName = userName;
        this.image = image;
    }

    //添加数据的方法
    public void addData(List<GetUserVideos_DuanDetailBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GetUserVideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.getuservideos_adapter, null);
        return new GetUserVideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetUserVideosViewHolder holder, int position) {
        if ( list == null || list.size() <= 0 ){
            holder.name.setText("天蝎喝牛奶");
            holder.title.setText("妹子智斗抢劫男，看到后笑死了~！");
            holder.time.setText("2017-05-23  14:25 ");
            holder.headImage.setImageURI("https://www.zhaoapi.cn/images/quarter/1523262781765%E6%88%AA%E5%B1%8F_20180409_162125.jpg");

            //普通页面播放视频，显示视频标题
            String videoUrl = "https://www.zhaoapi.cn/images/quarter/1517732837250video_tokyo_hot.mp4";
            holder.videoPlayer.setUp(videoUrl, JZVideoPlayer.SCREEN_WINDOW_NORMAL);
            //为播放视频设置封面图
            String videoImage =  "https://www.zhaoapi.cn/images/quarter/1517733899967video_cover_fucking.jpg";
            Glide.with(context).load(videoImage).into(holder.videoPlayer.thumbImageView);

            //设置倾斜字体显示 神评
            holder.text.setDegrees(28);

        }else if (list != null || list.size() > 0){
            //设置参数数据
            if (list.get(position).getWorkDesc() == null ) {
                holder.title.setText("此用户暂无评论");
            } else {
                holder.title.setText(list.get(position).getWorkDesc().toString());
            }

            holder.time.setText(list.get(position).getCreateTime());

            //点击用户头像,传递用户id数据到跳转至用户页面
            holder.headImage.setImageURI(image);
            holder.name.setText(userName);

            //普通页面播放视频，显示视频标题
            holder.videoPlayer.setUp(list.get(position).getVideoUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
            //为播放视频设置封面图
            Glide.with(context).load(list.get(position).getCover()).into(holder.videoPlayer.thumbImageView);

            //设置倾斜字体显示 神评
            holder.text.setDegrees(28);
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class GetUserVideosViewHolder extends RecyclerView.ViewHolder{
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
        @BindView(R.id.heart_layout)
        HeartLayout heartLayout;                  //爱心布局

        public GetUserVideosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

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

        @OnClick({ R.id.close, R.id.open, R.id.send_good, })
        public void onViewClicked(View view) {
            switch (view.getId()) {
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

                case R.id.send_good:                      //送爱心动画
                    heartLayout.addFavor();
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

