package com.qql.lifting.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.fragment.SimpleCardFragment;
import com.qql.lifting.fragment.dialog.DicDialogFragment;
import com.qql.lifting.impl.GlideImageLoader;
import com.qql.lifting.mvp.contract.ProductDetailContract;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.presenter.ProductDetailPresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity<ProductDetailContract.View, ProductDetailPresenter> implements ProductDetailContract.View {
    public static final String KEY_PRODUCT_ID = "key_product_id";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_look_count)
    TextView tvLookCount;
    @BindView(R.id.tv_collect_count)
    TextView tvCollectCount;
    @BindView(R.id.iv_att)
    ImageView ivAtt;
    @BindView(R.id.tv_real_price)
    TextView tvRealPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_send_free)
    ImageView ivSendFree;
    @BindView(R.id.tv_type)
    TextView tvType;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"详情","评价"};
    private DicDialogFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
        long id = getIntent().getLongExtra(KEY_PRODUCT_ID,0);
        mPresenter.getDetail(id);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected ProductDetailPresenter getPresenter() {
        return new ProductDetailPresenter();
    }

    @Override
    public void dealData(Product product) {
        if (product != null) {
//            大图
            banner.setImages(product.getImgs());
            banner.setImageLoader(new GlideImageLoader());
            banner.start();
            tvTitle.setText(product.getName());
            tvLookCount.setText(String.valueOf(product.getLookCount()));
            tvCollectCount.setText(String.valueOf(product.getCollectCount()));
            ivAtt.setImageResource(product.isCollected()?R.mipmap.guanzhu1:R.mipmap.guanzhu);
            ivSendFree.setVisibility(product.isSendFree()?View.VISIBLE:View.GONE);
            tvRealPrice.setText("￥"+product.getRealPrice());
            tvPrice.setText("￥"+product.getPrice());
            tvPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());

            fragments.add(SimpleCardFragment.getInstance("详情"));
            fragments.add(SimpleCardFragment.getInstance("评价"));

            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    return fragments.get(i);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    return titles[position];
                }
            });

            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @OnClick({R.id.tv_type,R.id.iv_go})
    public void type(View v){
        if (fragment == null) {
            Fragment _fragment = getSupportFragmentManager().findFragmentByTag("color_type");
            if (_fragment == null) {
                fragment = DicDialogFragment.newInstance("颜色规格");
            } else {
                fragment = (DicDialogFragment) _fragment;
            }
        }
        fragment.show(getSupportFragmentManager(),"color_type");
    }
}
