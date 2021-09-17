package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });

        binding.cvDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionary = new Intent(HomeActivity.this, DictionaryActivity.class);
                startActivity(dictionary);
            }
        });

        binding.cvMatchStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_match = new Intent(HomeActivity.this, MatchStatisticsActivity.class);
                startActivity(add_match);
            }
        });

        binding.cvMatchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(HomeActivity.this, MatchHistoryActivity.class);
                startActivity(history);
            }
        });

        binding.cvAgents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agents = new Intent(HomeActivity.this, ChooseAgentActivity.class);
                startActivity(agents);
            }
        });

        binding.cvForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forum = new Intent(HomeActivity.this, ForumActivity.class);
                startActivity(forum);
            }
        });

    }





}
