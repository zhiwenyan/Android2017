package zhiwenyan.cmccaifu.com.android2017.view.kugou;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yanzhiwen on 2017/8/28.
 */

public class ParallaxFragment extends Fragment implements LayoutInflaterFactory {
    public static final String LAYOUT_ID_KEY = "key";

    public ParallaxFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getArguments().getInt(LAYOUT_ID_KEY);
        //View创建的时候 我们去解析属性
        LayoutInflaterCompat.setFactory(inflater, this);
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        //View都会来这里
        return null;
    }
}
