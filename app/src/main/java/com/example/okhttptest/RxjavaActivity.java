package com.example.okhttptest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okhttptest.been.GsonBen;
import com.example.okhttptest.been.LoginRequest;
import com.example.okhttptest.been.LoginResponse;
import com.example.okhttptest.https.api;
import com.example.okhttptest.https.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RxJavaActivity";
    private Button btn_function;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        initView();
    }

    private void initView() {
        btn_function= (Button) findViewById(R.id.btn_function);
        btn_function.setOnClickListener(this);
        tv_text= (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_function:
                //        fun1();
                //        fun2();
                        fun3();
//                        fun4(this);
//                fun5();
                break;
        }
    }
    private void fun5() {
        api api = HttpUtils.create().create(com.example.okhttptest.https.api.class);
        api.getAndroidInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GsonBen>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GsonBen gsonBen) {
                        Log.d(TAG, "onNext: "+gsonBen.getResults().get(0).toString());
                        tv_text.setText(gsonBen.getResults().get(0).toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void fun4(final Context context) {
        api api = HttpUtils.create().create(com.example.okhttptest.https.api.class);
        api.Login(new LoginRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void fun3() {
        /*在主线程创建，默认在主线程 同一线程执行*/
    /*    Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is: "+Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                e.onNext(1);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "observer thread is: "+Thread.currentThread().getName());
                Log.d(TAG, "onNext"+integer);

            }
        });*/

        /*通过添加 线程调度 subscribeOn()  observerOn() 来切换线程
        * */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is: " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            e.onNext(1);

                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        })
                .subscribeOn(Schedulers.newThread())//被观察者只能指定一次线程
                .subscribeOn(AndroidSchedulers.mainThread())//其他的无效
                .observeOn(AndroidSchedulers.mainThread())//观察者可以切换多次线程
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "observer main is: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "observer io is: " + Thread.currentThread().getName());

                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "observer thread is: " + Thread.currentThread().getName());
                        Log.d(TAG, "onNext" + integer);

                    }
                });
    }

    private void fun1() {
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                Log.d(TAG, "emit 1");
                e.onNext(1);
                Log.d(TAG, "emit 2");
                e.onNext(2);
                Log.d(TAG, "emit 3");
                e.onNext(3);
                Log.d(TAG, "emit complete");
                e.onComplete();
                Log.d(TAG, "emit 4");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;
            private int i;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
                Log.d(TAG, "disposable=d;");

            }

            @Override
            public void onNext(@NonNull Integer s) {
                Log.d(TAG, "  onNext=" + s);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    /*一旦中断了 观察者将不会接受事件,取消订阅了
                    * */
                    disposable.dispose();
                    Log.d(TAG, "isDisposed : " + disposable.isDisposed());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError");

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");

            }
        });

    }

    private void fun2() {
        /*Consumer 表示只关心 onNext 事件
        * */
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(@NonNull ObservableEmitter e) throws Exception {
                Log.d(TAG, "emit 1");
                e.onNext(1);
                Log.d(TAG, "emit 2");
                e.onNext(2);
                Log.d(TAG, "emit 3");
                e.onNext(3);
                Log.d(TAG, "emit complete");
                e.onComplete();
                Log.d(TAG, "emit 4");
                e.onNext(4);
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                Log.d(TAG, "  onNext=" + o);
            }
        });
    }

}
