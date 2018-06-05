package com.example.yikezhong.ui.tuijian_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yikezhong.R;

/**
 * Created   by    Dewey
 * 关注
 */
public class Tui_Guan_Fragment extends Fragment  {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tui_guan_fragment, container, false);
        return view;

    }

}
