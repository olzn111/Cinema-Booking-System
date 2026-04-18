// 필요 라이브러리 불러오기 및 패키지 선언
package data;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    // reservation 객체가 가지는 정보를 인스턴스 변수로 선언
    private Movie movie;
    private List<String> seats;
    private int totalPeople;
    private int finalPrice;
    // reservation constructor : 영화, 좌석 인원 수, 결제 금액 받아 초기화
    public Reservation(Movie movie, List<String> seats, int totalPeople, int finalPrice) {
        this.movie = movie;
        this.seats = new ArrayList<>(seats);
        this.totalPeople = totalPeople;
        this.finalPrice = finalPrice;
    }
    // 예매한 영화에 대한 getter
    public Movie getMovie() { return movie; }
    public List<String> getSeats() { return new ArrayList<>(seats); }
    public int getTotalPeople() { return totalPeople; }
    public int getFinalPrice() { return finalPrice; }
    // 예매 내역 출력 시 출력 폼에 맞춰 문자열 형태로 반환
    @Override
    public String toString() {
        String seatNames = String.join(", ", seats);
        return movie.getTitle() + " (" + movie.getStartTime() + ") - "
                + totalPeople + "명 / 좌석: [" + seatNames + "] / 결제금액: " + finalPrice + "원";
    }
}