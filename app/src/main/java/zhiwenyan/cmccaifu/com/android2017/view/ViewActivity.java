package zhiwenyan.cmccaifu.com.android2017.view;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.view.CustomProgressBar.CustomProgressBarActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.LineActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.PieActivity;
import zhiwenyan.cmccaifu.com.android2017.view.activity.SimileViewActivity;
import zhiwenyan.cmccaifu.com.android2017.view.canvas.CanvasActivity;
import zhiwenyan.cmccaifu.com.android2017.view.customview.CustomActivity;
import zhiwenyan.cmccaifu.com.android2017.view.matrix.MatrixActivity;
import zhiwenyan.cmccaifu.com.android2017.view.path.BezierActivity;
import zhiwenyan.cmccaifu.com.android2017.view.path.PathActivity;
import zhiwenyan.cmccaifu.com.android2017.view.shapeview.ShapeViewActivity;

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
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10})
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
        }
    }
}
