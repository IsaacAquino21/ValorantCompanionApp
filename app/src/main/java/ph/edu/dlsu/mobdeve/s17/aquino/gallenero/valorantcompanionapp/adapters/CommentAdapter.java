package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.R;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces.ItemClickListener;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
    private ArrayList<Comment> comments;

    public CommentAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,
                parent, false);

        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        holder.agent.setImageResource(setAgentIcon(comments.get(position).getCommenterAgent()));
        holder.riotId.setText(comments.get(position).getCommenterRiotID());
        holder.tagline.setText(comments.get(position).getCommenterTagline());
        holder.rank.setText(comments.get(position).getCommenterRank());
        holder.comment.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    protected class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView agent;
        TextView riotId;
        TextView tagline;
        TextView rank;
        TextView comment;

        public CommentViewHolder(View view) {
            super(view);
            agent = view.findViewById(R.id.iv_agent);
            riotId = view.findViewById(R.id.tv_riotid);
            tagline = view.findViewById(R.id.tv_tagline);
            rank = view.findViewById(R.id.tv_rank);
            comment = view.findViewById(R.id.tv_comment);
        }

    }

    private int setAgentIcon(String agent){
        //default
        int id = R.drawable.agent_brimstone_icon;

        switch (agent){
            case "Brimstone":
                id = R.drawable.agent_brimstone_icon;
                break;

            case "Phoenix":
                id = R.drawable.agent_phoenix_icon;
                break;

            case "Sage":
                id = R.drawable.agent_sage_icon;
                break;

            case "Sova":
                id = R.drawable.agent_sova_icon;
                break;

            case "Viper":
                id = R.drawable.agent_viper_icon;
                break;

            case "Cypher":
                id = R.drawable.agent_cypher_icon;
                break;

            case "Reyna":
                id = R.drawable.agent_reyna_icon;
                break;

            case "Killjoy":
                id = R.drawable.agent_killjoy_icon;
                break;

            case "Breach":
                id = R.drawable.agent_breach_icon;
                break;

            case "Omen":
                id = R.drawable.agent_omen_icon;
                break;

            case "Jett":
                id = R.drawable.agent_jett_icon;
                break;

            case "Raze":
                id = R.drawable.agent_raze_icon;
                break;

            case "Skye":
                id = R.drawable.agent_skye_icon;
                break;

            case "Astra":
                id = R.drawable.agent_astra_icon;
                break;

            case "Yoru":
                id = R.drawable.agent_yoru_icon;
                break;

            case "Kay/o":
                id = R.drawable.agent_kayo_icon;
                break;
        }

        return id;
    }
}
