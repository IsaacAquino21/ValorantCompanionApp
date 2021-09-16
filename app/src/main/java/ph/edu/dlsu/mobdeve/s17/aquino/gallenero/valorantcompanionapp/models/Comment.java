package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;

public class Comment {
    private String agent;
    private String commenterRiotID;
    private String commenterTagline;
    private String commenterRank;
    private String comment;

    public Comment() {
    }

    public Comment(String agent, String commenterRiotID, String commenterTagline,
                   String commenterRank, String comment) {
        this.agent = agent;
        this.commenterRiotID = commenterRiotID;
        this.commenterTagline = commenterTagline;
        this.commenterRank = commenterRank;
        this.comment = comment;
    }

    public String getAgent() {
        return agent;
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

