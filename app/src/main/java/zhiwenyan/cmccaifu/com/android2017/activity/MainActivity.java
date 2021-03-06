package zhiwenyan.cmccaifu.com.android2017.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Executors;

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
import zhiwenyan.cmccaifu.com.android2017.emoji.EmojiActivity;
import zhiwenyan.cmccaifu.com.android2017.glide.GlideActivity;
import zhiwenyan.cmccaifu.com.android2017.indicatorViewPager.CommonViewPagerActivity;
import zhiwenyan.cmccaifu.com.android2017.materialdesign.DrawActivity;
import zhiwenyan.cmccaifu.com.android2017.materialdesign.ScrollingActivity;
import zhiwenyan.cmccaifu.com.android2017.mvp.MVPActivity;
import zhiwenyan.cmccaifu.com.android2017.net.HttpURLConnectionActivity;
import zhiwenyan.cmccaifu.com.android2017.okhttp.OkhttpActivity;
import zhiwenyan.cmccaifu.com.android2017.retrofit.RetrofitActivity;
import zhiwenyan.cmccaifu.com.android2017.sqlite.SqliteActivity;
import zhiwenyan.cmccaifu.com.android2017.transition.FirstActivity;
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
    private String mEnter;
    private int cache;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // String signParam = SignatureUtils.signatureParams("userName=steven&password=123456");
        // Log.i("TAG", signParam);
        Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();
        ViewGroup parent = (ViewGroup) findViewById(R.id.view_root);

        //EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
        NavigationBar navigationBar = new NavigationBar.Builder(this, R.layout.ui_navigation_bar, parent)
                .setText(R.id.text, "返回")
                .setOnClickListener(R.id.text, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).create();
        //如果想设置字体的大小、颜色，图片、等等
        TextView textView = navigationBar.findById(R.id.text);

        initSparseArray();
        textView.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;  //getByteCount会压缩四倍
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.home_content_bg, options);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        System.out.println("bitmap width=" + width);
        System.out.println("bitmap height=" + height);
        System.out.println("bitmap.getByteCount()=" + bitmap.getByteCount());

        iv.setImageBitmap(bitmap);

        testThreadLocal();

        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(5);
        Executors.newScheduledThreadPool(3);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {

        });
        textView.post(() -> {

        });
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventBus(MessageEvent messageEvent) {
//        System.out.println("MessageEvent=" + messageEvent.message);
//    }


    private ThreadLocal<Boolean> mBooleanThreadLocal = new ThreadLocal<>();

    @SuppressLint("StaticFieldLeak")
    private void testThreadLocal() {
        mBooleanThreadLocal.set(true);
        Log.d("TAG", "run: Main Thread=" + mBooleanThreadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                mBooleanThreadLocal.set(false);
                Log.d("TAG", "run: Thread[1]=" + mBooleanThreadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "run: Thread[2]=" + mBooleanThreadLocal.get());

            }
        }).start();
        //串行
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return null;
            }
        }.execute("");
        //并行
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");

    }

    private void initSparseArray() {
        SparseArray<String> array = new SparseArray<>();
        array.put(1, "s");
        System.out.println(array.indexOfKey(1));
        System.out.println(array.indexOfValue("ss"));
        System.out.println(array.keyAt(0));
        System.out.println(array.valueAt(0));
    }

    private void initWindow() {
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ImageView lv=new ImageView(this);
//        lv.setImageResource(R.mipmap.banner1);
//        mViewRoot.addView(lv,0);
        Log.i("TAG", "onResume: " + this.getSharedPreferences("code", Context.MODE_PRIVATE));
        Log.i("TAG", "onResume: " + this.getFilesDir().getAbsolutePath());
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
        uiautomator();
    }

    private void uiautomator() {

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
            , R.id.dialog, R.id.sqliteTv, R.id.dialogTv, R.id.noticeTv, R.id.designTv, R.id.emojiTv, R.id.kotlin,
            R.id.ui, R.id.tv_transition})
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
            case R.id.emojiTv:
                intent = new Intent(this, EmojiActivity.class);
                startActivity(intent);
                break;
            case R.id.kotlin:
                intent = new Intent(this, KotlinActivity.class);
                startActivity(intent);
                break;
            case R.id.ui:
                break;
            case R.id.tv_transition:
                intent = new Intent(this, FirstActivity.class);
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


    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ，onSaveInstanceState()的调用遵循一个重要原则，即当系统存在“未经你许可”时销毁了我们的activity的可能时，
     * 则onSaveInstanceState()会被系统调用，这是系统的责任，因为它必须要提供一个机会让你保存你的数据（当然你不保存那就随便你了）。
     * 如果调用，调用将发生在onPause()或onStop()方法之前。（虽然测试时发现多数在onPause()前）
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    /**
     * onRestoreInstanceState()被调用的前提是，activity A“确实”被系统销毁了，而如果仅仅是停留在有这种可能性的情况下，
     * 则该方法不会被调用，例如，当正在显示activity A的时候，用户按下HOME键回到主界面，然后用户紧接着又返回到activity A，
     * 这种情况下activity A一般不会因为内存的原因被系统销毁，故activity A的onRestoreInstanceState方法不会被执行 此也说明上二者，大多数情况下不成对被使用。
     * onRestoreInstanceState()在onStart() 和 onPostCreate(Bundle)之间调用。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
