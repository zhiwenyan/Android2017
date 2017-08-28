package zhiwenyan.cmccaifu.com.android2017.view.kugou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_first, container, false);
        return view;
    }

}
