package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping()
public class CinemaController {
    private final CinemaRoom cinemaRoom;
    private final CinemaService cinemaService;

    public CinemaController(CinemaRoom cinemaRoom, CinemaService cinemaService) {
        this.cinemaRoom = cinemaRoom;
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public ResponseEntity<CinemaService.CinemaRoomDto> getAllSeats() {
        return ResponseEntity.ok(cinemaService.getRoomConfiguration());
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody Seat seatRequest) {
        if (!cinemaRoom.isValidSeat(seatRequest.getRow(), seatRequest.getColumn())) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        } else if (cinemaRoom.isSeatPurchased(seatRequest.getRow(), seatRequest.getColumn())) {
            throw new IllegalStateException("The ticket has been already purchased!");
        } else {
            Seat purchasedSeat = cinemaRoom.purchaseSeat(seatRequest.getRow(), seatRequest.getColumn());
            TicketResponse response = new TicketResponse(purchasedSeat);
            cinemaRoom.setToken(purchasedSeat, response.getToken());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/return")
    public  ResponseEntity<Object> returnSeat(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Seat returnedSeat = cinemaRoom.returnSeat(token);
        if (returnedSeat == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Wrong token!\"}");
        }
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("ticket", returnedSeat);
        return ResponseEntity.ok(responseBody);
        }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam Map<String, String> params) {
        String password = params.get("password");
        if (!cinemaRoom.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"The password is wrong!\"}");
        }
        Map<String, Object> stats = new HashMap<>();
        stats.put("income", cinemaRoom.getTotalIncome());
        stats.put("available", cinemaRoom.getAvailableSeats());
        stats.put("purchased", cinemaRoom.getPurchasedTickets());
        return ResponseEntity.ok(stats);
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
