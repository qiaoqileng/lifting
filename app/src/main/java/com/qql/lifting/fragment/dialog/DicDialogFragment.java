package com.qql.lifting.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.qql.lifting.R;
import com.qql.lifting.adapter.TagItemAdapter;
import com.qql.lifting.adapter.TagRadioAdapter;
import com.qql.lifting.mvp.contract.DicContract;
import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.lifting.mvp.presenter.DicPresenter;
import com.qql.lifting.utils.LogUtil;
import com.qql.lifting.utils.Utils;
import com.qql.lifting.view.decoration.SpaceItemDecoration;
import com.qql.mylib.fragment.base.BaseDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DicDialogFragment extends BaseDialogFragment<DicContract.View, DicPresenter> implements DicContract.View {
    public static final String KEY_DOMAIN = "key_domain";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pre_img)
    ImageView preImg;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.store)
    TextView store;
    @BindView(R.id.curr_type)
    TextView currType;
    private TagRadioAdapter adapter;
    private OnDicSelectedListener listener;
    private Dic currDic;

    public static DicDialogFragment newInstance(String domain) {
        Bundle b = new Bundle();
        b.putString(KEY_DOMAIN, domain);
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
        if (b != null) {
            String domain = b.getString(KEY_DOMAIN, "");
            mPresenter.getDics(domain);
        }
    }

    @Override
    public void dealDics(List<Dic> dics) {
        if (!Utils.isEmptyList(dics)) {
            currDic = dics.get(0);
            FlexboxLayoutManager layoutManagerHistory = new FlexboxLayoutManager(getContext());
            layoutManagerHistory.setFlexDirection(FlexDirection.ROW);
            layoutManagerHistory.setJustifyContent(JustifyContent.FLEX_START);
            recyclerView.setLayoutManager(layoutManagerHistory);
            recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_4)));

            adapter = new TagRadioAdapter(getContext(),dics);
            recyclerView.setAdapter(adapter);
//            adapter.replaceData(dics);
//            adapter.bindToRecyclerView(recyclerView);
//            adapter.setOnItemClickListener((adapter, view, position) -> {
//                LogUtil.d(position);
//                boolean flag = false;
//                Object obj = view.getTag();
//                if (obj instanceof Boolean){
//                    flag = (boolean) obj;
//                }
//                view.setSelected(!flag);
//                view.setTag(!flag);
//                currDic = (Dic) adapter.getItem(position);
//            });
//            adapter.getViewByPosition(0,R.id.content).setSelected(true);
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
    public void confirm(View v) {
        // TODO: 2019/2/14
        dismiss();
    }

    public void setListener(OnDicSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnDicSelectedListener{
        void onDicSelected(Dic dic);
    }
}
