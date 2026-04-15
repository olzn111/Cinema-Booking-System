package ui;

import data.Main;
import data.Theater;
import data.User;
import service.UserService;
import service.MovieService;
import service.ReservationService;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private MovieService movieService;
    private ReservationService reservationService;
    private SeatUi seatUi;

    public ConsoleUi(UserService userService, MovieService movieService, ReservationService reservationService, SeatUi seatUi) {
        this.userService = userService;
        this.movieService = movieService;
        this.reservationService = reservationService;
        this.seatUi = seatUi;
    }

    // [변경됨] 메인 메뉴: 가입, 로그인, 로그아웃, 종료만 딱 보여줌!
    public void showStartMenu() {
        System.out.println("\n🎬 Cinema Booking System");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인 (로그인 시 예매로 자동 연결)");
        System.out.println("3. 로그아웃");
        System.out.println("4. 종료");
        System.out.print("선택: ");
    }

    public int inputMenuNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) { return -1; }
    }

    // 1. 회원가입 (하민님 로직 그대로)
    public void registerUser() {
        System.out.println("\n[회원가입 시작]");
        System.out.print("아이디: "); String id = scanner.nextLine();
        System.out.print("비번: "); String pw = scanner.nextLine();
        System.out.print("이름: "); String name = scanner.nextLine();
        System.out.print("나이: "); int age = Integer.parseInt(scanner.nextLine());

        System.out.print("통신사(1.SKT 2.KT 3.LG): "); int tel = Integer.parseInt(scanner.nextLine());
        System.out.print("등급(VIP/GOLD/COMMON): "); String grade = scanner.nextLine();

        System.out.print("군인여부(1.예 0.아니오): "); boolean isSoldier = scanner.nextLine().equals("1");
        System.out.print("장애인여부(1.예 0.아니오): "); boolean isDisabled = scanner.nextLine().equals("1");

        userService.register(id, pw, name, age, getAgeDiscountType(age), true, tel, grade, isSoldier, isDisabled);
        System.out.println("✅ 가입 완료! 이제 로그인해주세요.");
    }

    // 2. [핵심 변경] 로그인 -> 맞춤 정보 출력 -> 예매로 자동 이동
    public void loginUser() {
        System.out.println("\n[로그인]");
        System.out.print("아이디: "); String id = scanner.nextLine();
        System.out.print("비밀번호: "); String pw = scanner.nextLine();

        if (userService.login(id, pw)) {
            User user = userService.getCurrentUser();
            System.out.println("\n✨ " + user.getName() + "님, 환영합니다!");

            // ① 맞춤 할인 정보 요약 띄우기
            showPersonalizedDiscountInfo(user);

            // ② 바로 예매 창(콘솔)으로 이동
            System.out.println("잠시 후 예매 콘솔창으로 연결합니다...");
            startBooking();
        } else {
            System.out.println("❌ 로그인 실패! 아이디와 비밀번호를 확인하세요.");
        }
    }

    // 3. [추가됨] 맞춤 할인 정보 출력 로직
    private void showPersonalizedDiscountInfo(User user) {
        System.out.println("------------------------------------");
        System.out.println("🎁 " + user.getName() + "님을 위한 맞춤 할인 혜택");
        System.out.println("- 연령구분: " + user.getAgeDiscountType() + " (기본 할인 적용)");

        if (user.isMembership()) {
            System.out.println("- 통신사: " + telecomToString(user.getTelecom()) + " [" + user.getMembershipGrade() + "] 추가 할인 가능");
        }
        if (user.isSoldier()) System.out.println("- 특수혜택: 군인 우대 정액 할인(2000원)");
        if (user.isDisabled()) System.out.println("- 특수혜택: 장애인 우대 정액 할인(2000원)");

        System.out.println("※ 모든 할인은 결제 시 자동으로 적용됩니다.");
        System.out.println("------------------------------------");
    }

    // 4. 예매 콘솔창 (하은님 로직)
    public void startBooking() {
        System.out.println("\n🍿 [MOVIE BOOKING CONSOLE]");
        List<Main> movies = movieService.getMovieList();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1) + ". " + movies.get(i).getTitle() + " (" + movies.get(i).getStartTime() + ")");
        }

        System.out.print("영화 번호 선택: ");
        int choice = Integer.parseInt(scanner.nextLine());
        Main movie = movieService.getMovieByNumber(choice);

        System.out.println("\n📍 [" + movie.getTheaterType() + "] 좌석을 선택하세요.");
        if (movie.getTheaterType().equals("Premium")) seatUi.printPremium();
        else seatUi.printStandard();

        System.out.print("좌석 번호(예: A1): ");
        String seat = scanner.nextLine();

        // 가격 계산 및 안내
        int finalPrice = reservationService.calculatePrice(
                new Theater(movie.getTheaterType(), 15000, 5, 5),
                movie, (2026 - userService.getCurrentUser().getAge()),
                userTelecomFullInfo(userService.getCurrentUser()),
                userPrivilege(userService.getCurrentUser()), false
        );

        System.out.println("\n🎉 예매 완료!");
        System.out.println("💰 최종 결제 금액: " + finalPrice + "원");
        System.out.println("📢 \"현장에서 " + finalPrice + "원 결제 도와드리겠습니다.\"");
    }

    public void logoutUser() {
        userService.logout();
        System.out.println("👋 로그아웃 되었습니다.");
    }

    // 헬퍼 메서드들
    private String telecomToString(int t) { return (t==1)?"SKT":(t==2)?"KT":"LG U+"; }
    private String userTelecomFullInfo(User u) { return telecomToString(u.getTelecom()) + " " + u.getMembershipGrade(); }
    private String userPrivilege(User u) { return u.isSoldier()?"군인":u.isDisabled()?"장애인":"일반"; }
    private String getAgeDiscountType(int age) {
        if(age<=6) return "유아"; if(age<=18) return "청소년"; if(age>=65) return "경로우대"; return "일반";
    }
}