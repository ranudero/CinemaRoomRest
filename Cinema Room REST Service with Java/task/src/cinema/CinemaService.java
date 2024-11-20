package cinema;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    private final CinemaRoom cinemaRoom;

    public CinemaService(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
    public CinemaRoomDto getRoomConfiguration() {
        return CinemaRoomDto.from(cinemaRoom);
    }

    public record CinemaRoomDto (int row, int column, List<SeatDto> seats) {
        public static CinemaRoomDto from(CinemaRoom cinemaRoom) {
            return new CinemaRoomDto(
                    cinemaRoom.getRows(),
                    cinemaRoom.getColumns(),
                    cinemaRoom.getSeats().stream().map(SeatDto::from).collect(Collectors.toList())
            );
        }
    }
    public record SeatDto(int row, int column, int price) {
        public static SeatDto from(Seat seat) {
            return new SeatDto(seat.getRow(), seat.getColumn(), seat.getPrice());
        }
    }
}
