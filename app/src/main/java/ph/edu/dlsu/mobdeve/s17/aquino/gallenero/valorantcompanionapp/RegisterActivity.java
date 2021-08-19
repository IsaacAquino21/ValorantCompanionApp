package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityRegisterBinding;
import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.util.User;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityRegisterBinding binding;
    private String riotId;
    private String tagline;
    private String rank;
    private String region;
    private String email;
    private String password;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        initializeSpinners();
        initializeAccountDetails();

        binding.btnRegisterbutton.setOnClickListener(v -> {
            int result = validAccountDetails();

            if(result == 0)
                registerUser();
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spn_rank:
                this.rank = parent.getItemAtPosition(position).toString();
                break;

           case R.id.spn_region:
                this.region = parent.getItemAtPosition(position).toString();
                break;

          default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initializeAccountDetails(){
        this.riotId = "";
        this.tagline = "";
        this.rank = "Rank";
        this.region = "Region";
        this.password = "";
    }

    private void initializeSpinners(){
        //Initialize spinners for rank and region
        Spinner spinner1 = binding.spnRank;
        Spinner spinner2 = binding.spnRegion;

        //create adapter for spinners
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
                .createFromResource(this,
                        R.array.reg_rank, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(this,
                        R.array.reg_regions, android.R.layout.simple_spinner_item);

        //setDropDownViewResource of adapters
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set adapters of spinners
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);

        //set onItemSelectedListener of spinners
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    private int validAccountDetails(){
        //empty riot id
        if(binding.etRiotid.getText().toString().trim().isEmpty()){
            binding.etRiotid.setError("RiotId is Empty!");
            return -1;
        }

        //empty tagline
        else if(binding.etTagline.getText().toString().trim().isEmpty()){
            binding.etTagline.setError("Tagline is Empty!");
            return -1;
        }

        else if(binding.etEmail.getText().toString().trim().isEmpty()){
            binding.etEmail.setError("Email is Empty!");
            return -1;
        }

        else if(binding.etPassword.getText().toString().trim().isEmpty()){
            binding.etPassword.setError("Password is Empty!");
            return -1;
        }

        else if(binding.etConfirmpassword.getText().toString().trim().isEmpty()){
            binding.etConfirmpassword.setError("Confirm Password is Empty!");
            return -1;
        }

        String password = binding.etPassword.getText().toString();
        String confirmPassword = binding.etConfirmpassword.getText().toString();
        //check if password and confirm password match
        if (!password.equals(confirmPassword)){
            binding.etConfirmpassword.setError("Passwords do not match!");
            return -1;
        }

        //check if user has selected a rank and region
        if(this.rank.equals("Rank")){
            Toast.makeText(getApplicationContext(),
                    "Please select a rank!", Toast.LENGTH_SHORT);
            return -1;
        }

        else if(this.region.equals("Region")){
            Toast.makeText(getApplicationContext(),
                    "Please select a region!", Toast.LENGTH_SHORT);
            return -1;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString()).matches()){
            binding.etEmail.setError("Please enter valid email!");
            return -1;
        }

        //if all are valid
        return 0;
    }

    private void registerUser(){
        this.riotId = binding.etRiotid.getText().toString();
        this.tagline = binding.etTagline.getText().toString();
        this.email = binding.etEmail.getText().toString();
        this.password = binding.etPassword.getText().toString();

        this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("register", "register successful");
                    User user = new User(riotId, tagline, rank, region, email, password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            else{
                                Toast.makeText(getApplicationContext(),
                                        "Failed to Register User!", Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "Failed to Register User!", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
