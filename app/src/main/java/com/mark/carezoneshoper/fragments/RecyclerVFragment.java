package com.mark.carezoneshoper.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.adapters.RVAdapter;

/**
 * Created by mark on 12/6/15.
 */
public class RecyclerVFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_layout, container, false);

        setupRecycler(rootView);

        return rootView;
    }

    private void setupRecycler(View v){
        mRecyclerView = (RecyclerView)v.findViewById(R.id.rvCategories);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new RVAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addItem(String s){
        mAdapter.addItem(s);
        mAdapter.notifyDataSetChanged();
    }
}
