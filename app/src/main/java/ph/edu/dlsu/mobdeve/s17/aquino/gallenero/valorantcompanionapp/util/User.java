package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.util;

public class User {
    private String riotId;
    private String tagline;
    private String rank;
    private String region;
    private String email;
    private String password;

    public User(String riotId, String tagline, String rank, String region, String email, String password) {
        this.riotId = riotId;
        this.tagline = tagline;
        this.rank = rank;
        this.region = region;
        this.email = email;
        this.password = password;
    }

    public String getRiotId() {
        return riotId;
    }

    public String getTagline() {
        return tagline;
    }

    public String getRank() {
        return rank;
    }

    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }

    public String getPassword() {
        return password;
    }
}
