package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class CinemaController {
    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @RequestMapping("/seats")
    public CinemaRoom getAllSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody Seat seatRequest) {
        if (!cinemaRoom.isValidSeat(seatRequest.getRow(), seatRequest.getColumn())) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        } else if (cinemaRoom.isSeatPurchased(seatRequest.getRow(), seatRequest.getColumn())) {
            throw new IllegalStateException("The ticket has been already purchased!");
        } else {
            return ResponseEntity.ok(cinemaRoom.purchaseSeat(seatRequest.getRow(), seatRequest.getColumn()));
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("{\"error\": \"" + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.badRequest().body("{\"error\": \"" + ex.getMessage() + "\"}");
    }
}
