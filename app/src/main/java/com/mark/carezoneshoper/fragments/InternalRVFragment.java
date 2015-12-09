package com.mark.carezoneshoper.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.adapters.GategoriesAdapter;
import com.mark.carezoneshoper.models.Category;

/**
 * Created by mark on 12/6/15.
 * This fragment is responsible to handle all the items for each category
 *
 */
public class InternalRVFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private EditText edtNewItemField;
    private Button btnAddNewItem;
    private GategoriesAdapter mAdapter;
    private Category mCategory;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.internal_rv_layout, container, false);

        setupRecycler(rootView);
        setupWidgets(rootView);
        return rootView;
    }

    public void addCategory(Category cat){
        mCategory = cat;
    }

    private void setupRecycler(View v){
        mRecyclerView = (RecyclerView)v.findViewById(R.id.rvCategoriesList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new GategoriesAdapter();
        mAdapter.setList(mCategory.getItems());
        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupWidgets(View v){
        edtNewItemField = (EditText) v.findViewById(R.id.tvNewListItem);
        btnAddNewItem = (Button)v.findViewById(R.id.btAddNewListItem);

        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = edtNewItemField.getText().toString();
                edtNewItemField.setText("");
                mAdapter.setCategory(mCategory);
                mCategory.addItem(itemName);
                mAdapter.setList(mCategory.getItems());
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
