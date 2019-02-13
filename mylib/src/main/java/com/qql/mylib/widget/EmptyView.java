package com.qql.mylib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qql.mylib.R;


public class EmptyView extends FrameLayout {

    private TextView mEmptyTextView;
    private DrawableCenterTextView mRefreshButton;
    public static final int EMPTY_TYPE = 1;
    public static final int ERROR_TYPE = 2;
    public static final int LOADING_TYPE= 3;

    private int defaultType = EMPTY_TYPE;
    private View mLoadingProgress;

    public EmptyView(Context context, int defaultType) {
        super(context, null);
        this.defaultType = defaultType;
        init();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View v = View.inflate(getContext(), R.layout.plugin_empty_view, this);
        mEmptyTextView = v.findViewById(R.id.tv_tip);
        mRefreshButton =  v.findViewById(R.id.tv_refresh);
        mLoadingProgress = v.findViewById(R.id.progress_bar_loading);
        if (defaultType == EMPTY_TYPE) {
            mEmptyTextView.setVisibility(View.VISIBLE);
            mRefreshButton.setVisibility(View.GONE);
            mLoadingProgress.setVisibility(GONE);
            setEmptyStatus(R.string.plugin_empty_view_no_data);
        } else if (defaultType == ERROR_TYPE) {
            mEmptyTextView.setVisibility(View.VISIBLE);
            mRefreshButton.setVisibility(View.VISIBLE);
            mLoadingProgress.setVisibility(GONE);
            setReloadState(-1);
        }else if (defaultType == LOADING_TYPE){
            mRefreshButton.setVisibility(GONE);
            mEmptyTextView.setVisibility(GONE);
            mLoadingProgress.setVisibility(VISIBLE);
        }
    }


    /**
     * 设置 List 为无数据状态
     */
    public void setEmptyStatus(int textResId, int textResDrawable) {
        mEmptyTextView.setText(textResId);
        Drawable topDraw = getResources().getDrawable(textResDrawable);
        if (topDraw != null)
            topDraw.setBounds(0, 0, topDraw.getIntrinsicWidth(), topDraw.getIntrinsicHeight());
        mEmptyTextView.setCompoundDrawables(null, topDraw, null, null);

    }

    public void setEmptyStatus(int textRestId) {
        mEmptyTextView.setText(textRestId);
        Drawable topDraw = getResources().getDrawable(R.drawable.plugin_ic_no_data);
        if (topDraw != null)
            topDraw.setBounds(0, 0, topDraw.getIntrinsicWidth(), topDraw.getIntrinsicHeight());
        mEmptyTextView.setCompoundDrawables(null, topDraw, null, null);

    }


    /**
     * 设置 List 为加载失败状态
     */
    public void setReloadState(int resId) {
        if (resId == -1) {
            setEmptyStatus(R.string.plugin_empty_view_network_failure, R.drawable.plugin_ic_no_network);
        } else {
            setEmptyStatus(resId, R.drawable.plugin_ic_no_network);
        }
    }


    public void setRefreshButton(int textRes, int drawableLeftRes, int color, int backgroundRes) {
        if (backgroundRes > 0) {
            mRefreshButton.setBackgroundResource(backgroundRes);
        }
        if (color > 0) {
            mRefreshButton.setTextColor(getResources().getColor(color));
        }
        if (drawableLeftRes > 0) {
            mRefreshButton.setCompoundDrawablesWithIntrinsicBounds(drawableLeftRes, 0, 0, 0);
            mRefreshButton.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        } else {
            mRefreshButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            mRefreshButton.setGravity(Gravity.CENTER);
        }
        if (textRes > 0) {
            mRefreshButton.setText(textRes);
        }
    }

    public void setOnRefreshButtonClickListener(OnClickListener listener) {
        mRefreshButton.setOnClickListener(listener);
    }

    public void showRefreshButton() {
        mRefreshButton.setVisibility(View.VISIBLE);
    }

    public void hiddenRefreshButton() {
        mRefreshButton.setVisibility(View.GONE);
    }
}
