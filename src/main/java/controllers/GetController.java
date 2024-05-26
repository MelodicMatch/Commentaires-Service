package controllers;

import model.EventComment;
import model.MusicComment;
import model.PlaylistComment;
import model.Sorting;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetController {

    @GetMapping(path = "/comments/music", produces = "application/xml")
    public String getMusicComments(@RequestParam(value = "id") int id,
                                   @RequestParam(value = "sort", defaultValue = "dtasc") String sort,
                                   @RequestParam(value = "amnt", defaultValue = "10") int amount,
                                   @RequestParam(value = "strtAt", defaultValue = "1") int startAt) {

        if ((amount < 1) || (startAt < 0)) {
            return "<commentlist></commentlist";
        }

        DatabaseController controller = new DatabaseController();

        List<MusicComment> list = controller.getMusicComments(id, Sorting.fromString(sort), amount, startAt);

        if (list == null) {
            return "<commentlist></commentlist>";
        }

        StringBuilder string = new StringBuilder();

        string.append("<commentlist>");
        int max = list.size();
        for (int i = 0; i < max; i++) {
            string.append(list.get(i).toXML());
        }
        string.append("</commentlist>");

        return string.toString();
    }

    @GetMapping(path = "/comments/event", produces = "application/xml")
    public String getEventComments(@RequestParam(value = "id") int id,
                                   @RequestParam(value = "sort", defaultValue = "dtasc") String sort,
                                   @RequestParam(value = "amnt", defaultValue = "10") int amount,
                                   @RequestParam(value = "strtAt", defaultValue = "1") int startAt) {

        if ((amount < 1) || (startAt < 0)) {
            return "<commentlist></commentlist";
        }

        DatabaseController controller = new DatabaseController();

        List<EventComment> list = controller.getEventComments(id, Sorting.fromString(sort), amount, startAt);

        if (list == null) {
            return "<commentlist></commentlist>";
        }

        StringBuilder string = new StringBuilder();

        string.append("<commentlist>");
        int max = list.size();
        for (int i = 0; i < max; i++) {
            string.append(list.get(i).toXML());
        }
        string.append("</commentlist>");

        return string.toString();
    }

    @GetMapping(path = "/comments/playlist", produces = "application/xml")
    public String getPlaylistComments(@RequestParam(value = "id") int id,
                                      @RequestParam(value = "sort", defaultValue = "dtasc") String sort,
                                      @RequestParam(value = "amnt", defaultValue = "10") int amount,
                                      @RequestParam(value = "strtAt", defaultValue = "1") int startAt) {

        if ((amount < 1) || (startAt < 0)) {
            return "<commentlist></commentlist";
        }

        DatabaseController controller = new DatabaseController();

        List<PlaylistComment> list = controller.getPlaylistComments(id, Sorting.fromString(sort), amount, startAt);

        if (list == null) {
            return "<commentlist></commentlist>";
        }

        StringBuilder string = new StringBuilder();

        string.append("<commentlist>");
        int max = list.size();
        for (int i = 0; i < max; i++) {
            string.append(list.get(i).toXML());
        }
        string.append("</commentlist>");

        return string.toString();
    }

    @GetMapping(path = "/ratability/music")
    public int getMusicRatability(@RequestParam(value = "id") int id, @RequestParam(value = "user") int user) {

        DatabaseController controller = new DatabaseController();

        return controller.canRateMusic(user, id);
    }
}
