package zhiwenyan.cmccaifu.com.android2017.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import butterknife.InjectView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1.IOHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1.IOHandlerFactory;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2.IOFactory;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2.MemoryIOFactory;

import zhiwenyan.cmccaifu.com.android2017.IPC.AidlActivity;
import zhiwenyan.cmccaifu.com.android2017.IPC.MessengerActivity;
import zhiwenyan.cmccaifu.com.android2017.IPC.ServiceActivity;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.RecycleActivity;
import zhiwenyan.cmccaifu.com.android2017.Sensor.SensorActivity;
import zhiwenyan.cmccaifu.com.android2017.Thread.ThreadActivity;
import zhiwenyan.cmccaifu.com.android2017.Thread.ThreadPoolActivity;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.ViewGroupActivity;
import zhiwenyan.cmccaifu.com.android2017.animation.AnimationActivity;
import zhiwenyan.cmccaifu.com.android2017.animator.AnimatorActivity;
import zhiwenyan.cmccaifu.com.android2017.banner.banner.BannerActivity;
import zhiwenyan.cmccaifu.com.android2017.banner.cardViewPager.CardViewPagerActivity;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.behavior.BehaviorActivity;
import zhiwenyan.cmccaifu.com.android2017.cache.ThreeCacheActivity;
import zhiwenyan.cmccaifu.com.android2017.cache.lru.PhotoWallActivity;
import zhiwenyan.cmccaifu.com.android2017.glide.GlideActivity;
import zhiwenyan.cmccaifu.com.android2017.indicatorViewPager.CommonViewPagerActivity;
import zhiwenyan.cmccaifu.com.android2017.materialdesign.DrawActivity;
import zhiwenyan.cmccaifu.com.android2017.materialdesign.ScrollingActivity;
import zhiwenyan.cmccaifu.com.android2017.mvp.MVPActivity;
import zhiwenyan.cmccaifu.com.android2017.net.HttpURLConnectionActivity;
import zhiwenyan.cmccaifu.com.android2017.okhttp.OkhttpActivity;
import zhiwenyan.cmccaifu.com.android2017.retrofit.RetrofitActivity;
import zhiwenyan.cmccaifu.com.android2017.sqlite.SqliteActivity;
import zhiwenyan.cmccaifu.com.android2017.view.ViewActivity;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.propertyAnimTv)
    TextView mPropertyAnimTv;
    @InjectView(R.id.tweenAnimTv)
    TextView mTweenAnimTv;
    @InjectView(R.id.frameAnimTv)
    TextView mrameAnimTv;


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "onResume: " + this.getSharedPreferences("code", Context.MODE_PRIVATE));
        Log.i("TAG", "onResume: " + this.getCacheDir().getAbsolutePath());
        Log.i("TAG", "onResume: " + this.getExternalCacheDir().getAbsolutePath());
        Log.i("TAG", "onResume: " + Environment.getExternalStorageDirectory());
        File file=new File(Environment.getExternalStorageDirectory()+"/"+this.getPackageName()+"/test.apk");
        Log.i("TAG", "onResume: "+file.getPath());
        //简单工厂方法模式
        IOHandler ioHandler = IOHandlerFactory.createIOHandler(IOHandlerFactory.IOType.MEMORY);
        ioHandler.save("userName", "steven");
        String value = ioHandler.getString("userName");
        Log.i("TAG", "onResume: " + value);

        //工厂方法模式
        IOFactory ioFactory = new MemoryIOFactory();
        zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2.IOHandler ioHandler2 = ioFactory.createIOHandler();
        ioHandler2.save("userName", "steven");

        //类
        zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.IOHandler ioHandler3 =
                zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.IOHandlerFactory.getDefaultIOHandler();

        zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.IOHandlerFactory.getmInstance().getDefaultIOHandler();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.propertyAnimTv, R.id.tweenAnimTv, R.id.mvpTv, R.id.glideTv, R.id.threeTv,
            R.id.recylerTv, R.id.threadTv, R.id.threadPoolTv, R.id.frameAnimTv, R.id.httpTv,
            R.id.okHttpTv, R.id.retrofit, R.id.bannerTv, R.id.viewTv, R.id.viewgroupTv, R.id.commonViewPager,
            R.id.threedTv, R.id.lruTv, R.id.drawTv, R.id.coorTv, R.id.messenger, R.id.startService,
            R.id.aidlTv, R.id.behaviorTv, R.id.cardViewPager, R.id.bntv, R.id.device, R.id.sensor
            , R.id.rg, R.id.sqliteTv})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.propertyAnimTv:
                intent = new Intent(this, AnimatorActivity.class);
                startActivity(intent);
                break;
            case R.id.tweenAnimTv:
                intent = new Intent(this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.mvpTv:
                intent = new Intent(this, MVPActivity.class);
                startActivity(intent);
                break;
            case R.id.recylerTv:
                intent = new Intent(this, RecycleActivity.class);
                startActivity(intent);
                break;
            case R.id.behaviorTv:
                intent = new Intent(this, BehaviorActivity.class);
                startActivity(intent);
                break;
            case R.id.threeTv:
                intent = new Intent(this, ThreeCacheActivity.class);
                startActivity(intent);
                break;
            case R.id.threadTv:
                intent = new Intent(this, ThreadActivity.class);
                startActivity(intent);
                break;
            case R.id.threadPoolTv:
                intent = new Intent(this, ThreadPoolActivity.class);
                startActivity(intent);
                break;
            case R.id.httpTv:
                intent = new Intent(this, HttpURLConnectionActivity.class);
                startActivity(intent);
                break;
            case R.id.okHttpTv:
                intent = new Intent(this, OkhttpActivity.class);
                startActivity(intent);
                break;
            case R.id.retrofit:
                intent = new Intent(this, RetrofitActivity.class);
                startActivity(intent);
                break;
            case R.id.glideTv:
                intent = new Intent(this, GlideActivity.class);
                startActivity(intent);
                break;
            case R.id.bannerTv:
                intent = new Intent(this, BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.commonViewPager:
                intent = new Intent(this, CommonViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.viewTv:
                intent = new Intent(this, ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.viewgroupTv:
                intent = new Intent(this, ViewGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.threedTv:
                //     intent = new Intent(this, ThreeDActivity.class);
                //   startActivity(intent);
                break;
            case R.id.lruTv:
                intent = new Intent(this, PhotoWallActivity.class);
                startActivity(intent);
                break;
            case R.id.drawTv:
                intent = new Intent(this, DrawActivity.class);
                startActivity(intent);
                break;
            case R.id.coorTv:
                intent = new Intent(this, ScrollingActivity.class);
                startActivity(intent);
                break;
            case R.id.messenger:
                intent = new Intent(this, MessengerActivity.class);
                startActivity(intent);
                break;
            case R.id.startService:
                intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.aidlTv:
                intent = new Intent(this, AidlActivity.class);
                startActivity(intent);
                break;
            case R.id.cardViewPager:
                intent = new Intent(this, CardViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.bntv:
                intent = new Intent(this, BottomNavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.device:
                intent = new Intent(this, DeviceActivity.class);
                startActivity(intent);
                break;
            case R.id.sensor:
                intent = new Intent(this, SensorActivity.class);
                startActivity(intent);
                break;
            case R.id.rg:
                intent = new Intent(this, RadioActivity.class);
                startActivity(intent);
                break;
            case R.id.sqliteTv:
                intent = new Intent(this, SqliteActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Dialog dialog=new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("消息")
                //在create()之前去设置存放参数
                //create()主要是用于创建我们的Dialog对象，然后把参数取出来s设置给Dialog。
                .create();

//        dialog.show();
    }

    public static String getIP(Context context) {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
