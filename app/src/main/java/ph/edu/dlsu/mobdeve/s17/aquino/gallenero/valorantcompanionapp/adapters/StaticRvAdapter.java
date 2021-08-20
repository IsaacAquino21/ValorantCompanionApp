package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters;

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

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.DynamicRVModel;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.R;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.StaticRvModel;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces.UpdateRecyclerView;

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
            items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_cypher,0));
            items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_cypher,0));

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
                    items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_cypher,0));
                    items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_cypher,0));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==1){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_reyna,1));
                    items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_reyna,1));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==2){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_viper,2));
                    items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_viper,2));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==3){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_breach,3));
                    items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_breach,3));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==4){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Tip and trick 1", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 2", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 3", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 4", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 5", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 6", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 7", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 8", R.drawable.agent_brimstone,4));
                    items.add(new DynamicRVModel("Tip and trick 9", R.drawable.agent_brimstone,4));

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
