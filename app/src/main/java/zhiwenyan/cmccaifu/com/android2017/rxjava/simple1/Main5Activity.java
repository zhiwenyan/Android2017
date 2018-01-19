package zhiwenyan.cmccaifu.com.android2017.rxjava.simple1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava.Observable;
import zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava.Observer;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //Observable 被观察者
        //Observer 观察者
        //subscribe 订阅
        Observable.just("url")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
