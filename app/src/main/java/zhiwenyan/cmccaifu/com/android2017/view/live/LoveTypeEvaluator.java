package zhiwenyan.cmccaifu.com.android2017.view.live;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Description:自定义属性动画
 * Data：1/5/2018-11:52 AM
 *
 * @author: yanzhiwen
 */
public class LoveTypeEvaluator implements TypeEvaluator<PointF> {
    private PointF point1, point2;

    public LoveTypeEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * @param t      [0-1]的范围
     * @param point0 startValue
     * @param point3 endValue
     * @return
     */
    @Override

    public PointF evaluate(float t, PointF point0, PointF point3) {
        PointF pointF = new PointF();
        pointF.x = point0.x * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.x * t * (1 - t) * (1 - t)
                + 3 * point2.x * t * (1 - t)
                + point3.x * t * t * t;

        pointF.y = point0.y * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.y * t * (1 - t) * (1 - t)
                + 3 * point2.y * t * (1 - t)
                + point3.y * t * t * t;
        return pointF;
    }
}
