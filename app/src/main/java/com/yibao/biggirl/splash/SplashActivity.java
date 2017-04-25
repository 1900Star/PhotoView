package com.yibao.biggirl.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yibao.biggirl.R;
import com.yibao.biggirl.home.MainActivity;
import com.yibao.biggirl.util.SystemUiVisibilityUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 02:00
 */
public class SplashActivity
        extends AppCompatActivity
{


    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    private String url = "http://imgsrc.baidu.com/baike/pic/item/a044ad345982b2b78714197432adcbef77099bf2.jpg";
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mBind = ButterKnife.bind(this);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(), true);
        setSplash();
    }

    private void setSplash() {
        Observable.timer(2, TimeUnit.SECONDS)
                  .subscribe(new Observer<Long>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Long aLong) {
                          startActivity(new Intent(getApplicationContext(), MainActivity.class));
                          SplashActivity.this.finish();
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });


        //        new Handler().postDelayed(() -> {
        //            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        //            SplashActivity.this.finish();
        //
        //        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
