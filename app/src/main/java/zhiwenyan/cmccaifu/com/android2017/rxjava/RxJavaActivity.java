package zhiwenyan.cmccaifu.com.android2017.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import zhiwenyan.cmccaifu.com.android2017.R;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }
    private void fun(){
//        Observable.just("1").map(s->s.length()).subscribe(o->Log.i("-", "fun: "+o));
        Observable.just("1").map(new Func1<String, Object>() {

            @Override
            public Object call(String s) {
                return s.length();
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.i("--", "call: "+o);
            }
        });

    }

}
