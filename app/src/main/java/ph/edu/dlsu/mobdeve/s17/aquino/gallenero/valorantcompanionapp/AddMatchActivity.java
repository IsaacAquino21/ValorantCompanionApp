package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityAddMatchBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.DataHelper;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.MatchRecord;

public class AddMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityAddMatchBinding binding;
    private String agent;
    private String matchType;
    private String matchResult;
    private int kills;
    private int deaths;
    private int assists;
    private int econRating;
    private int averageCombatScore;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set bg
        getWindow().setBackgroundDrawableResource(R.drawable.addmatch_bg);

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initialize auth
        this.mAuth = FirebaseAuth.getInstance();

        initializeMatchInfo();
        initializeSpinners();

        binding.btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateMatchDetails() != -1){
                    addRecord();
                }
            }
        });
    }

    private void initializeMatchInfo(){
        this.agent = "Agent";
        this.matchType = "Match Type";
        this.matchResult = "Match Result";
        this.kills = 0;
        this.deaths = 0;
        this.assists = 0;
        this.averageCombatScore = 0;
        this.econRating = 0;
    }

    private void initializeSpinners(){
        //Get spinners
        Spinner spinner1 = binding.spnAgent;
        Spinner spinner2 = binding.spnResult;
        Spinner spinner3 = binding.spnType;

        //Create Adapters for spinners
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
                .createFromResource(this,
                        R.array.agents, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(this,
                        R.array.match_results, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter
                .createFromResource(this,
                        R.array.matches, android.R.layout.simple_spinner_item);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_result:
                this.matchResult = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spn_type:
                this.matchType = parent.getItemAtPosition(position).toString();
                break;

            case R.id.spn_agent:
                this.agent = parent.getItemAtPosition(position).toString();
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private int validateMatchDetails(){
        if(this.agent.equalsIgnoreCase("Agent")){
            Toast.makeText(getApplicationContext(),
                    "Please select agent used!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else if(this.matchType.equalsIgnoreCase("Match Type")){
            Toast.makeText(getApplicationContext(),
                    "Please select match type!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else if(this.matchResult.equalsIgnoreCase("Match Result")){
            Toast.makeText(getApplicationContext(),
                    "Please select match result!", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else if(binding.etKills.getText().toString().isEmpty()){
            binding.etKills.setError("Enter number of kills!");
            return -1;
        }

        else if(binding.etDeaths.getText().toString().isEmpty()){
            binding.etKills.setError("Enter number of deaths!");
            return -1;
        }

        else if(binding.etAssists.getText().toString().isEmpty()){
            binding.etKills.setError("Enter number of assists!");
            return -1;
        }

        else if(binding.etEconrating.getText().toString().isEmpty()){
            binding.etKills.setError("Enter Econ Rating!");
            return -1;
        }

        else if(binding.etAveragecombatscore.getText().toString().isEmpty()){
            binding.etKills.setError("Enter Average Combat Score!");
            return -1;
        }

        else if(Long.parseLong(binding.etEconrating.getText().toString()) < 0){
            binding.etEconrating.setError("Econ rating can't be negative!");
            return -1;
        }

        else if(Long.parseLong(binding.etAveragecombatscore.getText().toString()) < 0){
            binding.etEconrating.setError("Average combat score can't be negative!");
            return -1;
        }

        return 0;
    }

    private void addRecord(){
        this.kills = Integer.parseInt(binding.etKills.getText().toString());
        this.deaths = Integer.parseInt(binding.etDeaths.getText().toString());
        this.assists = Integer.parseInt(binding.etAssists.getText().toString());
        this.econRating = Integer.parseInt(binding.etEconrating.getText().toString());
        this.averageCombatScore = Integer.parseInt(binding.etAveragecombatscore.getText().toString());

        Bundle bundle = getIntent().getExtras();
        String matchRecordNumber = String.valueOf(bundle.getInt("Match Number"));

        MatchRecord record = new MatchRecord(agent, matchType, matchResult, kills, deaths, assists
        ,econRating,averageCombatScore);

        FirebaseDatabase.getInstance().getReference("Match Records")
                .child(mAuth.getCurrentUser().getUid()).child(matchRecordNumber)
                .setValue(record).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent();
                    setResult(RESULT_OK);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "There was a problem adding your record", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
