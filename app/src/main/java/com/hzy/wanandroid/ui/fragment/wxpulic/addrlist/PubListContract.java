package com.hzy.wanandroid.ui.fragment.wxpulic.addrlist;


import com.hzy.wanandroid.base.mvp.BasePersenter;
import com.hzy.wanandroid.base.mvp.BaseView;
import com.hzy.wanandroid.bean.PubAddrListBean;
import com.hzy.wanandroid.http.ResponseBean;

/**
 * Created by hzy on 2019/2/18
 *
 * @author hzy
 *
 * */
public interface PubListContract {

    interface View extends BaseView {
        void updateList(PubAddrListBean bean);
        void updateCollect(ResponseBean responseBean, int position);
        void updateUnCollect(ResponseBean responseBean, int position);
    }

    interface Presenter extends BasePersenter<View> {
        void getList(int id, int page);
        void collectArticle(int id,int position);
        void unCollectArticle(int id,int position);
    }

}
