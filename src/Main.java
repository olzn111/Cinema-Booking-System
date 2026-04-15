/* import service.UserService;
import service.MovieService;
import service.ReservationService;
import service.SampleDataLoader;
import ui.ConsoleUi;
import ui.SeatUi;

public class Main {
    public static void main(String[] args) {
         System.out.println("🎬 Cinema Booking System 부팅 중...\n");

        // 1. 모든 서비스 객체 생성 (하민님 A파트 + 하은님 B파트)
        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        ReservationService reservationService = new ReservationService();
        SeatUi seatUi = new SeatUi();

        // 2. 하은님의 초기 영화 데이터 로드 (헤일메리, 살목지 등)
        SampleDataLoader loader = new SampleDataLoader(movieService);
        loader.loadData();

        // 3. 대통합 ConsoleUi 생성
        ConsoleUi consoleUi = new ConsoleUi(userService, movieService, reservationService, seatUi);

        boolean run = true;

        // 4. 무한 루프 시작
        while (run) {
            consoleUi.showStartMenu();
            int menu = consoleUi.inputMenuNumber();

            switch (menu) {
                case 1:
                    consoleUi.registerUser();
                    break;
                case 2:
                    // 🌟 로그인 성공 시 맞춤 혜택 창 띄우고 예매로 자동 연결됨!
                    consoleUi.loginUser();
                    break;
                case 3:
                    consoleUi.logoutUser();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다. 이용해 주셔서 감사합니다!");
                    run = false;
                    break;
                default:
                    System.out.println("❌ 잘못된 입력입니다. 1~4 사이의 번호를 입력해주세요.\n");
            }
        }
    }
}
*/

package data;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private String title;
    private String genre;
    private String startTime;
    private String theaterType;

    // 이선좌 검증을 위한 예약 완료 좌석 리스트
    private List<String> bookedSeats;

    public Main(String title, String genre, String startTime, String theaterType) {
        this.title = title;
        this.genre = genre;
        this.startTime = startTime;
        this.theaterType = theaterType;
        this.bookedSeats = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getStartTime() { return startTime; }
    public String getTheaterType() { return theaterType; }
    public List<String> getBookedSeats() { return bookedSeats; }

    // 좌석 예약 처리 (이선좌 방지용)
    // 반환값: 예약 성공 시 true, 이미 예약된 좌석이면 false
    public boolean bookSeat(String seat) {
        if (bookedSeats.contains(seat)) {
            return false;
        }
        bookedSeats.add(seat);
        return true;
    }

    // 예매 취소 시 좌석 반환
    public void cancelSeat(String seat) {
        bookedSeats.remove(seat);
    }
}