package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityPostSpecificBinding;

public class PostSpecificActivity extends AppCompatActivity {
    ActivityPostSpecificBinding binding;
    String posterAgent;
    String posterRiotId;
    String posterTagline;
    String posterRank;
    String postContent;

    String userAgent;
    String userRiotId;
    String userTagline;
    String userRank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostSpecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        initializeData();
        setupPost();

    }

    private void initializeData(){
        //get data from previous activity
        Bundle bundle = getIntent().getExtras();

        //post data
        posterAgent = bundle.getString("agent");
        posterRiotId = bundle.getString("riotid");
        posterTagline = bundle.getString("tagline");
        posterRank = bundle.getString("rank");
        postContent = bundle.getString("content");

        //user data
        userAgent = bundle.getString("user_agent");
        userRiotId = bundle.getString("user_riotid");
        userTagline = bundle.getString("user_tagline");
        userRank = bundle.getString("user_rank");
    }

    private void setupPost(){
        binding.ivAgent.setImageResource(setAgentIcon(posterAgent));
        binding.tvRiotid.setText(posterRiotId);
        binding.tvTagline.setText(posterTagline);
        binding.tvRank.setText(posterRank);
        binding.tvPostContent.setText(postContent);
    }

    private int setAgentIcon(String agent){
        //default
        int id = R.drawable.agent_brimstone_icon;

        switch (agent){
            case "Brimstone":
                id = R.drawable.agent_brimstone_icon;
                break;

            case "Phoenix":
                id = R.drawable.agent_phoenix_icon;
                break;

            case "Sage":
                id = R.drawable.agent_sage_icon;
                break;

            case "Sova":
                id = R.drawable.agent_sova_icon;
                break;

            case "Viper":
                id = R.drawable.agent_viper_icon;
                break;

            case "Cypher":
                id = R.drawable.agent_cypher_icon;
                break;

            case "Reyna":
                id = R.drawable.agent_reyna_icon;
                break;

            case "Killjoy":
                id = R.drawable.agent_killjoy_icon;
                break;

            case "Breach":
                id = R.drawable.agent_breach_icon;
                break;

            case "Omen":
                id = R.drawable.agent_omen_icon;
                break;

            case "Jett":
                id = R.drawable.agent_jett_icon;
                break;

            case "Raze":
                id = R.drawable.agent_raze_icon;
                break;

            case "Skye":
                id = R.drawable.agent_skye_icon;
                break;

            case "Astra":
                id = R.drawable.agent_astra_icon;
                break;

            case "Yoru":
                id = R.drawable.agent_yoru_icon;
                break;

            case "Kay/o":
                id = R.drawable.agent_kayo_icon;
                break;
        }

        return id;
    }
}
