package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.util;

public class User {
    private String riotId;
    private String tagline;
    private String rank;
    private String email;
    private String country;

    public User(String riotId, String tagline, String rank, String email, String country) {
        this.riotId = riotId;
        this.tagline = tagline;
        this.rank = rank;
        this.email = email;
        this.country = country;
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

    public String getCountry() {
        return country;
    }
}
