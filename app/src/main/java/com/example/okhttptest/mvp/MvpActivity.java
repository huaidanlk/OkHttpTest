package com.example.okhttptest.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.okhttptest.R;

public class MvpActivity extends AppCompatActivity implements IMvpView<MvpModel.MvpBeen> {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MvpActivity.class);
        context.startActivity(intent);
    }


    private TextView mText;
    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mText = (TextView) findViewById(R.id.text);
        mvpPresenter = new MvpPresenter(this);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.getData();
            }
        });
    }

    @Override
    public void updateView(MvpModel.MvpBeen been) {
        mText.setText(been.getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.detachView();
    }
}
