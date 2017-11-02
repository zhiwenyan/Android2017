package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;

public class StevenListViewActivity extends AppCompatActivity {
    private List<String> mDatas = new ArrayList<>();
    private StevenListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steven_list_view);
        mListView = (StevenListView) findViewById(R.id.listView);
        for (int i = 0; i < 100; i++) {
            mDatas.add(i + "");
        }

        for (String item : mDatas) {
            android.widget.TextView itemView =
                    (android.widget.TextView) LayoutInflater.from(this).inflate(R.layout.steven_list_item, null);
            itemView.setText(item);
            mListView.addView(itemView);

        }
    }
}
