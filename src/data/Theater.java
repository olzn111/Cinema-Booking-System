package data;

public class Theater {
    private String type;         // 상영관 종류 (Standard, Premium 등)
    private int basePrice;       // 기본 가격
    private String[][] seats;    // 좌석 배치도

    // 생성자: 상영관 종류에 따라 좌석 크기를 유동적으로 생성
    public Theater(String type, int basePrice, int rows, int cols) {
        this.type = type;
        this.basePrice = basePrice;
        this.seats = new String[rows][cols];
    }

    // Getter
    public String getType() { return type; }
    public int getBasePrice() { return basePrice; }
    public String[][] getSeats() { return seats; }

    // 예매 처리: 빈 좌석(null)이면 "X"로 표시하고 true 반환
    public boolean reserveSeat(int row, int col) {
        if (seats[row][col] == null) {
            seats[row][col] = "X";
            return true;
        }
        return false; // 이미 예약된 좌석
    }
}