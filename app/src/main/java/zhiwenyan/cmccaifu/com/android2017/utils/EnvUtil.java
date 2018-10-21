package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by yanzhiwen on 2017/3/1.
 */

public class EnvUtil {
    private static int sStatusBarHeight;

    public static int getActionBarSize(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return DensityUtil.dp2px(48);
    }

    public static int getStatusBarHeight(Context context) {
        if (sStatusBarHeight == 0) {
            int resourceId =
                    context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                sStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return sStatusBarHeight;
    }

}
