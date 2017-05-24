package zhiwenyan.cmccaifu.com.android2017.view.activity;

import java.util.ArrayList;

import butterknife.InjectView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.view.pieview.PieData;
import zhiwenyan.cmccaifu.com.android2017.view.pieview.PieView;


public class PieActivity extends BaseActivity {

    @InjectView(R.id.pieView)
    PieView mPieView;

    @Override
    protected void init() {
        super.init();
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("java", 60);
        PieData pieData2 = new PieData("android", 30);
        PieData pieData3 = new PieData("html5", 40);
        PieData pieData4 = new PieData("ios", 20);
        PieData pieData5 = new PieData("php", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        mPieView.setData(datas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pie;
    }

    @Override
    protected void doSetToolBarTitle(String title) {
        super.doSetToolBarTitle("饼图");
    }

}
