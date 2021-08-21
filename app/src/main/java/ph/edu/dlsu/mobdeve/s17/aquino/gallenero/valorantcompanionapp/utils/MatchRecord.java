package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.utils;

public class MatchRecord {
    private String uId;
    private int kills;
    private int deaths;
    private int assists;
    private int econRating;
    private int averageCombatScore;

    public MatchRecord() {
    }

    public MatchRecord(String uId, int kills, int deaths, int assists, int econRating, int averageCombatScore) {
        this.uId = uId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.econRating = econRating;
        this.averageCombatScore = averageCombatScore;
    }

    public String getuId() {
        return uId;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getEconRating() {
        return econRating;
    }

    public int getAverageCombatScore() {
        return averageCombatScore;
    }
}
