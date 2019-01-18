package zhiwenyan.cmccaifu.com.android2017.animator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Description:
 * Data：10/16/2018-5:50 PM
 *
 * @author yanzhiwen
 */
public class PointFEvaluator implements TypeEvaluator<PointF> {
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        //只要能保证：当fraction=0时返回值为startValue，并且当fraction=1时返回值为endValue，就是一个比较合理的函数
        PointF pointF = new PointF();
        pointF.x = startValue.x + fraction * (endValue.x - startValue.x);// x方向匀速移动
        pointF.y = startValue.y + fraction * fraction * (endValue.y - startValue.y);// y方向抛物线加速移动
        return pointF;
    }
}