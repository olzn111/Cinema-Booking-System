// serive, ui package 내 파일 불러오기
import service.MovieService;
import service.ReservationService;
import service.SampleDataLoader;
import service.UserService;
import ui.ConsoleUi;
import ui.MovieConsoleUi;
import ui.SeatUi;

public class Main {
    public static void main(String[] args) {
        
        UserService userService = new UserService(); // 회원 서비스 객체 생성
        MovieService movieService = new MovieService(); // 영화 서비스 객체 생성
        ReservationService reservationService = new ReservationService(); // 예약 서비스 객체 생성

        SampleDataLoader sampleDataLoader = new SampleDataLoader(movieService); // sampledata 로더를 위한 객체 생성
        sampleDataLoader.loadData(); // 데이터 불러오기

        SeatUi seatUi = new SeatUi(); // 좌석을 보여주기 위한 객체 생성
        MovieConsoleUi movieConsoleUi = new MovieConsoleUi(movieService, reservationService, seatUi); // 영화 콘솔을 보여주기 위한 객체 생성
        ConsoleUi consoleUi = new ConsoleUi(userService, movieConsoleUi); // 기본 프로그램 콘솔을 위한 객체 생성

        consoleUi.start(); // 기본 프로그램 콘솔 시작
    }
}