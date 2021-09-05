package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity {
    private ActivityDictionaryBinding binding;
    private DatabaseReference mref;
    private ArrayList<String> keywords;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        keywords = new ArrayList<String>();

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //get reference to dictionary in database
        mref = FirebaseDatabase.getInstance().getReference("Dictionary");

        //get all keywords from database
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for(DataSnapshot info: snapshot.getChildren()){
                        keywords.add(info.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set adapter for edit text
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, keywords);
        binding.etKeyword.setAdapter(adapter);

        //event listener for search button
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = binding.etKeyword.getText().toString().toLowerCase().trim();
                if(validateKeyword(keyword) == 0){
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(keyword).exists()){
                                binding.tvWord.setText(keyword.toUpperCase());
                                binding.tvMeaning.setText(snapshot.child(keyword).getValue().toString());
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "No results found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    public int validateKeyword(String keyword){
        if(keyword.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter keyword/s", Toast.LENGTH_SHORT).show();
            return -1;
        }

        else{
            return 0;
        }
    }
}
