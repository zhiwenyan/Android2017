package zhiwenyan.cmccaifu.com.android2017.view.multiView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.multiScreenView.ListScreenMenuAdapter;

public class MultiViewActivity extends AppCompatActivity {
    private zhiwenyan.cmccaifu.com.android2017.view.multiScreenView.ListDataScreenView mListDataScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view);
        mListDataScreenView = (zhiwenyan.cmccaifu.com.android2017.view.multiScreenView.ListDataScreenView)
                findViewById(R.id.listDataScreenView);
        ListScreenMenuAdapter adapter = new ListScreenMenuAdapter(this);
        mListDataScreenView.setBaseMenuAdapter(adapter);
    }
}
