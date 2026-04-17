package ui;

import data.Movie;
import data.Reservation;
import data.Theater;
import data.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import service.MovieService;
import service.ReservationService;

public class MovieConsoleUi {
    private Scanner scanner = new Scanner(System.in);
    private MovieService movieService;
    private ReservationService reservationService;
    private SeatUi seatUi;

    private Map<String, List<Reservation>> reservationMap = new HashMap<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public MovieConsoleUi(MovieService movieService, ReservationService reservationService, SeatUi seatUi) {
        this.movieService = movieService;
        this.reservationService = reservationService;
        this.seatUi = seatUi;
    }

    public void startBooking(User user) {
        try {
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
            else {
                System.out.println("잘못된 영화 번호입니다.");
                return;
            }

            List<Movie> allMovies = movieService.getMovieList();
            List<Movie> availableTimes = new ArrayList<>();

            for (Movie m : allMovies) {
                if (m.getTitle().equals(targetTitle)) {
                    availableTimes.add(m);
                }
            }

            if (availableTimes.isEmpty()) {
                System.out.println("상영 중인 영화가 없습니다.");
                return;
            }

            System.out.println("\n[" + targetTitle + " 상영 시간표]");
            for (int i = 0; i < availableTimes.size(); i++) {
                Movie m = availableTimes.get(i);
                System.out.println((i + 1) + ". " + m.getStartTime() + " / " + m.getTheaterType());
            }

            System.out.print("시간 번호 선택: ");
            int timeChoice = Integer.parseInt(scanner.nextLine()) - 1;

            if (timeChoice < 0 || timeChoice >= availableTimes.size()) {
                System.out.println("잘못된 시간 번호입니다.");
                return;
            }

            Movie selectedMovie = availableTimes.get(timeChoice);

            String timeNotice = "";
            if (reservationService.isMorningMovie(selectedMovie)) {
                System.out.print(ANSI_CYAN);
                timeNotice = " [조조영화: 1인당 2000원 할인]";
            } else if (reservationService.isLateNightMovie(selectedMovie)) {
                System.out.print(ANSI_PURPLE);
                timeNotice = " [심야영화: 1인당 1000원 할인]";
            }

            System.out.println("\n선택된 영화: " + selectedMovie.getTitle() + " (" + selectedMovie.getStartTime() + ")" + timeNotice);
            System.out.print(ANSI_RESET);

            System.out.println("연령별 요금 기준");
            System.out.println("- 성인: 정가");
            System.out.println("- 청소년: 3000원 할인");
            System.out.println("- 경로: 5000원 할인");
            System.out.println("- 유아: 무료 (7세 이하)");

            System.out.print("인원 입력 (경로 성인 청소년 유아[7세 이하] 순, 예: 1 2 1 0): ");
            String[] pInput = scanner.nextLine().trim().split("\\s+");

            if (pInput.length != 4) {
                System.out.println("인원은 4개를 입력해야 합니다.");
                return;
            }

            int senior = Integer.parseInt(pInput[0]);
            int adult = Integer.parseInt(pInput[1]);
            int teen = Integer.parseInt(pInput[2]);
            int toddler = Integer.parseInt(pInput[3]);

            int totalPeople = reservationService.calculateTotalPeople(senior, adult, teen, toddler);

            if (totalPeople <= 0) {
                System.out.println("총 인원은 1명 이상이어야 합니다.");
                return;
            }

            List<String> selectedSeats = new ArrayList<>();
            System.out.println("\n[좌석 선택 - 총 " + totalPeople + "석]");

            boolean isPremium = selectedMovie.getTheaterType().equalsIgnoreCase("Premium");

            if (isPremium) {
                seatUi.printPremium(selectedMovie);
            } else {
                seatUi.printStandard(selectedMovie);
            }

            for (int i = 0; i < totalPeople; i++) {
                while (true) {
                    System.out.print((i + 1) + "번째 좌석 번호 입력: ");
                    String seat = scanner.nextLine().trim().toUpperCase();

                    boolean isValid = false;

                    if (isPremium) {
                        if (seat.matches("^[PN](?:[1-9]|1[0-2])$")) {
                            isValid = true;
                        } else {
                            System.out.println("존재하지 않는 좌석입니다. (P1~P12, N1~N12)");
                        }
                    } else {
                        if (seat.matches("^[A-F](?:[1-8])$")) {
                            isValid = true;
                        } else {
                            System.out.println("존재하지 않는 좌석입니다. (A1~F8)");
                        }
                    }

                    if (!isValid) {
                        continue;
                    }

                    if (selectedSeats.contains(seat)) {
                        System.out.println("방금 선택한 좌석입니다. 다른 좌석을 선택해주세요.");
                    } else if (selectedMovie.bookSeat(seat)) {
                        selectedSeats.add(seat);
                        break;
                    } else {
                        System.out.println("이미 예매된 좌석입니다. 다른 좌석을 선택해주세요.");
                    }
                }
            }

            System.out.println("\n[추가 할인 선택]");
            if (user.isMembership()) {
                System.out.println("1. 멤버십 할인 적용 ("
                        + user.getTelecomCompany() + " / "
                        + user.getMembershipGrade() + " / "
                        + user.getMembershipDiscount() + "원)");
            }
            System.out.println("0. 추가 할인 안 함");
            System.out.print("번호 선택: ");
            int dChoice = Integer.parseInt(scanner.nextLine());

            int membershipDiscount = 0;
            if (dChoice == 1 && user.isMembership()) {
                membershipDiscount = user.getMembershipDiscount();
            }

            Theater theater = createTheaterByMovie(selectedMovie);

            int finalPrice = reservationService.calculatePrice(
                    selectedMovie, theater, senior, adult, teen, toddler, membershipDiscount
            );

            Reservation reservation = reservationService.createReservation(
                    selectedMovie, selectedSeats, totalPeople, finalPrice
            );

            if (reservation == null) {
                System.out.println("예매 처리 중 오류가 발생했습니다.");
                for (String seat : selectedSeats) {
                    selectedMovie.cancelSeat(seat);
                }
                return;
            }

            reservationMap.putIfAbsent(user.getId(), new ArrayList<>());
            reservationMap.get(user.getId()).add(reservation);

            System.out.println("\n예매가 완료되었습니다.");
            System.out.println("적용된 시간대 할인: " + reservationService.getTimeDiscountLabel(selectedMovie));
            System.out.println("최종 결제 금액: " + finalPrice + "원");
            System.out.println(reservation);
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식 입력이 잘못되었습니다.");
        }
    }

