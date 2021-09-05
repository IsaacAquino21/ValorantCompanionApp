package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils;

import java.util.ArrayList;

public class DataHelper {

    public DataHelper(){
    }

    public ArrayList<MatchRecord> getMatches(){
        ArrayList<MatchRecord> matchRecords = new ArrayList<>();

        matchRecords.add(new MatchRecord("Reyna", "Competitive", "WIN", 15, 12, 16, 65, 220));
        matchRecords.add(new MatchRecord("Brimstone", "Unrated", "LOSE",8, 20, 5, 65, 220));
        matchRecords.add(new MatchRecord("Viper", "Escalation", "DRAW", 15, 12, 16, 65, 220));
        matchRecords.add(new MatchRecord("Cypher","Snowball Fight", "WIN", 15, 12, 16, 65, 220));
        matchRecords.add(new MatchRecord("Yoru", "Replication", "LOSE",15, 12, 16, 65, 220));
        matchRecords.add(new MatchRecord("Breach", "Deathmatch", "DRAW",15, 12, 16, 65, 220));
        matchRecords.add(new MatchRecord("Skye", "Spike Rush", "WIN",15, 12, 16, 65, 220));

        return matchRecords;
    }
}
