package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaRoom {
    private int rows;
    private int columns;
    private List<Seat> seats;
    @JsonIgnore
    private final Map<String, Seat> purchasedTickets = new HashMap<>();
    @JsonIgnore
    private int totalIncome;
    @JsonIgnore
    private final String password = "super_secret";

    public String getPassword() {
        return password;
    }

    public CinemaRoom(int totalRows, int totalColumns) {
        this.rows = totalRows;
        this.columns = totalColumns;
        this.totalIncome = 0;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                seats.add(new Seat(i, j));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Seat purchaseSeat(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {

                seat.setPurchased(true);
                totalIncome += seat.getPrice();
                return seat;
            }
        }
        return null;
    }

    public void setToken(Seat seat, String token) {
        purchasedTickets.put(token, seat);
    }

    public boolean isValidSeat(int row, int column) {
        return row >= 1 && row <= rows && column >= 1 && column <= columns;
    }

    public boolean isSeatPurchased(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat.isPurchased();
            }
        }
        return false;
    }

    public Seat getSeatByToken(String token) {
        return purchasedTickets.get(token);
    }

    public Seat returnSeat(String token){
        Seat seat = purchasedTickets.remove(token);
        if (seat != null) {
            seat.setPurchased(false);
            totalIncome -= seat.getPrice();
        }
        return seat;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public int getAvailableSeats(){
        return (int) seats.stream().filter(seat -> !seat.isPurchased()).count();
    }

    public int getPurchasedTickets(){
        return (int) seats.stream().filter(Seat::isPurchased).count();
    }

}
