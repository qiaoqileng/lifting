package com.qql.lifting.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qql.lifting.R;
import com.qql.lifting.mvp.contract.DicContract;
import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.lifting.mvp.presenter.DicPresenter;
import com.qql.lifting.utils.Utils;
import com.qql.mylib.fragment.base.BaseDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DicDialogFragment extends BaseDialogFragment<DicContract.View, DicPresenter> implements DicContract.View {
    public static final String KEY_DOMAIN = "key_domain";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;

    public static DicDialogFragment newInstance(String domain){
        Bundle b = new Bundle();
        b.putString(KEY_DOMAIN,domain);
        DicDialogFragment fragment = new DicDialogFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initParams(Bundle savedInstanceState) {
        Bundle b = getArguments();
        if (b!=null){
            String domain = b.getString(KEY_DOMAIN, "");
            mPresenter.getDics(domain);
        }
    }

    @Override
    public void dealDics(List<Dic> dics) {
        if (!Utils.isEmptyList(dics)){

        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_dialog_dic;
    }

    @Override
    protected DicPresenter getPresenter() {
        return new DicPresenter();
    }

    @OnClick(R.id.tv_confirm)
    public void confirm(View v){
        // TODO: 2019/2/14
    }
}
