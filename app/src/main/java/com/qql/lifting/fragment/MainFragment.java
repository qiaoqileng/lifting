package com.qql.lifting.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.qql.lifting.R;
import com.qql.lifting.activity.RemindAddActivity;
import com.qql.lifting.adapter.RemindAdapter;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.RemindContract;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.lifting.mvp.presenter.RemindPresenter;
import com.qql.lifting.utils.Utils;
import com.qql.mylib.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment<RemindContract.View, RemindPresenter> implements RemindContract.View {
    @BindView(R.id.tv_month_day)
    TextView tvMonthDay;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_lunar)
    TextView tvLunar;
    @BindView(R.id.ib_calendar)
    ImageView ibCalendar;
    @BindView(R.id.tv_current_day)
    TextView tvCurrentDay;
    @BindView(R.id.fl_current)
    FrameLayout flCurrent;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cl_layout)
    CalendarLayout clLayout;
    private Map<String,List<Remind>> cache = new HashMap<>();
    private RemindAdapter adapter;

    @Override
    protected void initParams(Bundle savedInstanceState) {
        tvMonthDay.setText(String.format(Locale.getDefault(),"%d月%d日", calendarView.getCurMonth(), calendarView.getCurDay()));
        tvYear.setText(String.valueOf(calendarView.getCurYear()));
        tvLunar.setText("今日");
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));
        calendarView.setOnYearChangeListener(year -> tvMonthDay.setText(String.valueOf(year)));
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                tvLunar.setVisibility(View.VISIBLE);
                tvYear.setVisibility(View.VISIBLE);
                tvMonthDay.setText(String.format(Locale.getDefault(),"%d月%d日", calendar.getMonth(), calendar.getDay()));
                tvYear.setText(String.valueOf(calendar.getYear()));
                tvLunar.setText(calendar.getLunar());
                refreshDetail(cache.get(calendar.toString()));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RemindAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Context context = getContext();
            Object remind = adapter1.getItem(position);
            if (context == null || !(remind instanceof Remind)){
                return;
            }
            Utils.jumpApp(context,((Remind)remind).getPkg());
        });
    }

    @Override
    protected void loadData() {
        Map<String, String> params = new HashMap<>();
        mPresenter.getRemind(params);
    }

    private void refreshDetail(List<Remind> remind) {
        if (Utils.isEmptyList(remind)){
            remind = new ArrayList<>();
        }
       if (adapter != null){
           adapter.replaceData(remind);
       }
    }

    @Override
    public void dealData(Page<Remind> remindPage) {
        if (remindPage != null && !Utils.isEmptyList(remindPage.getData())){
            Map<String, Calendar> map = new HashMap<>();
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            for (Remind remind:remindPage.getData()){
                calendar.setTime(remind.getRemindDate());
                Calendar calendar1 = new Calendar();
                calendar1.setYear(calendar.get(java.util.Calendar.YEAR));
                calendar1.setMonth(calendar.get(java.util.Calendar.MONTH) + 1);
                calendar1.setDay(calendar.get(java.util.Calendar.DAY_OF_MONTH));
                calendar1.setSchemeColor(getResources().getColor(R.color.plugin_color_red));
                List<Remind> list = null;
                if (cache.containsKey(calendar1.toString())){
                    list = cache.get(calendar1.toString());
                }
                if (list == null){
                    list = new ArrayList<>();
                }
                list.add(remind);
                map.put(calendar1.toString(),calendar1);
                cache.put(calendar1.toString(), list);
            }
            calendarView.setSchemeDate(map);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected RemindPresenter getPresenter() {
        return new RemindPresenter();
    }

    @OnClick({R.id.tv_month_day, R.id.fl_current,R.id.fab_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_month_day:
                if (!clLayout.isExpand()) {
                    clLayout.expand();
                    return;
                }
                calendarView.showYearSelectLayout(calendarView.getCurYear());
                tvLunar.setVisibility(View.GONE);
                tvYear.setVisibility(View.GONE);
                tvMonthDay.setText(String.valueOf(calendarView.getCurYear()));
                break;
            case R.id.fl_current:
                calendarView.scrollToCurrent();
                break;
            case R.id.fab_add:
                //  2019/2/19
                startActivity(new Intent(getContext(), RemindAddActivity.class));
                break;
        }
    }
}
