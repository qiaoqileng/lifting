package com.qql.lifting.mvp.module.network;

import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.module.entity.SimpleProduct;
import com.qql.mylib.mvp.module.response.BaseJsonResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Api.GET_BANNER)
    Flowable<BaseJsonResponse<Page<BannerBean>>> getBanner();

    @GET(Api.SEARCH_PRODUCT)
    Flowable<BaseJsonResponse<Page<Product>>> searchProduct(@FieldMap Map<String, String> params);

    @GET(Api.GET_HOT)
    Flowable<BaseJsonResponse<Page<SimpleProduct>>> getHot();

    @GET(Api.GET_PRODUCT_BY_ID)
    Flowable<BaseJsonResponse<Product>> getProductById(@FieldMap Map<String, String> params);

    @GET(Api.GET_DICS_BY_ID)
    Flowable<BaseJsonResponse<List<Dic>>> getDicsById(@FieldMap Map<String, String> params);

}
