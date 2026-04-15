package data;

public class Reservation {
    private String userId;       // 예매한 사용자 ID
    private String movieTitle;   // 예매한 영화 제목
    private String seatNumber;   // 선택한 좌석 (예: "A1")
    private int price;           // 최종 결제 금액 (할인 적용 후)

    public Reservation(String userId, String movieTitle, String seatNumber, int price) {
        this.userId = userId;
        this.movieTitle = movieTitle;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Getter (내역 확인 출력용)
    public String getUserId() { return userId; }
    public String getMovieTitle() { return movieTitle; }
    public String getSeatNumber() { return seatNumber; }
    public int getPrice() { return price; }
}