package com.example.yikezhong.ui.activity.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.GuanBean;
import com.example.yikezhong.bean.GuanListBean;
import com.example.yikezhong.bean.SearchBean;
import com.example.yikezhong.net.Tui_GuanApi;
import com.example.yikezhong.net.Tui_GuanApiService;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 搜索钟友布局
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private SearchBean bean;
    private  String followid;

    public SearchAdapter(Context context, SearchBean bean) {
        this.context = context;
        this.bean = bean;
    }

    Tui_GuanApiService service = new Tui_GuanApiService() {
        @Override
        public Observable<GuanBean> getGuan(String token, String uid, String followId) {
            return null;
        }

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
    public SearchAdapter(Tui_GuanApi tui_guanApi) {
        this.tui_guanApi = tui_guanApi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.search_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        holder.searchsdv.getHierarchy().setRoundingParams(roundingParams);
        holder.searchsdv.setImageURI((String) bean.getData().get(position).getIcon());
        holder.name.setText(bean.getData().get(position).getNickname());

        //点击关注
        holder.guanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "关注", Toast.LENGTH_SHORT).show();

                String token = (String) SharedPreferencesUtils.getParam(context,"token","");
                String uid = (String) SharedPreferencesUtils.getParam(context,"uid","");
                for (int i = 0; i <bean.getData().size() ; i++) {
                    followid = bean.getData().get(i).getUid()+"";
                }
                tui_guanApi.getGuan(token, uid, followid)
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean==null?0:bean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_sdv)
        SimpleDraweeView searchsdv;                //头像
        @BindView(R.id.search__item_name)
        TextView name;
        @BindView(R.id.guanBtn)
        Button guanBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
