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

/**
 * This class is responsible for the Dictionary page of the application
 */
public class DictionaryActivity extends AppCompatActivity {
    private ActivityDictionaryBinding binding;
    private DatabaseReference mref;
    private ArrayList<String> keywords;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //instantiate the arraylist for the class
        keywords = new ArrayList<>();

        //reference for the database
        mref = FirebaseDatabase.getInstance().getReference("Dictionary");

        //get keywords from db
        getKeywords();

        //set adapter for edit text
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, keywords);
        binding.etKeyword.setAdapter(adapter);

        //event listener for search button
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = binding.etKeyword.getText().toString().toLowerCase().trim();
                if(validateKeyword(keyword) == 0){
                    getMeaning(keyword);
                }
            }
        });
    }

    /**
     * This method is responsible for validating the keyword provided. If the edit text is empty,
     * set an error for the edit text.
     * @return -1 if a problem is edit text is empty, else returns 0.
     */
    private int validateKeyword(String keyword){
        if(keyword.isEmpty()){
            binding.etKeyword.setError("Please enter keyword/s");
            return -1;
        }

        else{
            return 0;
        }
    }

    /**
     * This method is getting the keywords from the database and adds them to the keywords arraylist.
     * Used to allow autocomplete function of edit text to work.
     */
    private void getKeywords(){
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
    }

    /**
     * This method is responsible for getting the meaning of the chosen keyword by the user
     * by obtaining it from the database.
     */
    private void getMeaning(String keyword){
        mref = FirebaseDatabase.getInstance().getReference("Dictionary");
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
