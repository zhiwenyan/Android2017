package zhiwenyan.cmccaifu.com.android2017.view.multiView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;

public class MultiViewActivity extends AppCompatActivity {
    private ListDataScreenView mListDataScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view);
        mListDataScreenView = (ListDataScreenView) findViewById(R.id.listDataScreenView);
        ListMenuAdapter adapter = new ListMenuAdapter(this);
        mListDataScreenView.setAdapter(adapter);
    }
}
