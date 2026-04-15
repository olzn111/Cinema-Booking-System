package data;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private String startTime;
    private String theaterType;

    // 예약된 좌석들을 기억하는 리스트 (이선좌 방지용)
    private List<String> bookedSeats;

    public Movie(String title, String genre, String startTime, String theaterType) {
        this.title = title;
        this.genre = genre;
        this.startTime = startTime;
        this.theaterType = theaterType;
        this.bookedSeats = new ArrayList<>(); // 초기화
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getStartTime() { return startTime; }
    public String getTheaterType() { return theaterType; }

    // 예약된 좌석 리스트 반환
    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    // [여기에 bookSeat가 있습니다!]
    // 좌석 예매 시도 (true면 성공, false면 이미 있는 좌석)
    public boolean bookSeat(String seat) {
        if (bookedSeats.contains(seat)) {
            return false;
        }
        bookedSeats.add(seat);
        return true;
    }

    // 예매 취소 시 좌석 리스트에서 삭제하여 다시 빈자리로 만듦
    public void cancelSeat(String seat) {
        bookedSeats.remove(seat);
    }
}