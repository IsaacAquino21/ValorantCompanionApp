package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityMatchStatisticsBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.MatchRecord;

public class MatchStatisticsActivity extends AppCompatActivity {
    ActivityMatchStatisticsBinding binding;
    private ArrayList<MatchRecord> matchRecords;
    private LineChart mpLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        matchRecords = new ArrayList<>();

        getMatches();
    }

    private void getMatches(){
        binding.pbProgressBar.setVisibility(View.VISIBLE);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference recordsReference = FirebaseDatabase.getInstance()
                .getReference("Match Records");
        recordsReference.child(mAuth.getCurrentUser().getUid()).limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    matchesFound();
                    matchRecords.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        MatchRecord matchRecord = dataSnapshot.getValue(MatchRecord.class);
                        matchRecords.add(matchRecord);
                    }
                    setupLineChart();
                    setStatistics();
                }

                else{
                    matchNotFound();
                }
                binding.pbProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setupLineChart(){
        mpLineChart = (LineChart) findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(getKills(),"Kills");
        LineDataSet lineDataSet2 = new LineDataSet(getDeaths(),"Deaths");
        LineDataSet lineDataSet3 = new LineDataSet(getAssists(),"Assists");

        lineDataSet1.setColor(Color.RED);
        lineDataSet2.setColor(Color.GREEN);
        lineDataSet3.setColor(Color.BLUE);

        lineDataSet1.setLineWidth(3f);
        lineDataSet2.setLineWidth(3f);
        lineDataSet3.setLineWidth(3f);

        lineDataSet1.setValueTextSize(16f);
        lineDataSet2.setValueTextSize(16f);
        lineDataSet3.setValueTextSize(16f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);

        LineData data= new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
    }

    private ArrayList<Entry> getKills(){
        ArrayList<Entry> kills = new ArrayList<>();
        for(int i = 0; i < matchRecords.size(); i++){
            kills.add(new Entry(i + 1, matchRecords.get(i).getKills()));
        }
        return kills;
    }

    private ArrayList<Entry> getDeaths(){
        ArrayList<Entry> deaths = new ArrayList<>();
        for(int i = 0; i < matchRecords.size(); i++){
            deaths.add(new Entry(i + 1, matchRecords.get(i).getDeaths()));
        }
        return deaths;
    }

    private ArrayList<Entry> getAssists(){
        ArrayList<Entry> ratings = new ArrayList<>();
        for(int i = 0; i < matchRecords.size(); i++){
            ratings.add(new Entry(i + 1, matchRecords.get(i).getAssists()));
        }
        return ratings;
    }

    private float getKillsAverage(){
        int kills = 0;
        float average;
        for(int i = 0; i < matchRecords.size(); i++){
            kills += matchRecords.get(i).getKills();
        }

        average = (float)kills/(float)matchRecords.size();

        return average;
    }

    private float getDeathsAverage(){
        int deaths = 0;
        float average = 0;
        for(int i = 0; i < matchRecords.size(); i++){
            deaths += matchRecords.get(i).getDeaths();
        }

        average = (float)deaths/(float)matchRecords.size();

        return average;
    }

    private float getAssistsAverage(){
        int assists = 0;
        float average = 0;
        for(int i = 0; i < matchRecords.size(); i++){
            assists += matchRecords.get(i).getAssists();
        }

        average = (float)assists/(float)matchRecords.size();

        return average;

    }

    private float getKDRatio(){
        int kills = 0;
        int deaths = 0;
        float ratio;

        for(int i = 0; i < matchRecords.size(); i++){
            kills += matchRecords.get(i).getKills();
            deaths += matchRecords.get(i).getDeaths();
        }

        ratio = (float)kills/(float)deaths;
        return ratio;
    }

    private void setStatistics(){
        String killsAverage = "Kills Average: " + getKillsAverage();
        String deathsAverage = "Deaths Average: " + getDeathsAverage();
        String assistsAverage = "Assists Average: " + getAssistsAverage();
        String ratio = "K/D Ratio: " + getKDRatio();
        binding.tvKillsAverage.setText(killsAverage);
        binding.tvDeathsAverage.setText(deathsAverage);
        binding.tvAssistsAverage.setText(assistsAverage);
        binding.tvKdRatio.setText(ratio);
    }

    private void matchesFound(){
        binding.tvNoMatchLabel.setVisibility(View.GONE);
        binding.lineChart.setVisibility(View.VISIBLE);
        binding.tvKdRatio.setVisibility(View.VISIBLE);
        binding.tvKillsAverage.setVisibility(View.VISIBLE);
        binding.tvAssistsAverage.setVisibility(View.VISIBLE);
        binding.tvDeathsAverage.setVisibility(View.VISIBLE);
    }

    private void matchNotFound(){
        binding.tvNoMatchLabel.setVisibility(View.VISIBLE);
        binding.lineChart.setVisibility(View.GONE);
        binding.tvKdRatio.setVisibility(View.GONE);
        binding.tvKillsAverage.setVisibility(View.GONE);
        binding.tvAssistsAverage.setVisibility(View.GONE);
        binding.tvDeathsAverage.setVisibility(View.GONE);
    }


}
