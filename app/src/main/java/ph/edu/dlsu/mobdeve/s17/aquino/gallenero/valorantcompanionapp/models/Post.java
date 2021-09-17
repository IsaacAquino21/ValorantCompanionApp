package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;

public class Post {
    private String posterID;
    private String posterAgent;
    private String posterRiotID;
    private String posterTagline;
    private String posterRank;
    private String postID;
    private String postContent;
    private int numComments;

    public Post() {
    }

    public Post(String posterID, String posterAgent, String posterRiotID,
                String posterTagline, String posterRank, String postID, String postContent){
        this.posterID = posterID;
        this.posterAgent = posterAgent;
        this.posterRiotID = posterRiotID;
        this.posterTagline = posterTagline;
        this.posterRank = posterRank;
        this.postID = postID;
        this.postContent = postContent;

        //initial value
        this.numComments = 0;
    }

    public String getPosterID() {
        return posterID;
    }

    public String getPosterAgent() {
        return posterAgent;
    }

    public String getPosterRiotID() {
        return posterRiotID;
    }

    public String getPosterTagline() {
        return posterTagline;
    }

    public String getPosterRank() {
        return posterRank;
    }

    public String getPostID() {
        return postID;
    }

    public String getPostContent() {
        return postContent;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }
}
