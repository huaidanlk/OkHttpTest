package com.example.okhttptest.mvp;

public class MvpPresenter implements IMvpModel<MvpModel.MvpBeen> {
    private MvpModel mvpModel;
    private IMvpView<MvpModel.MvpBeen> iMvpView;

    public MvpPresenter(IMvpView<MvpModel.MvpBeen> iMvpView) {
        this.iMvpView = iMvpView;
        this.mvpModel = new MvpModel(this);
    }

    @Override
    public void callback(MvpModel.MvpBeen mvpBeen) {
        if (iMvpView == null) return;
        iMvpView.updateView(mvpBeen);
    }

    public void getData() {
        mvpModel.request();
    }

    public void detachView() {
        iMvpView = null;
    }
}
