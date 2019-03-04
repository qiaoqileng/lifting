package com.qql.lifting.fragment.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.qql.lifting.R;
import com.qql.lifting.option.GlideOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PhotoDialogFragment extends DialogFragment {
    private static final String KEY_IMG_PATH = "key_img_path";
    @BindView(R.id.photoView)
    PhotoView photoView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            String path = b.getString(KEY_IMG_PATH);
            if (!TextUtils.isEmpty(path)) {
                Glide.with(this).asBitmap().load(path).apply(GlideOptions.defaultOption()).into(photoView);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    public static PhotoDialogFragment getInstance(String path) {
        PhotoDialogFragment fragment = new PhotoDialogFragment();
        if (!TextUtils.isEmpty(path)) {
            Bundle b = new Bundle();
            b.putString(KEY_IMG_PATH, path);
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.photoView)
    public void onViewClicked() {
        dismiss();
    }
}
