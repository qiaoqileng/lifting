package com.qql.lifting.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qql.lifting.R;
import com.qql.lifting.activity.LoginActivity;
import com.qql.lifting.config.UserConfig;
import com.qql.lifting.helper.SPHelper;
import com.qql.lifting.option.GlideOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillUserInfo();
    }

    private void fillUserInfo(){
        String path = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2574774309,856884605&fm=58&s=7A61F148D883984908165216030050DF&bpow=121&bpoh=75";
        if (UserConfig.isLogin()){
            path = UserConfig.getCurrUser().getHead_url();
            String name = UserConfig.getCurrUser().getName();
            tvName.setText(name);
        }
        Glide.with(this).asBitmap()
                .load(path)
                .apply(GlideOptions.circleOption(getResources().getDimensionPixelSize(R.dimen.dp_50)))
                .into(ivHead);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            fillUserInfo();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.iv_head, R.id.tv_all_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                if (!UserConfig.isLogin()){
                    startActivityForResult(new Intent(getContext(), LoginActivity.class),1);
                } else {
                    // TODO: 2019/3/1 用户信息
                }
                break;
            case R.id.tv_all_order:
                break;
        }
    }
}
