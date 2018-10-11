package com.example.okhttptest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.okhttptest.R;

/**
 * Created by lk on 2018/7/30.
 */

public class TestFragment extends Fragment {
    private static final String TAG = "TestFragment";

    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: " );
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " );
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        View rootView=inflater.inflate(R.layout.fragment_text,container,false);
        TestChildFragment testChildFragment=TestChildFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.text_container,testChildFragment).commit();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated: " );
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
