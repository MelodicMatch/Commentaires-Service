package model;

import java.time.LocalDateTime;

public class EventComment {
    private int id;
    private int eventID;
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

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
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

    // Util

    public String toXML() {
        return "<eventcomment id=\"" + id + "\" author=\"" + userID + "\" " + (replyTo != null ? "replyto=\"" + replyTo + "\"" : "") + ">"
            + "<content>" + content + "</content><datetime>" + date.toString() + "</datetime></eventcomment>";
    }
}
