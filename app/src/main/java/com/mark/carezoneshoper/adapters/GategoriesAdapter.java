package com.mark.carezoneshoper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mark.carezoneshoper.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mark on 12/6/15.
 */
public class GategoriesAdapter extends RecyclerView.Adapter<GategoriesAdapter.HV>{
    private List<String> list = new ArrayList<>();
    private ViewGroup parent;

    @Override
    public HV onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gategory_list_item, parent, false);
        this.parent = parent;
        return new HV(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(HV holder, int position) {
        holder.setLabel(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String s){
        list.add(s);
    }



    public static final class HV extends RecyclerView.ViewHolder{
        final TextView label;
        private static Context context;


        public HV(View itemView, Context context) {
            super(itemView);
            this.context = context;
            label = (TextView) itemView.findViewById(R.id.textView);
        }

        private void setLabel(String text){
            label.setText(text);

            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,
                            "This is inside onclick " +  getAdapterPosition(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            label.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(context, "This is inside long "
                            + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }
    }
}
