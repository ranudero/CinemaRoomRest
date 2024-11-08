package cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public CinemaRoom(int totalRows, int totalColumns) {
        this.rows = totalRows;
        this.columns = totalColumns;
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
                return seat;
            }
        }
        return null;
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
}