    public void showReservationHistory(User user) {
        System.out.println("\n[예매 내역]");

        List<Reservation> userReservations = reservationMap.get(user.getId());

        if (userReservations == null || userReservations.isEmpty()) {
            System.out.println("내역이 없습니다.");
            return;
        }

        for (int i = 0; i < userReservations.size(); i++) {
            System.out.println((i + 1) + ". " + userReservations.get(i));
        }
    }

    public void cancelReservation(User user) {
        System.out.println("\n[예매 취소]");

        List<Reservation> userReservations = reservationMap.get(user.getId());

        if (userReservations == null || userReservations.isEmpty()) {
            System.out.println("취소할 예매 내역이 없습니다.");
            return;
        }

        for (int i = 0; i < userReservations.size(); i++) {
            System.out.println((i + 1) + ". " + userReservations.get(i));
        }

        try {
            System.out.print("취소할 번호: ");
            int idx = Integer.parseInt(scanner.nextLine()) - 1;

            if (idx < 0 || idx >= userReservations.size()) {
                System.out.println("잘못된 번호입니다.");
                return;
            }

            Reservation target = userReservations.remove(idx);

            for (String seat : target.getSeats()) {
                target.getMovie().cancelSeat(seat);
            }

            System.out.println("예매가 정상적으로 취소되었습니다.");
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
        }
    }

    private Theater createTheaterByMovie(Movie movie) {
        String theaterType = movie.getTheaterType();

        if (theaterType.equalsIgnoreCase("Premium")) {
            return new Theater("Premium", 15000, 2, 12);
        } else if (theaterType.equalsIgnoreCase("Couple")) {
            return new Theater("Couple", 18000, 4, 8);
        } else {
            return new Theater("Standard", 12000, 6, 8);
        }
    }
}