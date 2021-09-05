package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityAddmatchBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityTipsTricksBinding;

public class AddMatchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityAddmatchBinding binding;
    private String agent;
    private String matchType;
    private String matchResult;
    private int kills;
    private int deaths;
    private int assists;
    private int econRating;
    private int averageCombatScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddmatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set bg
        getWindow().setBackgroundDrawableResource(R.drawable.addmatch_bg);

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        initializeMatchInfo();
        initializeSpinners();
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
