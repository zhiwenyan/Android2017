package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple4;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yanzhiwen on 2017/11/3.
 * <p>
 * 这个类相当于目标接口 target
 */

public interface AdapterTarget {
     int getCount();

     View getView(int position, ViewGroup parent);
}