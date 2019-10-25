package com.example.mvpdemo;

public class MvpPresenter {
    /**
     * view接口
     */
    private MvpView mvpView;
    public MvpPresenter(MvpView view){
        this.mvpView=view;
    }
    /**
     * 获取网络数据
     * @parma params 参数
     */
    public void getData(String params){
        //显示正在加载进度条
        mvpView.showLoading();
        //调用Model请求数据
        MvpModel.getNetData(params, new MvpCallback() {
            @Override
            public void onSuccess(String data) {
                //调用view接口显示数据
                mvpView.showData(data);
            }

            @Override
            public void onFailure(String msg) {
                //调用view接口提示失败信息
                mvpView.showFailureMessage(msg);
            }

            @Override
            public void onError() {
                //调用view接口提示出错信息
                mvpView.showErrorMessage();
            }

            @Override
            public void onComplete() {
                //隐藏正在加载进度条
                mvpView.hideLoading();

            }
        });
    }
}
