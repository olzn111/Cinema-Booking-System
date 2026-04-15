package ui;

import data.Movie;
import data.User;
import data.Reservation;
import service.MovieService;
import service.ReservationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieConsoleUi {
    private Scanner scanner = new Scanner(System.in);
    private MovieService movieService;
    private ReservationService reservationService;
    private SeatUi seatUi;
    private List<Reservation> reservations = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public MovieConsoleUi(MovieService movieService, ReservationService reservationService, SeatUi seatUi) {
        this.movieService = movieService;
        this.reservationService = reservationService;
        this.seatUi = seatUi;
    }

    public void startBooking(User user) {
        System.out.println("\n[영화 선택]");
        System.out.println("1. 헤일메리");
        System.out.println("2. 살목지");
        System.out.println("3. 귀멸의 칼날");
        System.out.println("4. 왕과 사는 남자");
        System.out.print("영화 번호 선택 (1~4): ");
        int titleChoice = Integer.parseInt(scanner.nextLine());

        String targetTitle = "";
        if (titleChoice == 1) targetTitle = "헤일메리";
        else if (titleChoice == 2) targetTitle = "살목지";
        else if (titleChoice == 3) targetTitle = "귀멸의 칼날";
        else if (titleChoice == 4) targetTitle = "왕과 사는 남자";

        List<Movie> allMovies = movieService.getMovieList();
        List<Movie> availableTimes = new ArrayList<>();
        for (Movie m : allMovies) {
            if (m.getTitle().equals(targetTitle)) {
                availableTimes.add(m);
            }
        }

        System.out.println("\n[" + targetTitle + " 상영 시간표]");
        for (int i = 0; i < availableTimes.size(); i++) {
            Movie m = availableTimes.get(i);
            System.out.println((i + 1) + ". " + m.getStartTime() + " / " + m.getTheaterType());
        }
        System.out.print("시간 번호 선택: ");
        int timeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        Movie selectedMovie = availableTimes.get(timeChoice);

        int hour = Integer.parseInt(selectedMovie.getStartTime().split(":")[0]);
        String timeNotice = "";
        if (hour <= 10) {
            System.out.print(ANSI_CYAN);
            timeNotice = " [조조영화입니다]";
        } else if (hour >= 22) {
            System.out.print(ANSI_PURPLE);
            timeNotice = "[심야영화입니다]";
        }

        System.out.println("\n선택된 영화: " + selectedMovie.getTitle() + " (" + selectedMovie.getStartTime() + ")" + timeNotice);
        System.out.print(ANSI_RESET);

        System.out.print("인원 입력 (경로 성인 청소년 유아 순 공백 구분, 예: 1 0 0 1): ");
        String[] pInput = scanner.nextLine().split(" ");
        int senior = Integer.parseInt(pInput[0]);
        int adult = Integer.parseInt(pInput[1]);
        int teen = Integer.parseInt(pInput[2]);
        int toddler = Integer.parseInt(pInput[3]);
        int totalPeople = senior + adult + teen + toddler;

        List<String> selectedSeats = new ArrayList<>();
        System.out.println("\n[좌석 선택 - 총 " + totalPeople + "석]");

        boolean isPremium = selectedMovie.getTheaterType().equals("Premium");
        if (isPremium) seatUi.printPremium();
        else seatUi.printStandard();

        for (int i = 0; i < totalPeople; i++) {
            while (true) {
                System.out.print((i + 1) + "번째 좌석 번호 입력: ");
                String seat = scanner.nextLine().trim().toUpperCase();

                // 1. 형식 검사
                if (!seat.matches("^[A-Z][0-9]+$")) {
                    System.out.println("잘못된 좌석 형식입니다. (예: P1, N1, A1)");
                    continue;
                }

                char prefix = seat.charAt(0);
                int num = Integer.parseInt(seat.substring(1));

                // 2. 상영관별 범위 검사 [핵심 수정 부분]
                boolean isValid = false;
                if (isPremium) {
                    // 프리미엄관: P1~P12 또는 N1~N12 인지 확인
                    if ((prefix == 'P' || prefix == 'N') && (num >= 1 && num <= 12)) {
                        isValid = true;
                    } else {
                        System.out.println("존재하지 않는 좌석입니다! (P1~P12, N1~N12 중에서 선택해주세요)");
                    }
                } else {
                    // 일반관: A~E행, 1~8열 인지 확인
                    if ((prefix >= 'A' && prefix <= 'E') && (num >= 1 && num <= 8)) {
                        isValid = true;
                    } else {
                        System.out.println("존재하지 않는 좌석입니다! (A~E행, 1~8열 중에서 선택해주세요)");
                    }
                }

                if (!isValid) continue;

                // 3. 중복 및 예약 처리
                if (selectedSeats.contains(seat)) {
                    System.out.println("방금 선택하신 좌석입니다. 다른 좌석을 선택해주세요.");
                } else if (selectedMovie.bookSeat(seat)) {
                    selectedSeats.add(seat);
                    break;
                } else {
                    System.out.println("이미 예매된 좌석입니다. 다른 좌석을 선택해주세요.");
                }
            }
        }

        System.out.println("\n[할인 정보 (본인 1표만 적용)]");
        System.out.println("1. 연령 할인 (" + user.getAgeDiscountType() + ")");
        if (user.isMembership()) System.out.println("2. 멤버십 할인");
        System.out.println("0. 할인 안 함");
        System.out.print("번호 선택: ");
        int dChoice = Integer.parseInt(scanner.nextLine());

        int discount = 0;
        if (dChoice == 1) discount = 2000;
        else if (dChoice == 2) discount = 3000;

        int finalPrice = reservationService.calculatePrice(
                new data.Theater(selectedMovie.getTheaterType(), 15000, 5, 5),
                senior, adult, teen, toddler, discount);

        Reservation res = new Reservation(selectedMovie, selectedSeats, totalPeople, finalPrice);
        reservations.add(res);

        System.out.println("\n예매가 완료되었습니다.");
    }

    public void showReservationHistory(User user) {
        System.out.println("\n[예매 내역]");
        if (reservations.isEmpty()) {
            System.out.println("내역이 없습니다.");
            return;
        }
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println((i + 1) + ". " + reservations.get(i).toString());
        }
    }

    public void cancelReservation(User user) {
        System.out.println("\n[예매 취소]");
        showReservationHistory(user);
        if (reservations.isEmpty()) return;

        System.out.print("취소할 번호: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        Reservation target = reservations.remove(idx);

        for (String seat : target.getSeats()) {
            target.getMovie().cancelSeat(seat);
        }
        System.out.println("예매가 정상적으로 취소되었습니다.");
    }
}