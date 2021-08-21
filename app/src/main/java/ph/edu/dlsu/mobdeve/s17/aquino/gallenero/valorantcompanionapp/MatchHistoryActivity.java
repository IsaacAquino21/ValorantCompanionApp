package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityMatchBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils.User;

public class MatchHistoryActivity extends AppCompatActivity {
    ActivityMatchBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
