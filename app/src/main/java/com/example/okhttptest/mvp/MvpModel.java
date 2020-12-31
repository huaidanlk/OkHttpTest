package com.example.okhttptest.mvp;

public class MvpModel {
    public static class MvpBeen {
        String id;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public IMvpModel<MvpModel.MvpBeen> iMvpModel;

    public MvpModel(IMvpModel<MvpModel.MvpBeen> iMvpModel) {
        this.iMvpModel = iMvpModel;
    }

    public void request() {
        MvpBeen mvpBeen = new MvpBeen();
        mvpBeen.setId("Alex");
        iMvpModel.callback(mvpBeen);
    }
}
