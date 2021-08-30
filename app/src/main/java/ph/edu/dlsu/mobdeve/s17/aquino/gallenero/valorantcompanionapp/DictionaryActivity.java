package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity {
    ActivityDictionaryBinding binding;
    DatabaseReference mref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        mref = FirebaseDatabase.getInstance().getReference("Dictionary");

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
