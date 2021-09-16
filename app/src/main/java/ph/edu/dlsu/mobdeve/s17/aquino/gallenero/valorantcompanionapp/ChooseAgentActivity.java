package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityChooseAgentBinding;

public class ChooseAgentActivity extends AppCompatActivity {
    private ActivityChooseAgentBinding binding;

    //default agent
    private String agent = "Cypher";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseAgentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //agents listeners
        binding.cvAstra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Astra";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvBreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Breach";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvBrimstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Brimstone";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvCypher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Cypher";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvJett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Jett";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvKayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Kay/o";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvKilljoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Killjoy";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvOmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Omen";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvPhoenix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Phoenix";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvRaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Raze";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvReyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Reyna";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvSage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Sage";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvSkye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Skye";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvSova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Sova";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvViper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Viper";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        binding.cvYoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agent = "Yoru";
                binding.ivAgent.setImageResource(setAgentIcon(agent));
                binding.tvAgentName.setText(agent);
            }
        });

        //lock in button listener
        binding.btnLockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAgentActivity.this, TipsTricksActivity.class);
                intent.putExtra("agent", agent);
                startActivity(intent);
            }
        });
    }


    private int setAgentIcon(String agent){
        //default
        int id = R.drawable.agent_brimstone;

        switch (agent){
            case "Brimstone":
                id = R.drawable.agent_brimstone;
                break;

            case "Phoenix":
                id = R.drawable.agent_phoenix;
                break;

            case "Sage":
                id = R.drawable.agent_sage;
                break;

            case "Sova":
                id = R.drawable.agent_sova;
                break;

            case "Viper":
                id = R.drawable.agent_viper;
                break;

            case "Cypher":
                id = R.drawable.agent_cypher;
                break;

            case "Reyna":
                id = R.drawable.agent_reyna;
                break;

            case "Killjoy":
                id = R.drawable.agent_killjoy;
                break;

            case "Breach":
                id = R.drawable.agent_breach;
                break;

            case "Omen":
                id = R.drawable.agent_omen;
                break;

            case "Jett":
                id = R.drawable.agent_jett;
                break;

            case "Raze":
                id = R.drawable.agent_raze;
                break;

            case "Skye":
                id = R.drawable.agent_skye;
                break;

            case "Astra":
                id = R.drawable.agent_astra;
                break;

            case "Yoru":
                id = R.drawable.agent_yoru;
                break;

            case "Kay/o":
                id = R.drawable.agent_kayo;
                break;
        }
        return id;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
