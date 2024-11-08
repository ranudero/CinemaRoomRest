package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;
    final private int priceFront = 10;
    final private int priceBack = 8;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? priceFront : priceBack;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
