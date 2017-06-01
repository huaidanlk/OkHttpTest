package com.example.okhttptest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.okhttptest.been.LoginRequest;
import com.example.okhttptest.been.LoginResponse;
import com.example.okhttptest.https.API;
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

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        //        fun1();
        //        fun2();
//        fun3();
        fun4(this);
    }

    private void fun4(final Context context) {
        API api= HttpUtils.create().create(API.class);
        api.Login(new LoginRequest())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context,"登录失败",Toast.LENGTH_SHORT).show();

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
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is: " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                e.onNext(1);
            }
        })
                .subscribeOn(Schedulers.newThread())//被观察者只能指定一次线程
                .subscribeOn(AndroidSchedulers.mainThread())
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
