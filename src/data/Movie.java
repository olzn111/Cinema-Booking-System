package data;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    // Movie 객체가 가지는 정보, 인스턴스 변수
    private String title; 
    private String genre;
    private String startTime;
    private String theaterType;
    private List<String> bookedSeats;
    // movie class 를 위한 생성자 (영화 제목, 장르, 시작시간, 영화관 정보가 들어감)
    public Movie(String title, String genre, String startTime, String theaterType) {
        this.title = title;
        this.genre = genre;
        this.startTime = startTime;
        this.theaterType = theaterType;
        this.bookedSeats = new ArrayList<>();
    }
    // 각 인스탄스 변수에 대한 getter
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getStartTime() { return startTime; }
    public String getTheaterType() { return theaterType; }

    //예약된 좌석 목록 반환
    public List<String> getBookedSeats() {
        return new ArrayList<>(bookedSeats);
    }
    // 전달받은 좌석이 예매된 좌석인지 확인하여 Boolean return
    public boolean isSeatBooked(String seat) {
        return bookedSeats.contains(seat);
    }
    // 좌석 예매 상태에 따라 이미 예매되어 있다면 false 반환, 예매 안 되어있으면 리스트 추가하고 true 반환
    public boolean bookSeat(String seat) {
        if (bookedSeats.contains(seat)) {
            return false;
        }
        bookedSeats.add(seat);
        return true;
    }
    // 예매 좌석 취소 - 리스트에서 해당 좌석 예약 내용 제거
    public void cancelSeat(String seat) {
        bookedSeats.remove(seat);
    }
    // 영화 객체 정보를 형식에 맞춰 문자열 형태로 변환
    @Override
    public String toString() {
        return title + " / " + genre + " / " + startTime + " / " + theaterType;
    }
}