package wang.edu.cn.mddemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import java.util.*;

import wang.edu.cn.mddemo.R;
import wang.edu.cn.mddemo.adapter.MainRecyclerAdapter;
import wang.edu.cn.mddemo.application.MyApp;


public class DummyFragment extends Fragment {
    private Context mContext = null;
    private RecyclerView recyclerView_fragment;
    private int tabIndex = 0;
    private List<Map<String, Object>> totalList = new ArrayList<>();

    public static DummyFragment getInstance(int tabindex) {
        DummyFragment fragment = new DummyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tabIndex", tabindex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Bundle bundle = getArguments();
        tabIndex = bundle.getInt("tabIndex");
        switch (tabIndex) {
            case 1:
            case 2:
            case 3:
            case 4:
                totalList = MyApp.loadOutlineData(mContext);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView_fragment = (RecyclerView) inflater.inflate(R.layout.fragment_dummy, container, false);
        return recyclerView_fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView_fragment.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_fragment.setAdapter(new MainRecyclerAdapter(mContext,totalList));
    }
}
