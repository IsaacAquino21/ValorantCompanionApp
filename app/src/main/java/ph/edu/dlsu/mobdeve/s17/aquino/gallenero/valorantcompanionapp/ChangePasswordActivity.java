package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityChangePasswordBinding;

/**
 * This class is responsible for the change password activity of the page
 */
public class ChangePasswordActivity extends AppCompatActivity {
    private ActivityChangePasswordBinding binding;
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String newPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //instantiate firebase auth
        mAuth = FirebaseAuth.getInstance();

        //listener for the update button
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm() != -1){
                    updatePassword();
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
    public int validateForm(){
        String email = binding.etEmail.getText().toString();
        String oldPassword = binding.etPassword.getText().toString();
        String newPassword = binding.etNewPassword.getText().toString();
        String confirmPassword = binding.etNewPasswordConfirm.getText().toString();

        //empty email
        if(email.trim().isEmpty()){
            binding.etEmail.setError("Please enter your email!");
            return -1;
        }

        //empty old password
        else if(oldPassword.trim().isEmpty()){
            binding.etPassword.setError("Please enter your password!");
            return -1;
        }

        //empty new password
        else if(newPassword.trim().isEmpty()){
            binding.etNewPassword.setError("Please enter new password!");
            return -1;
        }

        //empty confirm password
        else if(confirmPassword.trim().isEmpty()){
            binding.etNewPasswordConfirm.setError("Please enter new password!");
            return -1;
        }

        //email pattern does not match
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.setError("Please enter valid email!");
            return -1;
        }

        //new password length is less than 6
        else if(newPassword.trim().length() < 6){
            binding.etNewPassword.setError("Password must have at least 6 characters!");
            return -1;
        }

        //passwords do not match
        else if(!newPassword.equals(confirmPassword)){
            binding.etNewPasswordConfirm.setError("Passwords do not match!");
            return -1;
        }

        //if all tests are passed
        else{
            this.email = email;
            this.password = oldPassword;
            this.newPassword = newPassword;
        }

        return 0;
    }

    /**
     * This method is responsible for re-authenticating the user and then change their password using
     * the details provided.
     */
    private void updatePassword() {
        FirebaseUser user = mAuth.getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                            else{
                                Toast.makeText(getApplicationContext(),
                                        "There was with updating your password! Try again!"
                                        ,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "There was with your login credentials! Try again!"
                    ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
