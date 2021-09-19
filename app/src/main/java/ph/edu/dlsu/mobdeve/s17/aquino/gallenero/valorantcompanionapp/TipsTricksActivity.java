package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.databinding.ActivityTipsTricksBinding;

/**
 * This class is responsible for the TipsTricks Activity of the application
 */
public class TipsTricksActivity extends AppCompatActivity {
    ActivityTipsTricksBinding binding;
    String agent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipsTricksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //hide action bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        
        Bundle bundle = getIntent().getExtras();
        agent = bundle.getString("agent");

        setAgentPage(agent);
    }

    /**
     * This method calls the corresponding setup method for the selected agent from the ChooseAgent activity.
     */
    private void setAgentPage(String agent){
        switch (agent){
            case "Brimstone":
                setupBrimstone();
                break;

            case "Phoenix":
                setupPhoenix();
                break;

            case "Sage":
                setupSage();
                break;

            case "Sova":
                setupSova();
                break;

            case "Viper":
                setupViper();
                break;

            case "Cypher":
                break;

            case "Reyna":
                setupReyna();
                break;

            case "Killjoy":
                setupKilljoy();
                break;

            case "Breach":
                setupBreach();
                break;

            case "Omen":
                setupOmen();
                break;

            case "Jett":
                setupJett();
                break;

            case "Raze":
                setupRaze();
                break;

            case "Skye":
                setupSkye();
                break;

            case "Astra":
                setupAstra();
                break;

            case "Yoru":
                setupYoru();
                break;

            case "Kay/o":
                setupKayo();
                break;
        }
    }

    /*setup methods for the different agents*/

    private void setupAstra(){
        binding.llAbilityFifth.setVisibility(View.VISIBLE);

        binding.gifMain.setImageResource(R.drawable.agent_tips_astra_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.astra_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.astra_well);
        binding.tvAbilityOne.setText(R.string.astra_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.astra_stun);
        binding.tvAbilityThree.setText(R.string.astra_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.astra_smoke);
        binding.tvAbilityThree.setText(R.string.astra_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.astra_signature);
        binding.tvAbilityFour.setText(R.string.astra_ability4);

        binding.ivAbilityFive.setImageResource(R.drawable.astra_ult);
        binding.tvAbilityFive.setText(R.string.astra_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_astra_firsttip);
        binding.tvTipOne.setText(R.string.astra_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_astra_secondtip);
        binding.tvTipTwo.setText(R.string.astra_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_astra_thirdtip);
        binding.tvTipThree.setText(R.string.astra_tip3);
    }

    private void setupBreach(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_breach_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.breach_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.breach_shock);
        binding.tvAbilityOne.setText(R.string.breach_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.breach_flash);
        binding.tvAbilityTwo.setText(R.string.breach_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.breach_stun);
        binding.tvAbilityThree.setText(R.string.breach_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.breach_ult);
        binding.tvAbilityFour.setText(R.string.breach_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_breach_firsttip);
        binding.tvTipOne.setText(R.string.breach_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_breach_secondtip);
        binding.tvTipTwo.setText(R.string.breach_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_breach_thirdtip);
        binding.tvTipThree.setText(R.string.breach_tip3);
    }

    private void setupBrimstone(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_brim_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.brim_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.brim_stim);
        binding.tvAbilityOne.setText(R.string.brim_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.brim_molly);
        binding.tvAbilityTwo.setText(R.string.brim_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.brim_smoke);
        binding.tvAbilityThree.setText(R.string.brim_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.brim_ult);
        binding.tvAbilityFour.setText(R.string.brim_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_brimstone_firsttip);
        binding.tvTipOne.setText(R.string.brim_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_brimstone_secondtip);
        binding.tvTipTwo.setText(R.string.brim_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_brimstone_thirdtip);
        binding.tvTipThree.setText(R.string.brim_tip3);
    }

    private void setupJett(){
        binding.llAbilityFifth.setVisibility(View.VISIBLE);
        binding.gifMain.setImageResource(R.drawable.agent_tips_main_jett);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.jett_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.jett_drift);
        binding.tvAbilityOne.setText(R.string.jett_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.jett_smoke);
        binding.tvAbilityTwo.setText(R.string.jett_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.jett_updraft);
        binding.tvAbilityThree.setText(R.string.jett_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.jett_dash);
        binding.tvAbilityFour.setText(R.string.jett_ability4);

        binding.ivAbilityFive.setImageResource(R.drawable.jett_ult);
        binding.tvAbilityFive.setText(R.string.jett_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_jett_firsttip);
        binding.tvTipOne.setText(R.string.jett_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_jett_secondtip);
        binding.tvTipTwo.setText(R.string.jett_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_jett_thirdtip);
        binding.tvTipThree.setText(R.string.jett_tip3);
    }

    private void setupKayo(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_kayo_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.kayo_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.kayo_molly);
        binding.tvAbilityOne.setText(R.string.kayo_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.kayo_flash);
        binding.tvAbilityTwo.setText(R.string.kayo_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.kayo_knives);
        binding.tvAbilityThree.setText(R.string.kayo_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.kayo_ult);
        binding.tvAbilityFour.setText(R.string.kayo_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_kayo_firsttip);
        binding.tvTipOne.setText(R.string.kayo_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_kayo_secondtip);
        binding.tvTipTwo.setText(R.string.kayo_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_kayo_thirdtip);
        binding.tvTipThree.setText(R.string.kayo_tip3);
    }

    private void setupKilljoy(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_kj_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.killjoy_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.kj_molly);
        binding.tvAbilityOne.setText(R.string.killjoy_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.kj_bot);
        binding.tvAbilityTwo.setText(R.string.killjoy_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.kj_turret);
        binding.tvAbilityTwo.setText(R.string.killjoy_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.kj_ult);
        binding.tvAbilityFour.setText(R.string.killjoy_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_kj_firsttip);
        binding.tvTipOne.setText(R.string.killjoy_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_kj_secondtip);
        binding.tvTipTwo.setText(R.string.killjoy_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_killjoy_thirdtip);
        binding.tvTipThree.setText(R.string.killjoy_tip3);
    }

    private void setupOmen(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_omen_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.omen_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.omen_tp);
        binding.tvAbilityOne.setText(R.string.omen_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.omen_blind);
        binding.tvAbilityTwo.setText(R.string.omen_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.omen_smoke);
        binding.tvAbilityThree.setText(R.string.omen_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.omen_ult);
        binding.tvAbilityFour.setText(R.string.omen_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_omen_firsttip);
        binding.tvTipOne.setText(R.string.omen_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_omen_secondtip);
        binding.tvTipTwo.setText(R.string.omen_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_omen_thirdtip);
        binding.tvTipThree.setText(R.string.omen_tip3);
    }

    private void setupPhoenix(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_phoenix_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.phoenix_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.phoenix_wall);
        binding.tvAbilityOne.setText(R.string.phoenix_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.phoenix_flash);
        binding.tvAbilityTwo.setText(R.string.phoenix_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.phoenix_molly);
        binding.tvAbilityThree.setText(R.string.phoenix_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.phoenix_ult);
        binding.tvAbilityFour.setText(R.string.phoenix_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_phoenix_firsttip);
        binding.tvTipOne.setText(R.string.phoenix_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_phoenix_secondtip);
        binding.tvTipTwo.setText(R.string.phoenix_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_phoenix_thirdtip);
        binding.tvTipThree.setText(R.string.phoenix_tip3);
    }

    private void setupRaze(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_raze_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.raze_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.raze_bot);
        binding.tvAbilityOne.setText(R.string.raze_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.raze_satchel);
        binding.tvAbilityTwo.setText(R.string.raze_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.raze_nade);
        binding.tvAbilityThree.setText(R.string.raze_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.raze_ult);
        binding.tvAbilityFour.setText(R.string.raze_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_raze_firsttip);
        binding.tvTipOne.setText(R.string.raze_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_raze_secondtip);
        binding.tvTipTwo.setText(R.string.raze_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_raze_thirdtip);
        binding.tvTipThree.setText(R.string.raze_tip3);

    }

    private void setupReyna(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_reyna_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.reyna_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.reyna_blind);
        binding.tvAbilityOne.setText(R.string.reyna_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.reyna_heal);
        binding.tvAbilityTwo.setText(R.string.reyna_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.reyna_dismiss);
        binding.tvAbilityThree.setText(R.string.reyna_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.reyna_ult);
        binding.tvAbilityFour.setText(R.string.reyna_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_reyna_firsttip);
        binding.tvTipOne.setText(R.string.reyna_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_reyna_secondtip);
        binding.tvTipTwo.setText(R.string.reyna_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_reyna_thirdtip);
        binding.tvTipThree.setText(R.string.reyna_tip3);
    }

    private void setupSage(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_sage_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.sage_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.sage_wall);
        binding.tvAbilityOne.setText(R.string.sage_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.sage_slow);
        binding.tvAbilityTwo.setText(R.string.sage_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.sage_heal);
        binding.tvAbilityThree.setText(R.string.sage_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.sage_ult);
        binding.tvAbilityFour.setText(R.string.sage_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_sage_firsttip);
        binding.tvTipOne.setText(R.string.sage_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_sage_secondtip);
        binding.tvTipTwo.setText(R.string.sage_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_sage_thirdtip);
        binding.tvTipThree.setText(R.string.sage_tip3);
    }

    private void setupSkye(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_skye_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.skye_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.skye_heal);
        binding.tvAbilityOne.setText(R.string.skye_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.skye_dog);
        binding.tvAbilityTwo.setText(R.string.skye_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.skye_flash);
        binding.tvAbilityThree.setText(R.string.skye_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.skye_ult);
        binding.tvAbilityFour.setText(R.string.skye_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_skye_firsttip);
        binding.tvTipOne.setText(R.string.skye_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_skye_secondtip);
        binding.tvTipTwo.setText(R.string.skye_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_skye_thirdtip);
        binding.tvTipThree.setText(R.string.skye_tip3);
    }

    private void setupSova(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_sova_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.sova_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.sova_drone);
        binding.tvAbilityOne.setText(R.string.sova_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.sova_shock);
        binding.tvAbilityTwo.setText(R.string.sova_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.sova_recon);
        binding.tvAbilityThree.setText(R.string.sova_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.sova_ult);
        binding.tvAbilityFour.setText(R.string.sova_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_sova_firsttip);
        binding.tvTipOne.setText(R.string.sova_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_sova_secondtip);
        binding.tvTipTwo.setText(R.string.sova_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_sova_thirdtip);
        binding.tvTipThree.setText(R.string.sova_tip3);
    }

    private void setupViper(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_viper_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.viper_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.viper_molly);
        binding.tvAbilityOne.setText(R.string.viper_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.viper_orb);
        binding.tvAbilityTwo.setText(R.string.viper_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.viper_wall);
        binding.tvAbilityThree.setText(R.string.viper_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.viper_ult);
        binding.tvAbilityFour.setText(R.string.viper_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_viper_firsttip);
        binding.tvTipOne.setText(R.string.viper_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_viper_secondtip);
        binding.tvTipTwo.setText(R.string.viper_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_viper_thirdtip);
        binding.tvTipThree.setText(R.string.viper_tip3);
    }

    private void setupYoru(){
        binding.gifMain.setImageResource(R.drawable.agent_tips_yoru_main);
        binding.tvAgentName.setText(agent);
        binding.tvAgentDesc.setText(R.string.yoru_desc);

        binding.ivAbilityOne.setImageResource(R.drawable.yoru_foots);
        binding.tvAbilityOne.setText(R.string.yoru_ability1);

        binding.ivAbilityTwo.setImageResource(R.drawable.yoru_flash);
        binding.tvAbilityTwo.setText(R.string.yoru_ability2);

        binding.ivAbilityThree.setImageResource(R.drawable.yoru_tp);
        binding.tvAbilityThree.setText(R.string.yoru_ability3);

        binding.ivAbilityFour.setImageResource(R.drawable.yoru_ult);
        binding.tvAbilityFour.setText(R.string.yoru_ult);

        binding.gifTipOne.setImageResource(R.drawable.agent_yoru_firsttip);
        binding.tvTipOne.setText(R.string.yoru_tip1);

        binding.gifTipTwo.setImageResource(R.drawable.agent_yoru_secondtip);
        binding.tvTipTwo.setText(R.string.yoru_tip2);

        binding.gifTipThree.setImageResource(R.drawable.agent_yoru_thirdtip);
        binding.tvTipThree.setText(R.string.yoru_tip3);
    }
}
