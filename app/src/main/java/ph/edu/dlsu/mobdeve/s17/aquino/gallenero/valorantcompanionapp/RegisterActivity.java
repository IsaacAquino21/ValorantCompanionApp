package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityRegisterBinding binding;
    private String riotId;
    private String tagline;
    private String rank;
    private String region;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        Spinner spinner1 = binding.spnRank;
        Spinner spinner2 = binding.spnRegion;

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter
                .createFromResource(this,
                        R.array.reg_rank, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter
                .createFromResource(this,
                        R.array.reg_regions, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        initializeAccountDetails();

        binding.btnRegisterbutton.setOnClickListener(v -> {
            if(validAccountDetails() == -1){
                Toast.makeText(getApplicationContext(), "Empty RiotId",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -2){
                Toast.makeText(getApplicationContext(), "Empty Tagline",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -3){
                Toast.makeText(getApplicationContext(), "Empty Password",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -4){
                Toast.makeText(getApplicationContext(), "Empty Confirm Password",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -5){
                Toast.makeText(getApplicationContext(), "Passwords do not match",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -6){
                Toast.makeText(getApplicationContext(), "No Rank Selected",
                        Toast.LENGTH_SHORT).show();
            }

            else if(validAccountDetails() == -7){
                Toast.makeText(getApplicationContext(), "No Region Selected",
                        Toast.LENGTH_SHORT).show();
            }

            //insert db access here
            else if(validAccountDetails() == 0){
                this.riotId = binding.etRiotid.getText().toString();
                this.tagline = binding.etTagline.getText().toString();
                this.password = binding.etPassword.getText().toString();
                Log.i("RiotId", this.riotId);
                Log.i("Tagline", this.tagline);
                Log.i("Rank", this.rank);
                Log.i("Region", this.region);
                Log.i("Password", this.password);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }


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

    private int validAccountDetails(){
        //Check if all edit text fields are filled.
        if(binding.etRiotid.getText().toString().trim().length() == 0)
            return -1;

        else if(binding.etTagline.getText().toString().trim().length() == 0)
            return -2;

        else if(binding.etPassword.getText().toString().trim().length() == 0)
            return -3;

        else if(binding.etConfirmpassword.getText().toString().trim().length() == 0)
            return -4;

        String password = binding.etPassword.getText().toString();
        String confirmPassword = binding.etConfirmpassword.getText().toString();
        //check if password and confirm password match
        if (!password.equals(confirmPassword))
            return -5;

        //check if user has selected a rank and region
        if(this.rank.equals("Rank"))
            return -6;

        else if(this.region.equals("Region"))
            return -7;

        //if all are valid
        return 0;
    }
}
