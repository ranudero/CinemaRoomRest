package cinema;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class CinemaController {
    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @RequestMapping()
    public CinemaRoom getAllSeats() {
        return cinemaRoom;
    }
}
