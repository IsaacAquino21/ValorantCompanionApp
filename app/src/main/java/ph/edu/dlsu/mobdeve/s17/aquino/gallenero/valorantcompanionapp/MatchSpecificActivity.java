package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityMatchSpecificBinding;

public class MatchSpecificActivity extends AppCompatActivity {
    private ActivityMatchSpecificBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatchSpecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        //get data from previous activity
        Bundle bundle = getIntent().getExtras();
        String matchResult = bundle.getString("matchResult");
        String matchType = bundle.getString("matchType");
        String kills = bundle.getInt("kills") + " Kills";
        String deaths = bundle.getInt("deaths") + " Deaths";
        String assists = bundle.getInt("assists") + " Assists";
        String acs = String.valueOf(bundle.getInt("averageCombatScore"));
        String ecr = String.valueOf(bundle.getInt("econRating"));

        //update views
        setAgentIcon(bundle.getString("agent"));
        setMatchTypeIcon(matchType);
        binding.tvRiotid.setText(bundle.getString("riotid"));
        binding.tvTagline.setText(bundle.getString("tagline"));
        binding.tvMatchResult.setText(matchResult);
        binding.tvMatchType.setText(matchType.toUpperCase());
        binding.tvKills.setText(kills);
        binding.tvDeaths.setText(deaths);
        binding.tvAssists.setText(assists);
        binding.tvAcs.setText(acs);
        binding.tvEcr.setText(ecr);
    }

    //method used to set the agent icon in the view
    private void setAgentIcon(String agent){
        switch (agent){
            case "Brimstone":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_brimstone);
                break;

            case "Phoenix":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_phoenix);
                break;

            case "Sage":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_sage);
                break;

            case "Sova":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_sova);
                break;

            case "Viper":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_viper);
                break;

            case "Cypher":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_cypher);
                break;

            case "Reyna":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_reyna);
                break;

            case "Killjoy":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_killjoy);
                break;

            case "Breach":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_breach);
                break;

            case "Omen":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_omen);
                break;

            case "Jett":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_jett);
                break;

            case "Raze":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_raze);
                break;

            case "Skye":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_skye);
                break;

            case "Astra":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_astra);
                break;

            case "Yoru":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_yoru);
                break;

            case "Kay/o":
                binding.matchAgentPhoto.setImageResource(R.drawable.agent_kayo);
                break;
        }
    }

    private void setMatchTypeIcon(String matchType){
        switch (matchType){
            case "Unrated":

            case "Competitive":
                binding.ivMatchIcon.setImageResource(R.drawable.match_competitive);
                break;

            case "Spike Rush":
                binding.ivMatchIcon.setImageResource(R.drawable.match_spikerush);
                break;

            case "Deathmatch":
                binding.ivMatchIcon.setImageResource(R.drawable.match_deathmatch);
                break;

            case "Escalation":
                binding.ivMatchIcon.setImageResource(R.drawable.match_escalation);
                break;

            case "Replication":
                binding.ivMatchIcon.setImageResource(R.drawable.match_replication);
                break;

            case "Snowball Fight":
                binding.ivMatchIcon.setImageResource(R.drawable.match_snowballfight);
                break;
        }
    }
}