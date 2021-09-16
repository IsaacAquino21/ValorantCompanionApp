package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils;

import java.util.ArrayList;
import java.util.Random;

import ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models.MatchRecord;

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

    public String generateuID(){
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        //string builder
        StringBuilder sb = new StringBuilder();

        //random generator
        Random random = new Random();

        //length of uid
        int length = 28;


        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String uID = sb.toString();
        return uID;
    }
}
