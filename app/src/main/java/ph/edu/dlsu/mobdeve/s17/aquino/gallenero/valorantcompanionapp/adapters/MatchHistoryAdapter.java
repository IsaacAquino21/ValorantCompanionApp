package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.R;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.MatchRecord;

public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder> {
    private ArrayList<MatchRecord> matches;
    private ItemClickListener listener;

    public MatchHistoryAdapter(ArrayList<MatchRecord> matches, ItemClickListener listener) {
        this.matches = matches;
        this.listener = listener;
    }

    @Override
    public MatchHistoryAdapter.MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_matchcard, parent, false);

        MatchViewHolder matchViewHolder = new MatchViewHolder(view);

        return matchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.agent_name.setText(matches.get(position).getAgent());
        holder.agent_pic.setImageResource(setAgentIcon(matches.get(position).getAgent()));

        //win
        if(matches.get(position).getMatchResult().equalsIgnoreCase("WIN")){
            holder.layout.setBackgroundResource(R.drawable.agent_card1);
        }

        //lose
        else if(matches.get(position).getMatchResult().equalsIgnoreCase("LOSE")){
            holder.layout.setBackgroundResource(R.drawable.agent_card2);
        }

        //draw
        else{
            holder.layout.setBackgroundResource(R.drawable.agent_card3);
        }
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public interface ItemClickListener{
        void onClick(View view, int position);
    }

    protected class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView agent_pic;
        TextView agent_name;
        RelativeLayout layout;

        public MatchViewHolder(View view){
            super(view);
            agent_pic = view.findViewById(R.id.iv_rvagent_pic);
            agent_name = view.findViewById(R.id.tv_rvagent);
            layout = view.findViewById(R.id.rl_layout);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

    private int setAgentIcon(String agent){
        //default
        int id = R.drawable.agent_brimstone;

        switch (agent){
            case "Brimstone":
                id = R.drawable.agent_brimstone;
                break;

            case "Phoenix":
                id = R.drawable.agent_phoenix;
                break;

            case "Sage":
                id = R.drawable.agent_sage;
                break;

            case "Sova":
                id = R.drawable.agent_sova;
                break;

            case "Viper":
                id = R.drawable.agent_viper;
                break;

            case "Cypher":
                id = R.drawable.agent_cypher;
                break;

            case "Reyna":
                id = R.drawable.agent_reyna;
                break;

            case "Killjoy":
                id = R.drawable.agent_killjoy;
                break;

            case "Breach":
                id = R.drawable.agent_breach;
                break;

            case "Omen":
                id = R.drawable.agent_omen;
                break;

            case "Jett":
                id = R.drawable.agent_jett;
                break;

            case "Raze":
                id = R.drawable.agent_raze;
                break;

            case "Skye":
                id = R.drawable.agent_skye;
                break;

            case "Astra":
                id = R.drawable.agent_astra;
                break;

            case "Yoru":
                id = R.drawable.agent_yoru;
                break;

            case "Kay/o":
                id = R.drawable.agent_kayo;
                break;
        }

        return id;
    }
}
