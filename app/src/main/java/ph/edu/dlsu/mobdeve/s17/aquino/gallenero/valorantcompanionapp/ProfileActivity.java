package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityProfileBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.User;

public class ProfileActivity extends AppCompatActivity{
    private ActivityProfileBinding binding;
    private FirebaseAuth mAuth;

    //launcher for EditProfileActivity
    private ActivityResultLauncher<Intent> launchEdit =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK){
                                //get new data
                                Intent newData = result.getData();
                                String riotId = newData.getStringExtra("riotId");
                                String tagline = newData.getStringExtra("tagline");
                                String rank = newData.getStringExtra("rank");
                                String region = newData.getStringExtra("region");
                                String email = newData.getStringExtra("email");
                                String agent = newData.getStringExtra("agent");

                                //create a new User object
                                User user = new User(riotId, tagline, rank, region, email, agent);

                                //update view
                                setUserInformation(user);
                                setAgentIcon(user.getAgent());
                                setRankIcon(user.getRank());

                                //toast to notify user
                                Toast.makeText(getApplicationContext(), "Successfully Updated Info!",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Update Unsuccessful!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //instantiate FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //load info from database to view
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                if(profile != null){
                    setUserInformation(profile);
                    setAgentIcon(profile.getAgent());
                    setRankIcon(profile.getRank());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoUpdate = new Intent(ProfileActivity.this, EditProfileActivity.class);
                launchEdit.launch(gotoUpdate);
            }
        });

        binding.btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setUserInformation(User user){
        binding.tvRiotid.setText(user.getRiotId());
        binding.tvTagline.setText(user.getTagline());
        binding.tvMainAgent.setText(user.getAgent());
        binding.tvRank.setText(user.getRank());
        binding.tvRegion.setText(user.getRegion());
        binding.tvEmail.setText(user.getEmail());
    }

    private void setAgentIcon(String agent){
        switch (agent){
            case "Brimstone":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_brimstone);
                    break;

            case "Phoenix":
                break;

            case "Sage":
                break;

            case "Sova":
                break;

            case "Viper":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_viper);
                break;

            case "Cypher":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_cypher);
                break;

            case "Reyna":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_reyna);
                break;

            case "Killjoy":
                break;

            case "Breach":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_breach);
                break;

            case "Omen":
                break;

            case "Jett":
                break;

            case "Raze":
                break;

            case "Skye":
                break;

            case "Astra":
                break;

            case "Yoru":
                break;

            case "Kay/o":
                break;
        }
    }

    private void setRankIcon(String rank){
        switch (rank){
            case "Unranked":
                break;

            case "Iron 1":
                break;

            case "Iron 2":
                break;

            case "Iron 3":
                break;

            case "Bronze 1":
                break;

            case "Bronze 2":
                break;

            case "Bronze 3":
                break;

            case "Silver 1":
                break;

            case "Silver 2":
                break;

            case "Silver 3":
                break;

            case "Gold 1":
                break;

            case "Gold 2":
                break;

            case "Gold 3":
                break;

            case "Platinum 1":
                break;

            case "Platinum 2":
                break;

            case "Platinum 3":
                break;

            case "Diamond 1":
                break;

            case "Diamond 2":
                break;

            case "Diamond 3":
                break;

            case "Immortal":
                break;

            case "Radiant":
                break;
        }
    }

}
