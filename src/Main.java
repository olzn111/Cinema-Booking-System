import service.UserService;
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