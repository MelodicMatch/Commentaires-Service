package model;

import java.time.LocalDateTime;

public class PlaylistComment {

    private int id;
    private int playlistID;
    private int userID;
    private Integer replyTo;
    private String content;
    private LocalDateTime date;

    // Getters / Setters

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String toXML() {
        return "<playlistcomment id=\"" + id + "\" author=\"" + userID + "\" " + (replyTo != null ? "replyto=\"" + replyTo + "\"" : "") + ">"
                + "<content>" + content + "</content><datetime>" + date.toString() + "</datetime></playlistcomment>";
    }
}
