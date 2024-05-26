package controllers;

import model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;

@RestController
public class PostController {
    @RequestMapping(value = "/comment/event", method = RequestMethod.POST, consumes = "application/xml")
    public int addEventComment(@RequestBody String flux) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        EventCommentHandler handler = new EventCommentHandler();

        saxParser.parse(new InputSource(new StringReader(flux)), handler);

        EventComment comment = handler.getComment();

        DatabaseController controller = new DatabaseController();

        return controller.addEventComment(comment);
    }

    @RequestMapping(value = "/comment/playlist", method = RequestMethod.POST, consumes = "application/xml")
    public int addPlaylistComment(@RequestBody String flux) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PlaylistCommentHandler handler = new PlaylistCommentHandler();

        saxParser.parse(new InputSource(new StringReader(flux)), handler);

        PlaylistComment comment = handler.getComment();

        DatabaseController controller = new DatabaseController();

        return controller.addPlaylistComment(comment);
    }

    @RequestMapping(value = "/comment/music", method = RequestMethod.POST, consumes = "application/xml")
    public int addMusicComment(@RequestBody String flux) throws Exception {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        MusicCommentHandler handler = new MusicCommentHandler();

        saxParser.parse(new InputSource(new StringReader(flux)), handler);

        MusicComment comment = handler.getComment();

        DatabaseController controller = new DatabaseController();

        if ((comment.getRating() == null) || (controller.canRateMusic(comment.getUserID(), comment.getMusicID()) == 1)) {
            return controller.addMusicComment(comment);
        } else {
            return -1;
        }
    }
}
