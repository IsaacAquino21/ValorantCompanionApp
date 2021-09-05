package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityMatchBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.DataHelper;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.MatchRecord;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.User;

public class MatchHistoryActivity extends AppCompatActivity {
    private ActivityMatchBinding binding;
    private ArrayList<MatchRecord> matchRecords;
    private MatchHistoryAdapter matchHistoryAdapter;
    private MatchHistoryAdapter.ItemClickListener listener;
    private User user;


    private ActivityResultLauncher<Intent> launchAddMatch =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == 1){
                                Intent data = result.getData();

                                String agent = data.getStringExtra("agent");
                                String type = data.getStringExtra("type");
                                String matchResult = data.getStringExtra("matchResult");
                                int kills = data.getIntExtra("kills", 0);
                                int deaths = data.getIntExtra("deaths", 0);
                                int assists = data.getIntExtra("assists", 0);
                                int econRating = data.getIntExtra("econRating", 0);
                                int averageCombatScore = data.getIntExtra("averageCombatScore", 0);

                                MatchRecord matchRecord = new MatchRecord(agent, type, matchResult, kills, deaths, assists, econRating, averageCombatScore);
                                matchHistoryAdapter.addMatch(matchRecord);
                                binding.rvView.smoothScrollToPosition(0);
                            }
                        }
                    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //replace the text on action bar
        getSupportActionBar().setTitle("Match History");

        DataHelper dataHelper = new DataHelper();

        matchRecords = dataHelper.getMatches();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        setOnClickListener();
        matchHistoryAdapter = new MatchHistoryAdapter(matchRecords, listener);
        binding.rvView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvView.setAdapter(matchHistoryAdapter);

        binding.btnAddmatch.setOnClickListener(v -> {
            Intent gotoAddMatch = new Intent(MatchHistoryActivity.this, AddMatchActivity.class);
            launchAddMatch.launch(gotoAddMatch);
        });


    }

    private void setOnClickListener(){
        listener = new MatchHistoryAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getApplicationContext(), MatchSpecificActivity.class);
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

    private void setUser(User user){
        this.user = user;
    }









//    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
//        reference.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            if(snapshot.hasChildren()){
//                for (DataSnapshot info: snapshot.getChildren()) {
//                    Log.i("snapshot key",info.getKey());
//                    Log.i("Uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    if(info.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
//                        User user = info.getValue(User.class);
//                        setUserInformation(user);
//                        setAgentIcon(user.getAgent());
//                        setRankIcon(user.getRank());
//                        break;
//                    }
//
//                }
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    });

}
