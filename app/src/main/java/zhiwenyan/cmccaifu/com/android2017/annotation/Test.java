package zhiwenyan.cmccaifu.com.android2017.annotation;

import android.content.res.AssetManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yanzhiwen on 2017/10/26.
 * <p>
 * 反射
 */

public class Test {

    public static void main(String[] args) {
        try {
            TestBean testBean = TestBean.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Constructor constructor = TestBean.class.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);//设置权限
            TestBean testBean1 = ( TestBean ) constructor.newInstance("hhhh");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        try {
            TestBean testBean = new TestBean();
            Method method = TestBean.class.getDeclaredMethod("getName");
            method.setAccessible(true);
            method.invoke(testBean);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //方法
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.setAccessible(true);
            method.invoke(assetManager, "");
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //获取属性
        try {
            TestBean testBean = new TestBean();
            Method method = testBean.getClass().getMethod("getName", null);
            method.invoke(testBean, null);

            Field field = TestBean.class.getDeclaredField("name");
            field.setAccessible(true);
            field.set(testBean, "1212");
            String key = field.getName();
            Object value = field.get(testBean);
            String name = ( String ) field.get(testBean);
            System.out.println("key=" + key + "," + "name" + name + ",value=" + value);
            String fileTypeName = field.getType().getName();
            System.out.println("fileTypeName=" + fileTypeName);
//            Class clz = Class.forName("android.app.ActivityThread");
//            clz.newInstance();
//            Field sCurrentActivityThreadField = clz.getDeclaredField("sCurrentActivityThread");
//            sCurrentActivityThreadField.setAccessible(true);
//            sCurrentActivityThreadField.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
