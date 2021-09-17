package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;

public class Comment {
    private String commenterUID;
    private String commenterAgent;
    private String commenterRiotID;
    private String commenterTagline;
    private String commenterRank;
    private String comment;

    public Comment() {
    }

    public Comment(String commenterUID, String commenterAgent, String commenterRiotID,
                   String commenterTagline, String commenterRank, String comment) {
        this.commenterUID = commenterUID;
        this.commenterAgent = commenterAgent;
        this.commenterRiotID = commenterRiotID;
        this.commenterTagline = commenterTagline;
        this.commenterRank = commenterRank;
        this.comment = comment;
    }

    public String getCommenterUID() {
        return commenterUID;
    }

    public String getCommenterAgent() {
        return commenterAgent;
    }

    public String getCommenterRiotID() {
        return commenterRiotID;
    }

    public String getCommenterTagline() {
        return commenterTagline;
    }

    public String getCommenterRank() {
        return commenterRank;
    }

    public String getComment() {
        return comment;
    }
}

