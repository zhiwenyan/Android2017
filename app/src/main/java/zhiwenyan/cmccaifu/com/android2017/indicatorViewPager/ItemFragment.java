package zhiwenyan.cmccaifu.com.android2017.indicatorViewPager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    private TextView mTv;

    public ItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item2, container, false);
        mTv = (TextView) view.findViewById(R.id.tv);
        String tv = getArguments().getString("title");
        mTv.setText(tv);
        return view;
    }

}
