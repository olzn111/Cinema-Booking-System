package main;

import service.MovieService;
import service.ReservationService;
import service.SampleDataLoader;
import service.UserService;
import ui.ConsoleUi;
import ui.MovieConsoleUi;
import ui.SeatUi;

public class Main {
    public static void main(String[] args) {
        // 1. 서비스 객체 초기화
        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        ReservationService reservationService = new ReservationService();

        // 2. 샘플 데이터 로드 (영화 4개, 각 시간대 5개씩 총 20개 상영 세팅)
        SampleDataLoader dataLoader = new SampleDataLoader(movieService);
        dataLoader.loadData();

        // 3. UI 객체 초기화 및 의존성 주입
        SeatUi seatUi = new SeatUi();
        MovieConsoleUi movieConsoleUi = new MovieConsoleUi(movieService, reservationService, seatUi);
        ConsoleUi consoleUi = new ConsoleUi(userService, movieConsoleUi);

        // 4. 메인 콘솔 시작
        consoleUi.start();
    }
}