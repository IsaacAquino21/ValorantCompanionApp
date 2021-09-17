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
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Post;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {
    private ArrayList<Post> posts;
    private ItemClickListener listener;

    public ForumAdapter(ArrayList<Post> posts, ItemClickListener listener){
        this.posts = posts;
        this.listener = listener;
    }

    @Override
    public ForumAdapter.ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        ForumViewHolder forumViewHolder = new ForumViewHolder(view);

        return forumViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForumAdapter.ForumViewHolder holder, int position) {
        String numComments = posts.get(position).getNumComments() + " comments";
        holder.agent.setImageResource(setAgentIcon(posts.get(position).getPosterAgent()));
        holder.riotid.setText(posts.get(position).getPosterRiotID());
        holder.tagline.setText(posts.get(position).getPosterTagline());
        holder.rank.setText(posts.get(position).getPosterRank());
        holder.post_content.setText(posts.get(position).getPostContent());
        holder.num_comments.setText(numComments);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected class ForumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView agent;
        TextView riotid;
        TextView tagline;
        TextView rank;
        TextView post_content;
        TextView num_comments;

        public ForumViewHolder(View view){
            super(view);
            agent = view.findViewById(R.id.iv_agent);
            riotid = view.findViewById(R.id.tv_riotid);
            tagline = view.findViewById(R.id.tv_tagline);
            rank = view.findViewById(R.id.tv_rank);
            post_content = view.findViewById(R.id.tv_post_content);
            num_comments = view.findViewById(R.id.tv_num_comments);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
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
