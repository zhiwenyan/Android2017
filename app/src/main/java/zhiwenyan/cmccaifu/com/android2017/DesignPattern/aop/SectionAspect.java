package zhiwenyan.cmccaifu.com.android2017.DesignPattern.aop;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import zhiwenyan.cmccaifu.com.android2017.Handler.Activity;

/**
 * Created by Steven on 2017/8/27.
 * 处理切点
 */
@Aspect
public class SectionAspect {
    /**
     * 找到处理的切点
     */
    @Pointcut("execution(@zhiwenyan.cmccaifu.com.android2017.DesignPattern.aop.CheckNet * *(..))")
    public void checkNetBehavior() {

    }

    /**
     * 处理切面
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("checkNetBehavior()")
    public Object checkNet(ProceedingJoinPoint joinPoint) throws Throwable {
        //做埋点  日志上传 权限检测 （RxPermission，easyPermission）
        //网络检测
        Log.i("TAG", "检查网络");
        //1 获取CheckNet注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckNet checkNet = signature.getMethod().getAnnotation(CheckNet.class);
        if (checkNet != null) {
            //2 判断有没有网络 怎样获取Context
            Object obj = joinPoint.getThis();  //View Activity Fragment getThis()当前切点所在类
            Context context = getContext(obj);
            if (context != null) {
                if (!isNetworkAvailable(context)) {
                    //3 没有网络往下执行
                    Toast.makeText(context, "请检查你的网路", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        Log.i("TAG", "checkNet: 有网络");
        return joinPoint.proceed();
    }

    private Context getContext(Object obj) {
        if (obj instanceof Activity) {
            return (android.app.Activity) obj;
        } else if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            return fragment.getActivity();
        } else if (obj instanceof View) {
            View view = (View) obj;
            return view.getContext();
        }
        return null;
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */
    private static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
