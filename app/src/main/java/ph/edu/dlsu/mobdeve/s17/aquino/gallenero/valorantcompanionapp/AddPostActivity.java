package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityAddPostBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Post;
/**
 * This class is responsible for the add post activity of the application
 */
public class AddPostActivity extends AppCompatActivity {
    ActivityAddPostBinding binding;
    private String uID;
    private String agent;
    private String riotId;
    private String tagline;
    private String rank;
    private String postContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get the passed information about the user.
        initializeAttributes();

        //update views
        initializeUserInfo();

        //listener for the cancel button
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //listener for the post button
        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatePost() != -1){
                    addPost();
                }
            }
        });


    }

    /**
     * This method initializes the attributes of the class using data from the previous activity.
     */
    private void initializeAttributes(){
        Bundle bundle = getIntent().getExtras();
        this.uID = bundle.getString("uId");
        this.agent = bundle.getString("agent");
        this.riotId = bundle.getString("riotid");
        this.tagline = bundle.getString("tagline");
        this.rank = bundle.getString("rank");
    }

    /**
     * This method updates the view using the obtained data
     */
    private void initializeUserInfo(){
        binding.ivAgent.setImageResource(setAgentIcon(agent));
        binding.tvRiotid.setText(riotId);
        binding.tvTagline.setText(tagline);
        binding.tvRank.setText(rank);
    }

    /**
     * This method validates that the post has content. If there is post content, then this method
     * sets the postContent attribute of the class to the String value of the edit text content.
     */
    private int validatePost(){
        if(binding.etPostContent.getText().toString().trim().isEmpty()){
            binding.etPostContent.setError("Please write something!");
            return -1;
        }

        else{
            this.postContent = binding.etPostContent.getText().toString();
        }
        return 0;
    }

    /**
     * This method adds the post to the database
     */
    private void addPost(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String postID = dateFormat.format(date);

        binding.pbProgressBar.setVisibility(View.VISIBLE);
        Post post = new Post(uID, agent, riotId, tagline, rank, postID, postContent);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.child(postID).setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    binding.pbProgressBar.setVisibility(View.GONE);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(), "There was a trouble posting",
                            Toast.LENGTH_SHORT).show();
                    binding.pbProgressBar.setVisibility(View.GONE);
                }

            }
        });
    }

    /**
     * This method sets the agent icon of the page using the String name of the agent.
     * @param agent is the String name of the main agent of the user.
     */
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
