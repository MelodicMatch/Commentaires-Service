package controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {
    @DeleteMapping(path = "/deletecomment/music")
    public int deleteMusicComment(@RequestParam(value = "id") int id) {
        DatabaseController controller = new DatabaseController();

        return controller.deleteMusicComment(id);
    }

    @DeleteMapping(path = "/deletecomment/event")
    public int deleteEventComment(@RequestParam(value = "id") int id) {
        DatabaseController controller = new DatabaseController();

        return controller.deleteEventComment(id);
    }

    @DeleteMapping(path = "/deletecomment/playlist")
    public int deletePlaylistComment(@RequestParam(value = "id") int id) {
        DatabaseController controller = new DatabaseController();

        return controller.deletePlaylistComment(id);
    }
}
