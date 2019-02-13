package com.qql.lifting.utils;

import com.qql.lifting.interfaces.IBannerBean;

import java.util.ArrayList;
import java.util.List;

public class BannerUtils {
    public static List<String> transformImgs(List<? extends IBannerBean> datas) {
        if (Utils.isEmptyList(datas)){
            return new ArrayList<>();
        } else {
            List<String> array = new ArrayList<>(datas.size());
            for (int i=0;i<datas.size();i++){
                array.add(datas.get(i).getImgUrl());
            }
            return array;
        }
    }
}
