package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityHomeBinding;

/**
 * This class is responsible for the Homepage of the application
 */
public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //listener for the profile page button
        binding.cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });

        //listener for the dictionary button
        binding.cvDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dictionary = new Intent(HomeActivity.this, DictionaryActivity.class);
                startActivity(dictionary);
            }
        });

        //listener for the match history button
        binding.cvMatchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(HomeActivity.this, MatchHistoryActivity.class);
                startActivity(history);
            }
        });

        //listener for the match statistics button
        binding.cvMatchStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_match = new Intent(HomeActivity.this, MatchStatisticsActivity.class);
                startActivity(add_match);
            }
        });

        //listener for the agents button
        binding.cvAgents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agents = new Intent(HomeActivity.this, ChooseAgentActivity.class);
                startActivity(agents);
            }
        });

        //listener for the forum button
        binding.cvForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forum = new Intent(HomeActivity.this, ForumActivity.class);
                startActivity(forum);
            }
        });
    }
}
