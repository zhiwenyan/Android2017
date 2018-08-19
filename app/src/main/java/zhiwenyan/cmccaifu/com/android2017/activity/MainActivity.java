package zhiwenyan.cmccaifu.com.android2017.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.DesignPatternActivity;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.builder.navigation.NavigationBar;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1.IOHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1.IOHandlerFactory;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2.IOFactory;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2.MemoryIOFactory;
import zhiwenyan.cmccaifu.com.android2017.DialogFragment.DialogFragmentActivity;
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
import zhiwenyan.cmccaifu.com.android2017.behavior.NewMessageNotification;
import zhiwenyan.cmccaifu.com.android2017.cache.ThreeCacheActivity;
import zhiwenyan.cmccaifu.com.android2017.cache.lru.PhotoWallActivity;
import zhiwenyan.cmccaifu.com.android2017.dialog.DialogActivity;
import zhiwenyan.cmccaifu.com.android2017.eventbus.eventbus.MessageEvent;
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
import zhiwenyan.cmccaifu.com.androidadvanced.UserAidl;

public class MainActivity extends BaseActivity {
    @BindView(R.id.view_root)
    LinearLayout mViewRoot;
    @BindView(R.id.propertyAnimTv)
    TextView mPropertyAnimTv;
    @BindView(R.id.tweenAnimTv)
    TextView mTweenAnimTv;
    @BindView(R.id.frameAnimTv)
    TextView mrameAnimTv;
    private Map<Integer, String> mMap = new HashMap<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private String mEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup parent = (ViewGroup) findViewById(R.id.view_root);
        EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
        NavigationBar navigationBar = new NavigationBar.Builder(this, R.layout.ui_navigation_bar, parent)
                .setText(R.id.text, "返回")
                .setOnClickListener(R.id.text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).create();
        //如果想设置字体的大小、颜色，图片、等等
        TextView textView = navigationBar.findById(R.id.text);
        DisplayMetrics displayMetrics=new DisplayMetrics();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        ImageView lv=new ImageView(this);
//        lv.setImageResource(R.mipmap.banner1);
//        mViewRoot.addView(lv,0);
        Log.i("TAG", "onResume: " + this.getSharedPreferences("code", Context.MODE_PRIVATE));
        Log.i("TAG", "onResume: " + this.getCacheDir().getAbsolutePath());
        Log.i("TAG", "onResume: " + this.getExternalCacheDir().getAbsolutePath());
        Log.i("TAG", "onResume: " + Environment.getExternalStorageDirectory());
        Log.i("TAG", "onResume: " + Environment.getDownloadCacheDirectory().getPath());
        Log.i("TAG", "onResume: " + Environment.getDataDirectory().getPath());
        File file = new File(Environment.getExternalStorageDirectory() + "/" + this.getPackageName() + "/test.apk");
        Log.i("TAG", "onResume: " + file.getPath());
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
//

        //在写代码时 高扩展 并不是要把所有的的内容和出现的问题都想到，而在新增的功能时候可以保证原来的代码不变
        //对于开发者来说，需要用好最少知识原则，使用者并不需要关注太多

        //日志输出
//      Timber.d("TAG", "log");
        startMessageService();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        ListView listView = new ListView(this);
//        listView.setAdapter();
//      View.inflate(this, R.layout.item_recycler, null);
//        LayoutInflater.from(context).inflate(R.layout.item_recycler, parent);
//        LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        //上面三种方式都是走 LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        List<String> data = new ArrayList<>();
        data.listIterator();
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
            , R.id.dialog, R.id.sqliteTv, R.id.dialogTv, R.id.noticeTv, R.id.designTv})
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
            case R.id.dialog:
                intent = new Intent(this, DialogFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.sqliteTv:
                intent = new Intent(this, SqliteActivity.class);
                startActivity(intent);
                break;
            case R.id.dialogTv:
                intent = new Intent(this, DialogActivity.class);
                startActivity(intent);
                break;
            case R.id.noticeTv:
                NewMessageNotification.notify(this, "Hello Notice", 0);
                break;
            case R.id.designTv:
                intent = new Intent(this, DesignPatternActivity.class);
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
        Dialog dialog = new AlertDialog.Builder(this)
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

    private void startMessageService() {
        //客户端代码
        //跨进程之间通信 Aidl
        //隐式启动
        Intent intent = new Intent();
        intent.setAction("com.fumi.ipc");
        intent.setPackage("zhiwenyan.cmccaifu.com.androidadvanced");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }


    private UserAidl mUserAidl;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //service 是服务端返回给我的
            mUserAidl = UserAidl.Stub.asInterface(service);
            try {
                String userName = mUserAidl.getUserName();
                Log.i("TAG", "onServiceConnected: " + userName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public void updateApp() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(getString(R.string.app_name))
                .setMessage("update")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
