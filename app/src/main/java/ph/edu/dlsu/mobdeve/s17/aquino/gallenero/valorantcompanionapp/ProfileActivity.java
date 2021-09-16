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
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityProfileBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.User;

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
                                String email = mAuth.getCurrentUser().getEmail();
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

    private ActivityResultLauncher<Intent> launchEditPassword =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK){
                                Toast.makeText(getApplicationContext(),
                                        "Successfully changed password!", Toast.LENGTH_SHORT)
                                        .show();
                            }

                            else{
                                Toast.makeText(getApplicationContext(),
                                        "Password change unsuccessful!", Toast.LENGTH_SHORT)
                                        .show();
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
        getUser();

        //update profile info
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoUpdate = new Intent(ProfileActivity.this, EditProfileActivity.class);
                launchEdit.launch(gotoUpdate);
            }
        });

        binding.btnChangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNewPassword = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                launchEditPassword.launch(gotoNewPassword);
            }
        });

        //signout
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

    //method used to set the information obtained from db to the view
    private void setUserInformation(User user){
        binding.tvRiotid.setText(user.getRiotId());
        binding.tvTagline.setText(user.getTagline());
        binding.tvMainAgent.setText(user.getAgent());
        binding.tvRank.setText(user.getRank());
        binding.tvRegion.setText(user.getRegion());
        binding.tvEmail.setText(user.getEmail());
    }

    private void getUser(){
        binding.pbProgressBar.setVisibility(View.VISIBLE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profile = snapshot.getValue(User.class);

                //info of user is found
                if(profile != null){
                    setUserInformation(profile);
                    setAgentIcon(profile.getAgent());
                    setRankIcon(profile.getRank());
                }
                binding.pbProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //method used to set the agent icon in the view
    private void setAgentIcon(String agent){
        switch (agent){
            case "Brimstone":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_brimstone);
                    break;

            case "Phoenix":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_phoenix);
                break;

            case "Sage":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_sage);
                break;

            case "Sova":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_sova);
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
                binding.ivMainagentPic.setImageResource(R.drawable.agent_killjoy);
                break;

            case "Breach":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_breach);
                break;

            case "Omen":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_omen);
                break;

            case "Jett":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_jett);
                break;

            case "Raze":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_raze);
                break;

            case "Skye":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_skye);
                break;

            case "Astra":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_astra);
                break;

            case "Yoru":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_yoru);
                break;

            case "Kay/o":
                binding.ivMainagentPic.setImageResource(R.drawable.agent_kayo);
                break;
        }
    }

    //method used to set the rank icon in the view
    private void setRankIcon(String rank){
        switch (rank){
            case "Unranked":
                binding.ivRankPic.setImageResource(R.drawable.no_rank);
                break;

            case "Iron 1":
                binding.ivRankPic.setImageResource(R.drawable.iron_one);
                break;

            case "Iron 2":
                binding.ivRankPic.setImageResource(R.drawable.iron_two);
                break;

            case "Iron 3":
                binding.ivRankPic.setImageResource(R.drawable.iron_three);
                break;

            case "Bronze 1":
                binding.ivRankPic.setImageResource(R.drawable.bronze_one);
                break;

            case "Bronze 2":
                binding.ivRankPic.setImageResource(R.drawable.bronze_two);
                break;

            case "Bronze 3":
                binding.ivRankPic.setImageResource(R.drawable.bronze_three);
                break;

            case "Silver 1":
                binding.ivRankPic.setImageResource(R.drawable.silver_one);
                break;

            case "Silver 2":
                binding.ivRankPic.setImageResource(R.drawable.silver_two);
                break;

            case "Silver 3":
                binding.ivRankPic.setImageResource(R.drawable.silver_three);
                break;

            case "Gold 1":
                binding.ivRankPic.setImageResource(R.drawable.gold_one);
                break;

            case "Gold 2":
                binding.ivRankPic.setImageResource(R.drawable.gold_two);
                break;

            case "Gold 3":
                binding.ivRankPic.setImageResource(R.drawable.gold_three);
                break;

            case "Platinum 1":
                binding.ivRankPic.setImageResource(R.drawable.plat_one);
                break;

            case "Platinum 2":
                binding.ivRankPic.setImageResource(R.drawable.plat_two);
                break;

            case "Platinum 3":
                binding.ivRankPic.setImageResource(R.drawable.plat_three);
                break;

            case "Diamond 1":
                binding.ivRankPic.setImageResource(R.drawable.dia_one);
                break;

            case "Diamond 2":
                binding.ivRankPic.setImageResource(R.drawable.dia_two);
                break;

            case "Diamond 3":
                binding.ivRankPic.setImageResource(R.drawable.dia_three);
                break;

            case "Immortal":
                binding.ivRankPic.setImageResource(R.drawable.immo_rank);
                break;

            case "Radiant":
                binding.ivRankPic.setImageResource(R.drawable.radiant_rank);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
