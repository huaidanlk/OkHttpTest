package com.example.okhttptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttptest.annotation.ContentView;
import com.example.okhttptest.annotation.Inject;
import com.example.okhttptest.annotation.OnClick;


@ContentView(R.layout.activity_test)
public class AnnotationTestActivity extends AppCompatActivity {
    @Inject(R.id.text)
    TextView text;
    @Inject(R.id.btn)
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject.inject(this);

    }

    @OnClick({R.id.text,R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.text:
                Toast.makeText(this,"test onClick",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(this,"hello word",Toast.LENGTH_SHORT).show();
                Toast.makeText(this,"hello1 word",Toast.LENGTH_SHORT).show();
                break;

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewInject.unInject();
    }
}

