package com.example.yikezhong.net;

import com.example.yikezhong.bean.SearchBean;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/6/12.
 */

public class SearchApi {
    private static SearchApi searchApi;
    private static SearchApiService service;

    private SearchApi(SearchApiService service) {
        this.service = service;
    }

    public static SearchApi getSearchApi(SearchApiService service) {
        if (searchApi == null) {
            searchApi = new SearchApi(service);
        }
        return searchApi;
    }

    public Observable<SearchBean> getSearch(String keywords) {
        return service.getSearch(keywords);
    }
}
