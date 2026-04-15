package ui;

import data.User;
import service.MovieService;
import service.ReservationService;

public class MovieConsoleUi {
    private MovieService movieService;
    private ReservationService reservationService;
    private SeatUi seatUi;

    public MovieConsoleUi(MovieService movieService, ReservationService reservationService, SeatUi seatUi) {
        this.movieService = movieService;
        this.reservationService = reservationService;
        this.seatUi = seatUi;
    }

    // 영화 예매 메인 진입점
    public void startBooking(User user) {
        System.out.println("영화 예매 콘솔 진입 (다음 단계에서 인원수/할인/좌석 로직 구현 예정)");
    }

    // 예매 내역 확인
    public void showReservationHistory(User user) {
        System.out.println("예매 내역 조회 (다음 단계 구현 예정)");
    }

    // 예매 취소
    public void cancelReservation(User user) {
        System.out.println("예매 취소 (다음 단계 구현 예정)");
    }
}