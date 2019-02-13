package com.qql.mylib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qql.mylib.R;


/**
 * Created by tongzyang on 2018/10/30.
 */

public class NetErrorView extends FrameLayout {

    public NetErrorView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_net_error_view,this,true);

    }

    public void enableRefresh(View.OnClickListener onRefreshListener){
        View refreshBtn = findViewById(R.id.tv_refresh);
        TextView refreshTip = findViewById(R.id.tv_tip);
        refreshTip.setText(getResources().getString(R.string.network_failure));
        refreshBtn.setVisibility(VISIBLE);
        if(onRefreshListener!=null){
            refreshBtn.setOnClickListener(onRefreshListener);
        }
    }
}
