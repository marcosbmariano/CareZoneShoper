package com.mark.carezoneshoper.adapters;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mark.carezoneshoper.R;
import com.mark.carezoneshoper.fragments.InternalRVFragment;
import com.mark.carezoneshoper.models.Category;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mark on 12/6/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.HV>{
    private static List<Category> list ;

    static{
        list = Category.getCategories();
    }


    @Override
    public HV onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_layout, parent, false);
        return new HV(v, parent.getContext());
    }


    @Override
    public void onBindViewHolder(HV holder, int position) {
        holder.setLabel(list.get(position).getName());
        holder.setCategory(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addCategory( Category cat){
        list.add(cat);
    }


    public static final class HV extends RecyclerView.ViewHolder{
        private TextView label;
        private View itemView;
        private static Context context;
        private boolean toogle = false;
        private FrameLayout newFrameLayout;
        private Category category;


        public HV(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            setupViews(itemView);

        }

        public void setCategory( Category cat){
            category = cat;
        }

        private void setupViews(View v){
            label = (TextView) v.findViewById(R.id.tvCategories);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(toogle){
                        toogle = false;
                        removeListFromLayOut(newFrameLayout);
                    }else{
                        toogle = true;
                        addFragmentList();
                    }
                }
            });
        }

        private void addFragmentList(){
            int id = View.generateViewId();
            newFrameLayout = getNewFrameLayout(context, id);
            addViewToLayout(newFrameLayout);
            InternalRVFragment intFrag = new InternalRVFragment();
            intFrag.addCategory(category);
            addFragment(id, intFrag);
            itemView.getLayoutParams().height = getHeightInDP(300);
        }


        private void setLabel(String text){
            label.setText(text);
        }

        private int getHeightInDP(int pixels){
            final  float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pixels * scale + 0.5f);
        }

        private void addFragment(int layoutId, Fragment fragment){
            FragmentTransaction transaction = getFragmentTransaction(context);
            transaction.add(layoutId, fragment);
            transaction.commit();

        }
        private FragmentTransaction getFragmentTransaction(Context context){
                FragmentManager fragMan =
                        ((AppCompatActivity) context).getSupportFragmentManager();
                return fragMan.beginTransaction();
        }

        private void addViewToLayout(FrameLayout view){
            FrameLayout layout = (FrameLayout)itemView.findViewById(R.id.flCategoryList);
            layout.addView(view);
        }

        private void removeListFromLayOut(View v){
            FrameLayout layout = (FrameLayout)itemView.findViewById(R.id.flCategoryList);
            layout.removeView(v);
            itemView.getLayoutParams().height = getHeightInDP(72);
        }

        private FrameLayout getNewFrameLayout(Context context, int id){
            FrameLayout result = new FrameLayout(context);
            result.setId(id);
            return result;
        }
    }
}
