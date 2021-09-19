package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.adapters.MatchHistoryAdapter;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityMatchHistoryBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.interfaces.ItemClickListener;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.MatchRecord;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.User;
/**
 * This class is responsible for the TipsTricks Activity of the application
 */
public class MatchHistoryActivity extends AppCompatActivity {
    private ActivityMatchHistoryBinding binding;
    private ArrayList<MatchRecord> matchRecords;
    private MatchHistoryAdapter matchHistoryAdapter;
    private ItemClickListener listener;
    private User user;
    private boolean noMatchFlag = true;

    //launcher for add match activity
    private ActivityResultLauncher<Intent> launchAddMatch =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                if(noMatchFlag == true){
                                        noMatchFlag = false;
                                        showRecyclerView();
                                }

                                Toast.makeText(getApplicationContext(),
                                        "Successfully Added Record",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(getApplicationContext(),
                                        "Adding unsuccessful!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //replace the text on action bar
        getSupportActionBar().setTitle("Match History");

        //instantiate match records list
        matchRecords = new ArrayList<>();

        //get user info from db
        getUser();

        //set listener for the matches
        setOnClickListener();

        //get matches from db
        getMatches();

        //setup recycler view
        matchHistoryAdapter = new MatchHistoryAdapter(matchRecords, listener);
        binding.rvView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvView.setAdapter(matchHistoryAdapter);

        //listener for add match button
        binding.btnAddmatch.setOnClickListener(v -> {
            Intent gotoAddMatch = new Intent(MatchHistoryActivity.this, AddMatchActivity.class);
            gotoAddMatch.putExtra("Match Number", matchRecords.size());
            launchAddMatch.launch(gotoAddMatch);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMatches();
    }

    /**
     * This method sets up the listener for the matches
     */
    private void setOnClickListener(){
        listener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MatchHistoryActivity.this, MatchSpecificActivity.class);
                intent.putExtra("riotid", user.getRiotId());
                intent.putExtra("tagline", user.getTagline());
                intent.putExtra("agent", matchRecords.get(position).getAgent());
                intent.putExtra("matchResult", matchRecords.get(position).getMatchResult());
                intent.putExtra("matchType", matchRecords.get(position).getMatchType());
                intent.putExtra("kills", matchRecords.get(position).getKills());
                intent.putExtra("deaths", matchRecords.get(position).getDeaths());
                intent.putExtra("assists",matchRecords.get(position).getAssists());
                intent.putExtra("econRating", matchRecords.get(position).getEconRating());
                intent.putExtra("averageCombatScore", matchRecords.get(position).getAverageCombatScore());
                startActivity(intent);
            }
        };
    }

    /**
     * This method gets the current user's info from the database
     */
    private void getUser(){
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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * This method sets the User instance to the profile from db
     */
    private void setUser(User user){
        this.user = user;
    }

    /**
     * This method is responsible getting the matches from the database
     */
    private void getMatches() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference recordsReference = FirebaseDatabase.getInstance()
                .getReference("Match Records");

        recordsReference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    noMatchFlag = false;
                    showRecyclerView();
                    matchRecords.clear();

                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        MatchRecord matchRecord = dataSnapshot.getValue(MatchRecord.class);
                        matchRecords.add(0,matchRecord);
                    }
                    matchHistoryAdapter.notifyDataSetChanged();
                }

                else{
                    hideRecyclerView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * This method hides the recycler view and shows the no match label
     */
    private void hideRecyclerView(){
        binding.rvView.setVisibility(View.GONE);
        binding.tvNoMatchLabel.setVisibility(View.VISIBLE);
    }

    /**
     * This method shows the recycler view and hides the no match label
     */
    private void showRecyclerView(){
        binding.rvView.setVisibility(View.VISIBLE);
        binding.tvNoMatchLabel.setVisibility(View.GONE);
    }
}
