package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;

public class MatchRecord {
    private String agent;
    private String matchType;
    private String matchResult;
    private int kills;
    private int deaths;
    private int assists;
    private int econRating;
    private int averageCombatScore;

    public MatchRecord() {
    }

    public MatchRecord(String agent, String matchType, String matchResult, int kills, int deaths,
                       int assists, int econRating, int averageCombatScore) {
        this.agent = agent;
        this.matchType = matchType;
        this.matchResult = matchResult;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.econRating = econRating;
        this.averageCombatScore = averageCombatScore;
    }

    public String getAgent() {
        return agent;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getMatchResult() {
        return matchResult;
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
