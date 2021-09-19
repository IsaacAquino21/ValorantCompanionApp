package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters.ForumAdapter;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityForumBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces.ItemClickListener;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Post;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.User;

/**
 * This class is responsible for the forum page of the application
 */
public class ForumActivity extends AppCompatActivity {
    private ActivityForumBinding binding;
    private ArrayList<Post> posts;
    private ForumAdapter forumAdapter;
    private ItemClickListener listener;
    private FirebaseAuth mAuth;
    private User user;
    private boolean noPostsFlag = true;

    //launcher for the add post activity
    private ActivityResultLauncher<Intent> launchAddPost =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK){
                                if(noPostsFlag){
                                    noPostsFlag = false;
                                    showRecyclerView();
                                }

                                getPosts();
                                Toast.makeText(getApplicationContext(),
                                        "Posted successfully!", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(getApplicationContext(),
                                        "Posting unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForumBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Set title of action bar
        getSupportActionBar().setTitle("Forum");

        //initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //initialize array list for the posts
        posts = new ArrayList<>();

        //get current user details from db
        getUser();

        //set the listener for the posts
        setOnClickListener();

        //get posts from db
        getPosts();

        //setup recycler view
        forumAdapter = new ForumAdapter(posts, listener);
        binding.rvView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvView.setAdapter(forumAdapter);

        //listener for the add post button
        binding.btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddPost = new Intent(ForumActivity.this, AddPostActivity.class);
                gotoAddPost.putExtra("uId", mAuth.getCurrentUser().getUid());
                gotoAddPost.putExtra("agent", user.getAgent());
                gotoAddPost.putExtra("riotid", user.getRiotId());
                gotoAddPost.putExtra("tagline", user.getTagline());
                gotoAddPost.putExtra("rank", user.getRank());
                launchAddPost.launch(gotoAddPost);
            }
        });

        //listener for the swipe up gesture for swipe refresh layout
        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPosts();
            }
        });
    }

    //update posts onStart
    @Override
    protected void onStart() {
        super.onStart();
        getPosts();
    }

    /**
     * This method gets the information of the current user from the db
     */
    private void getUser(){
        binding.pbProgressBar.setVisibility(View.VISIBLE);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User profile = snapshot.getValue(User.class);

                        //info of user is found
                        if(profile != null){
                            setUser(profile);
                            binding.pbProgressBar.setVisibility(View.GONE);
                            binding.btnAddPost.setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    /**
     * This method sets the obtained info from the db to the User instance of the class
     */
    private void setUser(User user){
        this.user = user;
    }

    /**
     * This method sets up the ItemClickListener for each post
     */
    private void setOnClickListener(){
        listener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("Click", "Clicked");
                Intent intent = new Intent(ForumActivity.this, PostSpecificActivity.class);
                intent.putExtra("user_agent", user.getAgent());
                intent.putExtra("user_riotid", user.getRiotId());
                intent.putExtra("user_tagline", user.getTagline());
                intent.putExtra("user_rank", user.getRank());
                intent.putExtra("postId", posts.get(position).getPostID());
                intent.putExtra("agent", posts.get(position).getPosterAgent());
                intent.putExtra("riotid", posts.get(position).getPosterRiotID());
                intent.putExtra("tagline", posts.get(position).getPosterTagline());
                intent.putExtra("rank", posts.get(position).getPosterRank());
                intent.putExtra("content", posts.get(position).getPostContent());
                startActivity(intent);
            }
        };
    }

    /**
     * This method gets the post from the database and updates the view accordingly using the adapter.
     * If no post is found, hides the recycler view and shows the no post message
     */
    private void getPosts(){
        binding.pbProgressBar.setVisibility(View.VISIBLE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    noPostsFlag = false;
                    showRecyclerView();
                    posts.clear();
                    for(DataSnapshot data: snapshot.getChildren()){
                        Post post = data.getValue(Post.class);
                        posts.add(0,post);
                    }
                    forumAdapter.notifyDataSetChanged();
                }

                else{
                    hideRecyclerView();
                }
                binding.swipeLayout.setRefreshing(false);
                binding.pbProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * This method hides the recycler view and shows the no post label
     */
    private void hideRecyclerView(){
        binding.rvView.setVisibility(View.GONE);
        binding.tvNoPostsLabel.setVisibility(View.VISIBLE);
    }

    /**
     * This method shows the recycler view and hides the no post label
     */
    private void showRecyclerView(){
        binding.rvView.setVisibility(View.VISIBLE);
        binding.tvNoPostsLabel.setVisibility(View.GONE);
    }

}
