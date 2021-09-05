package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityEditprofileBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.User;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String riotId;
    private String tagline;
    private String rank;
    private String region;
    private String agent;
    private ActivityEditprofileBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditprofileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Set background of view
        getWindow().setBackgroundDrawableResource(R.drawable.editprofile_bg);

        //initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        initializeSpinners();
        initializeAccountDetails();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = validAccountDetails();

                if(result ==0){
                    updateInfo();
                }
            }
        });
    }

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

    private void initializeAccountDetails(){
        this.riotId = "";
        this.tagline = "";
        this.rank = "Rank";
        this.region = "Region";
        this.agent = "Agent";
    }

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

        //check if user has selected a rank and region
        if(this.rank.equals("Rank")){
            Toast.makeText(getApplicationContext(),
                    "Please select a rank!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else if(this.region.equals("Region")){
            Toast.makeText(getApplicationContext(),
                    "Please select a region!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else if(this.agent.equals("Agent")){
            Toast.makeText(getApplicationContext(),
                    "Please select a main agent!", Toast.LENGTH_SHORT).show();
            return -1;
        }


        //if all are valid
        return 0;
    }

    private void updateInfo(){
        String riotId = binding.etRiotid.getText().toString().trim();
        String tagline = binding.etTagline.getText().toString().trim();
        String rank = this.rank;
        String region = this.region;
        String agent = this.agent;
        String email = mAuth.getCurrentUser().getEmail();
        User info = new User(riotId,tagline,rank,region,email,agent);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent data = new Intent();
                    data.putExtra("riotId", riotId);
                    data.putExtra("tagline", tagline);
                    data.putExtra("rank", rank);
                    data.putExtra("region", region);
                    data.putExtra("agent", agent);
                    data.putExtra("email", email);
                    setResult(RESULT_OK, data);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(), "There was a problem in updating your data",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
