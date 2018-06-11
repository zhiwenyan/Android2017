package zhiwenyan.cmccaifu.com.android2017.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：3/23/2018-1:17 PM
 *
 * @author: yanzhiwen
 */
public class PlaceholderFragment extends Fragment {
    private static final String TAG = PlaceholderFragment.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";
    private boolean isInitData;
    private boolean isPrepareView;

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    //fragment懒加载实现的关键在于其的setUserVisibleHint(isVisibleToUser: Boolean)方法，
    //该方法在fragment对用户由可见变为不可见以及由不可见变为可见时都会回调a
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint: " + getArguments().getInt(ARG_SECTION_NUMBER) + "" + isVisibleToUser);
        if (isVisibleToUser) {
            Log.i(TAG, "setUserVisibleHint: " + getArguments().getInt(ARG_SECTION_NUMBER));
            lazyInitData();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: " + getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main8, container, false);
        TextView textView = ( TextView ) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        Log.i(TAG, "onCreateView: " + getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: " + getArguments().getInt(ARG_SECTION_NUMBER));
        isPrepareView = true;

    }

    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: " + getArguments().getInt(ARG_SECTION_NUMBER));
        lazyInitData();
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    private void lazyInitData() {
        if (!isInitData && getUserVisibleHint() && isPrepareView) {
            isInitData = true;
            initData();
        }
    }

    protected void initData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onPause: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: " + getArguments().getInt(ARG_SECTION_NUMBER));

    }
}
