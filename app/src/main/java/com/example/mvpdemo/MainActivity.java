package com.example.mvpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MvpView {
    ProgressDialog progressDialog;
    TextView text;
    MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        //初始化进度条

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");
        //初始化Presenter
        presenter = new MvpPresenter(this);
    }

    //button 点击事件调用方法
    public void getData(View view) {
        presenter.getData("normal");
    }

    //button 点击事件调用方法
    public void getDataForFailure(View view) {
        presenter.getData("failure");
    }

    //button 点击事件调用方法
    public void getDataForError(View view) {
        presenter.getData("error");
        @Override
            //实现mvpview接口
        @Override
        public void showLoading () {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
        @Override
        public void hideLoading () {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
        @Override
        public void showData (String data){
            text.setText(data);

        }
        @Override
        public void showFailureMessage (String msg){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            text.setText(msg);
        }
        @Override
        public void showErrorMessage () {
            Toast.makeText(this, "网络请求数据出现异常", Toast.LENGTH_SHORT).show();
            text.setText("网络请求数据出现异常");
        }
    }
}