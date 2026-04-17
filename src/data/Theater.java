package data;

public class Theater {
    private String type;
    private int basePrice;
    private int rows;
    private int cols;

    public Theater(String type, int basePrice, int rows, int cols) {
        this.type = type;
        this.basePrice = basePrice;
        this.rows = rows;
        this.cols = cols;
    }

    public String getType() {
        return type;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {
        return type + "관 / 기본가격: " + basePrice + "원 / 좌석 수: " + rows + "x" + cols;
    }
}