package com.qql.lifting.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.fragment.HomeFragment;
import com.qql.lifting.fragment.SimpleCardFragment;
import com.qql.lifting.mvp.contract.MainContract;
import com.qql.lifting.mvp.module.entity.AppVersion;
import com.qql.lifting.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract.View{
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
//    private String[] titles = {"首页","消息","我的"};
    private int[] res = {R.drawable.main_home_tab_bg,R.drawable.main_msg_tab_bg,R.drawable.main_my_tab_bg};
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mPresenter.checkVersion();
    }

    private void initView() {
        for (int title:res){
            if (res[0]==title) {
                tabLayout.addTab(tabLayout.newTab(),true);
            } else {
                tabLayout.addTab(tabLayout.newTab());
            }
        }
        fragments.add(new HomeFragment());
        fragments.add(SimpleCardFragment.getInstance("2"));
        fragments.add(SimpleCardFragment.getInstance("3"));

        tabLayout.setupWithViewPager(viewPager,false);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        for (int i=0;i<res.length;i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setIcon(res[i]);
            }
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected boolean useBackNav() {
        return false;
    }

    @Override
    public void getVersion(AppVersion version) {

    }
}
