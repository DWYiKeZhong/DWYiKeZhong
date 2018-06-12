package com.example.yikezhong.net;

import com.example.yikezhong.bean.CollectionBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/11.
 */

public class CollectionApi {
    private static CollectionApi collectionApi;
    private static CollectionApiService service;

    private CollectionApi(CollectionApiService service) {
        this.service = service;
    }

    public static CollectionApi getCollectionApi(CollectionApiService service) {
        if (collectionApi == null) {
            collectionApi = new CollectionApi(service);
        }
        return collectionApi;
    }

    public Observable<CollectionBean> getCollection(String uid,String token) {
        return service.getCollection(uid,token);
    }
}
