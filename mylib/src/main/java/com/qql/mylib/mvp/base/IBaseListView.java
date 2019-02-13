package com.qql.mylib.mvp.base;

import java.util.List;

/**
 * Created by yxf on 2018/7/17.
 */

public interface IBaseListView<data> extends IBaseView {
     void handleData(List<data> resultDatas, Exception e);
}
