package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.TimeZone;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity{
    //3 seconds delay before transition
    private static final int TIME_DELAY = 3000;
    private ActivitySplashBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //instantiate FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //transition to another view
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser() != null){
                    Intent launchHome = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(launchHome);
                    finish();
                }

                else{
                    Intent launchLogin = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(launchLogin);
                    finish();
                }
            }
        }, TIME_DELAY);



    }
}
