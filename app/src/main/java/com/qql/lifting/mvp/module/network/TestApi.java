package com.qql.lifting.mvp.module.network;

import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.lifting.mvp.module.entity.Brand;
import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.module.entity.Shop;
import com.qql.lifting.mvp.module.entity.SimpleProduct;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.mylib.mvp.module.response.BaseJsonResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class TestApi implements ApiService {
    @Override
    public Flowable<BaseJsonResponse<Page<BannerBean>>> getBanner() {
        return Flowable.create(e -> {
            BaseJsonResponse<Page<BannerBean>> value = new BaseJsonResponse<>();
            List<BannerBean> banners = new ArrayList<>();
            banners.add(new BannerBean(0,"http://img2.3lian.com/2014/f2/37/d/40.jpg"));
            banners.add(new BannerBean(0,"http://img2.3lian.com/2014/f2/37/d/39.jpg"));
            banners.add(new BannerBean(0,"http://img4.imgtn.bdimg.com/it/u=958691974,197794884&fm=23&gp=0.jpg"));
            banners.add(new BannerBean(0,"http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg"));
            banners.add(new BannerBean(0,"http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"));
            value.setModule(new Page<>(banners));
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<BaseJsonResponse<Page<Product>>> searchProduct(Map<String, String> params) {
        return Flowable.create(e ->{
            BaseJsonResponse<Page<Product>> value = new BaseJsonResponse<>();
            List<Product> list = new ArrayList<>();
            Product product1 = new Product();
            product1.setName("title for Adidas");
            product1.setBrand(new Brand("Adidas","something with Adidas"));
            product1.setCollectCount(135);
            product1.setCollected(true);
            product1.setSendFree(true);
            product1.setLookCount(3356);
            product1.setShop(new Shop("adidas shop"));
            product1.setContent("something with product...");
            product1.setPrice(199F);
            product1.setHeadImg("http://img-ads.csdn.net/2018/201811091452349556.png");
            list.add(product1);
            Product product2 = new Product();
            product2.setName("title for Adidas");
            product2.setBrand(new Brand("Adidas","something with Adidas"));
            product2.setCollectCount(135);
            product2.setLookCount(3356);
            product2.setShop(new Shop("adidas shop"));
            product2.setContent("something with product...");
            product2.setPrice(199F);
            product2.setHeadImg("http://img-ads.csdn.net/2018/201811091452349556.png");
            list.add(product2);
            Product product3 = new Product();
            product3.setName("title for Adidas");
            product3.setCollectCount(135);
            product3.setLookCount(3356);
            product3.setBrand(new Brand("Adidas","something with Adidas"));
            product3.setShop(new Shop("adidas shop"));
            product3.setContent("something with product...");
            product3.setPrice(199F);
            product3.setHeadImg("http://img-ads.csdn.net/2018/201811091452349556.png");
            list.add(product3);

            value.setModule(new Page<>(list));
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<BaseJsonResponse<Page<SimpleProduct>>> getHot() {
        return Flowable.create(e ->{
            BaseJsonResponse<Page<SimpleProduct>> value = new BaseJsonResponse<>();
            List<SimpleProduct> list = new ArrayList<>();
            for (int i=0;i<10;i++){
                list.add(new SimpleProduct((long) i,"hot " + i));
            }
            value.setModule(new Page<>(list));
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        }, BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<BaseJsonResponse<Product>> getProductById(Map<String, String> params) {
        return Flowable.create(e ->{
            BaseJsonResponse<Product> value = new BaseJsonResponse<>();
            Product product = new Product();
            product.setBrand(new Brand("Adidas","something with Adidas"));
            product.setShop(new Shop("adidas shop"));
            product.setContent("something with product...");
            product.setPrice(199F);
            product.setHeadImg("http://img-ads.csdn.net/2018/201811091452349556.png");
            List<String> imgs = new ArrayList<>();
            imgs.add("http://img2.3lian.com/2014/f2/37/d/40.jpg");
            imgs.add("http://img2.3lian.com/2014/f2/37/d/39.jpg");
            imgs.add("http://img4.imgtn.bdimg.com/it/u=958691974,197794884&fm=23&gp=0.jpg");
            imgs.add("http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg");
            imgs.add("http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg");
            product.setImgs(imgs);
            product.setCollectCount(135);
            product.setCollected(true);
            product.setSendFree(true);
            product.setLookCount(3356);
            product.setRealPrice(99F);
            product.setName("Adidas 大头娃娃吃雪糕");
            product.setType("红色 37码");
            value.setModule(product);
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        },BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<BaseJsonResponse<List<Dic>>> getDicsById(Map<String, String> params) {
        return Flowable.create(e->{
            BaseJsonResponse<List<Dic>> value = new BaseJsonResponse<>();
            List<Dic> dics = new ArrayList<>();
            dics.add(new Dic("红色","http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"));
            dics.add(new Dic("绿色","http://img4.imgtn.bdimg.com/it/u=958691974,197794884&fm=23&gp=0.jpg"));
            dics.add(new Dic("白色","http://img2.3lian.com/2014/f2/37/d/39.jpg"));
            value.setModule(dics);
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        },BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<BaseJsonResponse<User>> loginOrRegirst(Map<String, String> params) {
        return Flowable.create(e->{
            BaseJsonResponse<User> value = new BaseJsonResponse<>();
            User user = new User();
            user.setId(1);
            user.setEmail(UUID.randomUUID().toString());
            user.setName(params.get("user.name"));
            user.setHead_url(params.get("user.head_url"));
            user.setSid("xxxxx");
            value.setModule(user);
            value.success = true;
            value.errCode = "0";
            value.errMessage = "";
            e.onNext(value);
            e.onComplete();
        },BackpressureStrategy.BUFFER);
    }
}
