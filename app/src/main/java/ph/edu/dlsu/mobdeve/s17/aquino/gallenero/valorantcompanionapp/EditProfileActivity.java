package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityEditProfileBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Comment;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.Post;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.User;

/**
 * This class is responsible for the edit profile activity of the application
 */
public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String riotId;
    private String tagline;
    private String rank;
    private String region;
    private String agent;
    private ActivityEditProfileBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Set background of view
        getWindow().setBackgroundDrawableResource(R.drawable.editprofile_bg);

        //initialize firebase auth
        mAuth = FirebaseAuth.getInstance();

        //initialize spinners and account details
        initializeSpinners();
        initializeAccountDetails();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = validAccountDetails();

                if(result ==0){
                    updateUser();
                }

            }
        });
    }

    /**
     * This method changes the chosen rank, region, or agent when the user selects a choice using the
     * associated spinners.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_rank:
                this.rank = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spn_region:
                this.region = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spn_agent:
                this.agent = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This method sets the account details of the object to their default value.
     */
    private void initializeAccountDetails(){
        this.riotId = "";
        this.tagline = "";
        this.rank = "Rank";
        this.region = "Region";
        this.agent = "Agent";
    }

    /**
     * This method initializes the spinners using the string array resources for each spinner and
     * sets this class as the listener
     */
    private void initializeSpinners(){
        //Initialize spinners for rank and region
        Spinner spinner1 = binding.spnRank;
        Spinner spinner2 = binding.spnRegion;
        Spinner spinner3 = binding.spnAgent;

        //create adapter for spinners
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
                .createFromResource(this,
                        R.array.ranks, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(this,
                        R.array.regions, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.agents, android.R.layout.simple_spinner_item);

        //setDropDownViewResource of adapters
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set adapters of spinners
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        //set onItemSelectedListener of spinners
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
    }

    /**
     * This method is responsible for validating the account details provided.
     * If any error is found, this methods sets an error to that field if applicable, else puts a
     * toast message instead.
     * @return -1 if a problem is encountered in any of the details, else returns 0.
     */
    private int validAccountDetails(){
        String riotId = binding.etRiotid.getText().toString();
        String tagline = binding.etTagline.getText().toString();

        //empty riot id
        if(riotId.trim().isEmpty()){
            binding.etRiotid.setError("RiotId is Empty!");
            binding.etRiotid.requestFocus();
            return -1;
        }

        //empty tagline
        else if(tagline.trim().isEmpty()){
            binding.etTagline.setError("Tagline is Empty!");
            binding.etTagline.requestFocus();
            return -1;
        }

        //wrong format
        else if(!(tagline.charAt(0) == '#')){
            binding.etTagline.setError("taglines must start with '#'!");
            binding.etTagline.requestFocus();
            return -1;
        }

        //invalid tagline length
        else if(tagline.trim().length() < 5){
            binding.etTagline.setError("Invalid tagline length!");
            binding.etTagline.requestFocus();
            return -1;
        }

        //check if user has selected a rank and region
        if(this.rank.equals("Rank")){
            Toast.makeText(getApplicationContext(),
                    "Please select a rank!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        //no region selected
        else if(this.region.equals("Region")){
            Toast.makeText(getApplicationContext(),
                    "Please select a region!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        //no agent selected
        else if(this.agent.equals("Agent")){
            Toast.makeText(getApplicationContext(),
                    "Please select a main agent!", Toast.LENGTH_SHORT).show();
            return -1;
        }


        //if all are valid
        return 0;
    }

    /**
     * This method is responsible for updating the user using Firebase Auth.
     */
    private void updateUser(){
        String riotId = binding.etRiotid.getText().toString().trim();
        String tagline = binding.etTagline.getText().toString().trim();
        String email = mAuth.getCurrentUser().getEmail();
        User info = new User(riotId, tagline, rank, region, agent, email);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent data = new Intent();
                            data.putExtra("riotId", riotId);
                            data.putExtra("tagline", tagline);
                            data.putExtra("rank", rank);
                            data.putExtra("region", region);
                            data.putExtra("agent", agent);
                            setResult(RESULT_OK, data);
                            finish();
                        }

                        else {
                           Toast.makeText(getApplicationContext(),
                                   "There was a problem updating your profile info",
                                   Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
