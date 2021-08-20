package com.example.dashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, final int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check){

            ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
            items.add(new DynamicRVModel("burger 1", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 2", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 3", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 4", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 5", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 6", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 7", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 8", R.drawable.burger_png,0));
            items.add(new DynamicRVModel("burger 9", R.drawable.burger_png,0));

            updateRecyclerView.callback(position, items);

            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position==0){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("burger 1", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 2", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 3", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 4", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 5", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 6", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 7", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 8", R.drawable.burger_png,0));
                    items.add(new DynamicRVModel("burger 9", R.drawable.burger_png,0));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==1){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("pizza 1", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 2", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 3", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 4", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 5", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 6", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 7", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 8", R.drawable.pizza_png,1));
                    items.add(new DynamicRVModel("pizza 9", R.drawable.pizza_png,1));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==2){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("fries 1", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 2", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 3", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 4", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 5", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 6", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 7", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 8", R.drawable.fries_png,2));
                    items.add(new DynamicRVModel("fries 9", R.drawable.fries_png,2));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==3){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("sandwich 1", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 2", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 3", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 4", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 5", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 6", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 7", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 8", R.drawable.sandwitch_png,3));
                    items.add(new DynamicRVModel("sandwich 9", R.drawable.sandwitch_png,3));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==4){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("dessert 1", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 2", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 3", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 4", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 5", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 6", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 7", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 8", R.drawable.dessert_png,4));
                    items.add(new DynamicRVModel("dessert 9", R.drawable.dessert_png,4));

                    updateRecyclerView.callback(position, items);

                }
            }
        });

        if (select){
            if (position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            select=false;
        }
        else {
            if (row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            }
            else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linearLayour);
        }
    }
}
