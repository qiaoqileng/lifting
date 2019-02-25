package com.qql.lifting.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.mvp.contract.RemindAddContract;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.lifting.mvp.presenter.RemindAddPresenter;
import com.qql.lifting.utils.ApkUtil;
import com.qql.lifting.utils.DateFormat;
import com.qql.lifting.utils.LogUtil;
import com.qql.lifting.utils.ToastUtils;
import com.qql.lifting.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class RemindAddActivity extends BaseActivity<RemindAddContract.View, RemindAddPresenter> implements RemindAddContract.View {

    private static final int GET_APP_INFO = 1002;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_app)
    TextView tvApp;
    @BindView(R.id.tv_date)
    TextView tvDate;
    private String pkg;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("新增打卡");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (validate()){
            save();
        }
        return super.onMenuItemClick(menuItem);
    }

    private boolean validate() {
        String title = etTitle.getText().toString();
        if (TextUtils.isEmpty(title)){
            ToastUtils.showShortToast("请输入标题",false);
            return false;
        }
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)){
            ToastUtils.showShortToast("请输入内容",false);
            return false;
        }
        if (pkg == null){
            ToastUtils.showShortToast("请选择打卡app",false);
            return false;
        }
        String date = tvDate.getText().toString();
        if (TextUtils.isEmpty(date)){
            ToastUtils.showShortToast("请选择提醒时间",false);
            return false;
        }
        return true;
    }

    private void save(){
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        Remind remind = new Remind();
        remind.setTitle(title);
        remind.setContent(content);
        remind.setPkg(pkg);
        remind.setCreateDate(new Date());
        remind.setTypeName("打卡");
        remind.setRemindDate(calendar.getTime());
        mPresenter.add(remind);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_remind_add;
    }

    @Override
    protected RemindAddPresenter getPresenter() {
        return new RemindAddPresenter();
    }

    @Override
    public void dealResult(boolean success,Remind remind) {
        if (success){
            ToastUtils.showShortToast("success");
            Utils.startRemind(this,remind);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_APP_INFO && resultCode == AppInfoActivity.RESULT_OK && data != null){
            pkg = data.getStringExtra(AppInfoActivity.RESULT_APP_PKG);
            if (pkg != null){
                tvApp.setText(ApkUtil.getNameByPkg(this,pkg));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.tv_app,R.id.tv_date})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_app:
                startActivityForResult(new Intent(this,AppInfoActivity.class),GET_APP_INFO);
                break;
            case R.id.tv_date:
                calendar = Calendar.getInstance(Locale.getDefault());
                if (datePickerDialog == null){
                    datePickerDialog = new DatePickerDialog(this, (view12, year, month, dayOfMonth) -> {
                        calendar.set(year,month,dayOfMonth);
                        if (timePickerDialog == null){
                            timePickerDialog = new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                calendar.set(Calendar.MINUTE,minute);
                                LogUtil.d(calendar.toString());
                                tvDate.setText(DateFormat.getDateAsString(calendar.getTime(),"yyyy-MM-dd HH:mm"));
                            },0,0,true);
                        }
                        timePickerDialog.show();
                    },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                }
                datePickerDialog.show();
                break;
        }
    }
}
