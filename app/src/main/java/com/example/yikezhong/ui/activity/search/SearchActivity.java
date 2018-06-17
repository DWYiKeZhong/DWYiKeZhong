package com.example.yikezhong.ui.activity.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yikezhong.R;
import com.example.yikezhong.bean.SearchBean;
import com.example.yikezhong.component.DaggerHttpComponent;
import com.example.yikezhong.module.HttpModule;
import com.example.yikezhong.ui.activity.search.adapter.SearchAdapter;
import com.example.yikezhong.ui.activity.search.contract.SearchContract;
import com.example.yikezhong.ui.activity.search.presenter.SearchPresenter;
import com.example.yikezhong.ui.base.BaseActivity;
import com.example.yikezhong.ui.shared.SharedPreferencesUtils;
import com.example.yikezhong.ui.utils.DialogUtil;
import com.example.yikezhong.xunfei_util.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.tencent.mm.opensdk.utils.Log;
import com.umeng.analytics.MobclickAgent;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//搜索钟友  集成讯飞语音听写
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    @BindView(R.id.search_search)
    Button searchSearch;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;
    private int curren;

    //显示听写结果
    private TextView textView;

    //语音听写对象
    private SpeechRecognizer speechRecognizer;

    //语音听写UI
    private RecognizerDialog recognizerDialog;

    //是否显示听写UI
    private boolean isShowDialog = true;

    //缓存
    private SharedPreferences sharedPreferences;

    //用hashmap存储听写结果
    private HashMap<String, String> hashMap = new LinkedHashMap<>();

    //引擎类型（云端或本地）
    private String mEngineType = null;

    //函数返回值
    private int ret = 0;

    private Toast toast;

    //  定义Activity的请求码
    private final int PICK_CONTACT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        curren = (Integer) SharedPreferencesUtils.getParam(SearchActivity.this, "position", 0);
        if (curren == Configuration.UI_MODE_NIGHT_NO) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        initView();
        initDate();
    }

    private void initView() {
        textView = findViewById(R.id.search_name);
    }

    private void initDate() {
        //初始化sdk 将自己申请的appid放到下面
        //此句代码应该放在application中的，这里为了方便就直接放代码中了
        SpeechUtility.createUtility(this, "appid=5adff6ad");
        speechRecognizer = SpeechRecognizer.createRecognizer(this, initListener);
        recognizerDialog = new RecognizerDialog(this, initListener);
        sharedPreferences = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        //这里我直接将引擎类型设置为云端，因为本地需要下载讯飞语记，这里为了方便直接使用云端
        //有需要的朋友可以加个单选框 让用户选择云端或本地
        mEngineType = SpeechConstant.TYPE_CLOUD;
    }


    //开始听写
    public void start(View view) {
        textView.setText("");
        hashMap.clear();
        setParams();

        if (isShowDialog) {
            recognizerDialog.setListener(dialogListener);
            recognizerDialog.show();
        } else {
            ret = speechRecognizer.startListening(recognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                Log.e("tag", "听写失败,错误码" + ret);
            }
        }

    }

    //结束听写
    public void stop(View view) {
        Toast.makeText(this, "停止听写", Toast.LENGTH_SHORT).show();
        if (isShowDialog) {
            recognizerDialog.dismiss();
        } else {
            speechRecognizer.stopListening();
        }
    }

    //初始化监听器
    private InitListener initListener = new InitListener() {
        @Override
        public void onInit(int i) {
            if (i != ErrorCode.SUCCESS) {
                Log.e("tag", "初始化失败，错误码" + i);
            }
        }
    };

    //无UI监听器
    private RecognizerListener recognizerListener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(final int i, byte[] bytes) {
            Log.e("tag", "返回数据大小" + bytes.length);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.setText("当前音量" + i);
                }
            });
        }

        @Override
        public void onBeginOfSpeech() {
            Log.e("tag", "开始说话");
        }

        @Override
        public void onEndOfSpeech() {
            Log.e("tag", "结束说话");
        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            if (recognizerResult != null) {
                Log.e("tag", "听写结果：" + recognizerResult.getResultString());
                printResult(recognizerResult);

            }

        }

        @Override
        public void onError(SpeechError speechError) {
            Log.e("tag", "错误信息" + speechError.getPlainDescription(true));

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //  if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //      String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //      Log.d(TAG, "session id =" + sid);
            //  }
        }
    };

    //有UI监听器
    private RecognizerDialogListener dialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            if (recognizerResult != null) {
                Log.e("tag", "听写结果：" + recognizerResult.getResultString());
                printResult(recognizerResult);

            }
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.e("tag", speechError.getPlainDescription(true));

        }
    };

    //输出结果，将返回的json字段解析并在textVie中显示
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        hashMap.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : hashMap.keySet()) {
            resultBuffer.append(hashMap.get(key));
        }

        textView.setText(resultBuffer.toString());
    }

    private void setParams() {
        //清空参数
        speechRecognizer.setParameter(SpeechConstant.PARAMS, null);
        //设置引擎
        speechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        //设置返回数据类型
        speechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");
        //设置中文 普通话
        speechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        speechRecognizer.setParameter(SpeechConstant.ACCENT, "mandarin");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        speechRecognizer.setParameter(SpeechConstant.VAD_BOS,
                sharedPreferences.getString("iat_vadbos_preference", "4000"));

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        speechRecognizer.setParameter(SpeechConstant.VAD_EOS,
                sharedPreferences.getString("iat_vadeos_preference", "1000"));

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        speechRecognizer.setParameter(SpeechConstant.ASR_PTT,
                sharedPreferences.getString("iat_punc_preference", "0"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        speechRecognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        speechRecognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH,
                Environment.getExternalStorageDirectory() + "/msc/iat.wav");

    }


    @Override
    public int getContentLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getSearchSuccess(SearchBean searchBean) {
        DialogUtil.hideProgressDialog();
        searchRv.setLayoutManager(new LinearLayoutManager(this));
        searchRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SearchAdapter adapter = new SearchAdapter(SearchActivity.this, searchBean);
        searchRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.back, R.id.back_text, R.id.query_contacts, R.id.search_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.back_text:
                finish();
                break;

            case R.id.query_contacts:     //查找手机联系人
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,PICK_CONTACT);
                break;

            case R.id.search_search:
                mPresenter.getSearch(textView.getText().toString().trim());
                DialogUtil.showProgressDialog(SearchActivity.this, "提示", "正在加载......");
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PICK_CONTACT:
                if(resultCode == Activity.RESULT_OK){
                    //  获取返回的联系人的Uri信息
                    Uri contactDataUri = data.getData();
                    Cursor cursor = getContentResolver().query(contactDataUri,null,null,null,null);
                    if(cursor.moveToFirst()){
                        //   获得联系人记录的ID
                        String contactId = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts._ID));
                        //  获得联系人的名字
                        String name = cursor.getString(cursor.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "未找到联系人号码";
                        Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.
                                Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.
                                Phone.CONTACT_ID+"="+"?",new String[]{contactId},null);
                        if(phoneCursor.moveToFirst()){
                            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                        }

                        //  关闭查询手机号码的cursor
                        phoneCursor.close();
                        Toast.makeText(SearchActivity.this,name+""+phoneNumber,Toast.LENGTH_SHORT).show();
                    }

                    //  关闭查询联系人信息的cursor
                    cursor.close();
                }
                break;
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
