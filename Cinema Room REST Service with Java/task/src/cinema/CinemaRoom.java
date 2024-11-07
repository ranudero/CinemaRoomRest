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
}
