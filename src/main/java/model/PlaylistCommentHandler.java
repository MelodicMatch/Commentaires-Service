package model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PlaylistCommentHandler extends DefaultHandler {
    // STATIC

    protected static final String COMMENT = "playlistcomment";
    protected static final String CONTENT = "content";

    // Variables

    protected StringBuilder value;

    protected PlaylistComment comment;

    // Getter

    public PlaylistComment getComment() {
        return comment;
    }

    // DefaultHandler

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (value == null) {
            value = new StringBuilder();
        } else {
            value.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        comment = new PlaylistComment();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case COMMENT:
                comment.setPlaylistID(Integer.parseInt(attr.getValue("playlistid")));
                comment.setUserID(Integer.parseInt(attr.getValue("author")));
                if (attr.getValue("replyto") != null) {
                    comment.setReplyTo(Integer.parseInt(attr.getValue("replyto")));
                }
                break;
            case CONTENT:
                // Nothing
                break;
        }
        value = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case COMMENT:
                // Nothing
                break;
            case CONTENT:
                comment.setContent(value.toString());
                break;
        }
    }
}
