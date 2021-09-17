package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters.CommentAdapter;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityPostSpecificBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces.ItemClickListener;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Comment;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Post;

public class PostSpecificActivity extends AppCompatActivity {
    private ActivityPostSpecificBinding binding;
    private String postId;
    private String posterAgent;
    private String posterRiotId;
    private String posterTagline;
    private String posterRank;
    private String postContent;

    private String userAgent;
    private String userRiotId;
    private String userTagline;
    private String userRank;

    private ArrayList<Comment> comments;
    private CommentAdapter commentsAdapter;
    private boolean noCommentsFlag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostSpecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        comments = new ArrayList<>();

        initializeData();
        setupPost();
        getComments();

        commentsAdapter = new CommentAdapter(comments);
        binding.rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvComments.setAdapter(commentsAdapter);


        binding.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateComment() != -1){
                    addComment();
                    updateCommentCount();
                }
            }
        });

        binding.srlComments.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getComments();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getComments();
    }

    private void initializeData(){
        //get data from previous activity
        Bundle bundle = getIntent().getExtras();

        //post data
        postId = bundle.getString("postId");
        posterAgent = bundle.getString("agent");
        posterRiotId = bundle.getString("riotid");
        posterTagline = bundle.getString("tagline");
        posterRank = bundle.getString("rank");
        postContent = bundle.getString("content");

        //user data
        userAgent = bundle.getString("user_agent");
        userRiotId = bundle.getString("user_riotid");
        userTagline = bundle.getString("user_tagline");
        userRank = bundle.getString("user_rank");
    }

    private void setupPost(){
        binding.ivAgent.setImageResource(setAgentIcon(posterAgent));
        binding.tvRiotid.setText(posterRiotId);
        binding.tvTagline.setText(posterTagline);
        binding.tvRank.setText(posterRank);
        binding.tvPostContent.setText(postContent);
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
    private int validateComment(){
        if(binding.etComment.getText().toString().trim().isEmpty()){
            binding.etComment.setError("Comment cannot be empty!");
            return -1;
        }

        return 0;
    }

    private void getComments(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comments");
        reference.child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(noCommentsFlag){
                        noCommentsFlag = false;
                        showRecyclerView();
                    }
                    comments.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Comment comment = dataSnapshot.getValue(Comment.class);
                        comments.add(comment);
                    }
                    commentsAdapter.notifyDataSetChanged();
                }

                else{
                    hideRecyclerView();
                }
                binding.srlComments.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void addComment(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String commentId = dateFormat.format(date);
        String commentContent = binding.etComment.getText().toString().trim();
        String commenterId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Comment comment = new Comment(commenterId,userAgent, userRiotId, userTagline, userRank,commentContent);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comments");
        reference.child(postId).child(commentId).setValue(comment)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    getComments();
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "There was a problem commenting on this post!",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void updateCommentCount(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Post post = snapshot.getValue(Post.class);
                    HashMap hashMap = new HashMap();
                    hashMap.put("numComments", post.getNumComments() + 1);
                    reference.child(postId).updateChildren(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void hideRecyclerView(){
        binding.rvComments.setVisibility(View.GONE);
        binding.tvNoCommentsLabel.setVisibility(View.VISIBLE);
    }

    private void showRecyclerView(){
        binding.rvComments.setVisibility(View.VISIBLE);
        binding.tvNoCommentsLabel.setVisibility(View.GONE);
    }
}
