package com.qql.lifting.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qql.lifting.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yxf on 2016/12/20.
 */
public final class PhotoPickerUtils {
    public static final int REQUEST_SELECT_IMAGE = PictureConfig.CHOOSE_REQUEST;


    //多张选择
    public static void pickerMultiMode(Object target, int maxCount) {
        PictureSelector pictureSelector = null;
        if (target instanceof Activity) {
            pictureSelector = PictureSelector.create((Activity) target);
        }else if(target instanceof Fragment){
            pictureSelector = PictureSelector.create((Fragment) target);
        }
        if(pictureSelector==null){
            return;
        }
        pictureSelector
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_linkAge_style)
                .imageSpanCount(4)
                .maxSelectNum(maxCount)
                .selectionMode(PictureConfig.MULTIPLE)
                .isCamera(false)
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    //头像选择
    public static void pickerAvatarMode(Context act) {
        if (act instanceof Activity) {
            PictureSelector.create((Activity) act)
                    .openGallery(PictureMimeType.ofImage())
                    .theme(R.style.picture_linkAge_style)
                    .selectionMode(PictureConfig.SINGLE)
                    .enableCrop(true)
                    .hideBottomControls(true)
                    .showCropGrid(false)
                    .withAspectRatio(1, 1)
                    .isCamera(true)
                    .compress(true)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }
    }

    //视频选择
    public static void pickerVideoMode(Context act) {
        if (act instanceof Activity) {
            PictureSelector.create((Activity) act)
                    .openGallery(PictureMimeType.ofVideo())
                    .selectionMode(PictureConfig.SINGLE)
                    .previewVideo(true)
                    .isCamera(false)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }
    }

    //预览视频功能
    public static void previewVideo(Activity act, String videoPath) {
        PictureSelector.create(act).externalPictureVideo(videoPath);
    }

    public static void startCaptureImage(Object target) {
        PictureSelector pictureSelector = null;
        if (target instanceof Activity) {
            pictureSelector = PictureSelector.create((Activity) target);
        } else if (target instanceof Fragment) {
            pictureSelector = PictureSelector.create((Fragment) target);
        }
        if (pictureSelector == null) {
            return;
        }
        pictureSelector.openCamera(PictureMimeType.ofImage())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public static ArrayList<String> getSelectedList(Intent data) {
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        ArrayList<String> select_result = new ArrayList<>();
        for (LocalMedia localMedia : selectList) {
            String path;
            if (localMedia.isCompressed()) {
                path = localMedia.getCompressPath();
            } else if (localMedia.isCut()) {
                path = localMedia.getCutPath();
            } else {
                path = localMedia.getPath();
            }
            select_result.add(path);
        }


        return select_result;
    }


}
