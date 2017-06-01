package com.example.okhttptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "OkHttpActivity";
    private TextView tv_text;
    private Button btn_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();

    }

    private void initView() {
        tv_text= (TextView) findViewById(R.id.tv_text);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
 /*       Observer<Integer> observer=new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer s) {
                Log.d(TAG,"  onNext="+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
//                sendRequestWithHttpConnection();
                sendRequestWithOkHttp();
                break;
        }
    }
    public void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //OkHttp request
                OkHttpClient client=new OkHttpClient();
                //if post
           /*     RequestBody requestBody=new FormBody.Builder()
                        .add("username","admin")
                        .add("password","123456")
                        .build();
                Request request=new Request.Builder()
                        .url("https://www.baidu.com")
                        .post(requestBody)
                        .build();
*/
                Request request=new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();

                try {
                    //callback
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showResponse(responseData);

                        }
                    });

                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showResponse(responseData);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void sendRequestWithHttpConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url=new URL("https://www.baidu.com");
                    try {
                        connection= (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        InputStream in=connection.getInputStream();

                        reader=new BufferedReader(new InputStreamReader(in));
                        StringBuilder response=new StringBuilder();
                        String line;
                        while ((line=reader.readLine())!=null){
                            response.append(line);
                        }
                        showResponse(response.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(reader!=null){
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(connection!=null){
                            connection.disconnect();
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_text.setText(response);
            }
        });
    }
}
