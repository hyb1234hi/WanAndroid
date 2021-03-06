package com.hzy.wanandroid.ui.activity.pubsearch;

import android.arch.lifecycle.LifecycleOwner;

import com.hzy.wanandroid.App;
import com.hzy.wanandroid.base.mvp.BasePAV;
import com.hzy.wanandroid.service.ApiService;
import com.hzy.wanandroid.utils.RxSchedulers;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

/**
 * Created by hzy on 2019/2/25
 *
 * @author hzy
 *
 * */
public class SearchPresenter extends BasePAV<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    public SearchPresenter() {

    }


    @Override
    public void getData(int id, int page, String k) {
        mView.showLoading();
        App.apiService(ApiService.class)
                .getSearchPub(id, page, k)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mView)))
                .subscribe(responseBean -> {
                    mView.closeLoading();
                    if (responseBean != null) {
                        mView.updateView(responseBean.getData());
                    }
                }, throwable -> {
                    mView.closeLoading();
                    mView.onFail();
                });
    }


    @Override
    public void collectArticle(int id, int position) {
        App.apiService(ApiService.class)
                .insideCollect(id)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mView)))
                .subscribe(responseBean -> {
                    mView.updateCollect(responseBean, position);
                }, throwable -> {
                    mView.onFail();
                });
    }

    @Override
    public void unCollectArticle(int id, int position) {
        App.apiService(ApiService.class)
                .articleListUncollect(id)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mView)))
                .subscribe(responseBean -> {
                    mView.updateUnCollect(responseBean, position);
                }, throwable -> {
                    mView.onFail();
                });

    }

}
