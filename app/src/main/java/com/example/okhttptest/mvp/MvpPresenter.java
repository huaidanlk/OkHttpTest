package com.example.okhttptest.mvp;

public class MvpPresenter implements IMvpModel<MvpModel.MvpBeen> {
    private MvpModel mvpModel;
    private IMvpView iMvpView;

    public MvpPresenter(IMvpView iMvpView) {
        this.iMvpView = iMvpView;
        this.mvpModel = new MvpModel(this);
    }

    @Override
    public void callback(MvpModel.MvpBeen mvpBeen) {
        iMvpView.updateView(mvpBeen);
    }

    public void getData(){
        mvpModel.getData();
    }
}
