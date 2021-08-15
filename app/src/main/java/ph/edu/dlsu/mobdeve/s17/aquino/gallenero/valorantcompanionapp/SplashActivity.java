package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity{
    //3 seconds delay before transition
    private static final int TIME_DELAY = 3000;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //transition to another view
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent launchLogin = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(launchLogin);
                finish();
            }
        }, TIME_DELAY);



    }
}
