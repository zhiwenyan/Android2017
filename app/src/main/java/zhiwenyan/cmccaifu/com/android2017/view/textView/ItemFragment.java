package zhiwenyan.cmccaifu.com.android2017.view.textView;


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


    public ItemFragment() {
    }

    public static ItemFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        TextView tv = (TextView) view.findViewById(R.id.text);
        tv.setText(getArguments().getString("title"));
        return view;
    }

}
