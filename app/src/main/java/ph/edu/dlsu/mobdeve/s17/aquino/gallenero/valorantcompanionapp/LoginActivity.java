package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityLoginBinding;

/**
 * This class is responsible for the login page of the application
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    //launcher for RegisterActivity
    private ActivityResultLauncher<Intent> launchRegister =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK){
                                Toast.makeText(getApplicationContext(), "Successfully Registered!",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Registration Unsuccessful!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //set bg
        getWindow().setBackgroundDrawableResource(R.drawable.login_bg);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //listener for the register button
        binding.btnRegisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                launchRegister.launch(gotoRegister);
            }
        });

        //listener for the login button
        binding.btnLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = validateAccountDetails();
                if(result == 0){
                    loginUser();
                }
            }
        });
    }

    /**
     * This method is responsible for validating the account details provided.
     * If any error is found, this methods sets an error to that field if applicable, else puts a
     * toast message instead.
     * @return -1 if a problem is encountered in any of the details, else returns 0.
     */
    private int validateAccountDetails(){
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();

        if(email.trim().isEmpty()){
            binding.etEmail.setError("Email is empty!");
            binding.etEmail.requestFocus();
            return -1;
        }

        else if(password.trim().isEmpty()){
            binding.etPassword.setError("Password is empty!");
            binding.etPassword.requestFocus();
            return -1;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
            binding.etEmail.setError("Invalid Email!");
            binding.etEmail.requestFocus();
            return -1;
        }

        else if(password.length() < 6){
            binding.etPassword.setError("Invalid Password length!");
            binding.etPassword.requestFocus();
            return -1;
        }

        return 0;
    }

    /**
     * This method is responsible for logging in the user using FirebaseAuth.
     */
    private void loginUser(){
        binding.pbProgressBar.setVisibility(View.VISIBLE);
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                binding.pbProgressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "Failed to login! Please check login credentials!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
