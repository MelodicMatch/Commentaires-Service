package model;

import java.time.LocalDateTime;

public class MusicComment {
    private int id;
    private int musicID;
    private int userID;
    private Integer replyTo;
    private Integer rating;
    private String content;
    private LocalDateTime date;

    // Getters / Setters

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getMusicID() {
        return musicID;
    }

    public void setMusicID(int musicID) {
        this.musicID = musicID;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
        return "<musiccomment id=\"" + id + "\" author=\"" + userID + "\" " + (replyTo != null ? "replyto=\"" + replyTo + "\"" : "") + ">"
                + "<content>" + content + "</content><datetime>" + date.toString() + "</datetime>"
                + (rating != null ? "<rating>" + rating + "</rating>" : "") + "</musiccomment>";
    }
}
