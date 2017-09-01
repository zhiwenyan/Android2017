package zhiwenyan.cmccaifu.com.android2017.view;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.view.BezierCurve.BezierCurveActivity;
import zhiwenyan.cmccaifu.com.android2017.view.CustomProgressBar.CustomProgressBarActivity;
import zhiwenyan.cmccaifu.com.android2017.view.Loading.LoadingViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.IndexActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.LineActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.PasswordActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.PieActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.SimileViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.canvas.CanvasActivity;
import zhiwenyan.cmccaifu.com.android2017.view.coupon.CouponActivity;
import zhiwenyan.cmccaifu.com.android2017.view.customview.CustomActivity;
import zhiwenyan.cmccaifu.com.android2017.view.kugou.KugouActivity;
import zhiwenyan.cmccaifu.com.android2017.view.matrix.MatrixActivity;
import zhiwenyan.cmccaifu.com.android2017.view.multiView.MultiViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.path.BezierActivity;
import zhiwenyan.cmccaifu.com.android2017.view.path.PathActivity;
import zhiwenyan.cmccaifu.com.android2017.view.progressbar.ProgressActivity;
import zhiwenyan.cmccaifu.com.android2017.view.shapeview.ShapeViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.textView.ColorTrackTextViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.touchView.ViewTouchActivity;
import zhiwenyan.cmccaifu.com.android2017.view.yahoo.YahooActivity;

public class ViewActivity extends BaseActivity {


    @Override
    protected void doSetToolBarTitle(String title) {
        super.doSetToolBarTitle("自定义View");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view;
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11,
            R.id.btn12, R.id.btn13, R.id.btn15, R.id.btn16, R.id.btn18, R.id.btn20, R.id.btn21, R.id.btn22,
            R.id.kugou,R.id.yahoo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, SimileViewActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, PieActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, LineActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(this, PathActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(this, BezierActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(this, ShapeViewActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(this, MatrixActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(this, CustomProgressBarActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(this, CustomActivity.class));
                break;
            case R.id.btn11:
                startActivity(new Intent(this, PasswordActivity.class));
                break;
            case R.id.btn12:
                startActivity(new Intent(this, IndexActivity.class));
                break;
            case R.id.btn13:
                startActivity(new Intent(this, ColorTrackTextViewActivity.class));
                break;
            case R.id.btn15:
                startActivity(new Intent(this, ViewTouchActivity.class));
                break;
            case R.id.btn16:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.btn18:
                startActivity(new Intent(this, MultiViewActivity.class));
                break;
            case R.id.btn20:
                startActivity(new Intent(this, CouponActivity.class));
                break;
            case R.id.btn21:
                startActivity(new Intent(this, LoadingViewActivity.class));
                break;
            case R.id.btn22:
                startActivity(new Intent(this, BezierCurveActivity.class));
                break;
            case R.id.kugou:
                startActivity(new Intent(this, KugouActivity.class));
                break;
            case R.id.yahoo:
                startActivity(new Intent(this, YahooActivity.class));
                break;

        }
    }
}
