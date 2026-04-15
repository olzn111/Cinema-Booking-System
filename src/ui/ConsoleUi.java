package ui;

import data.User;
import service.UserService;
import java.util.Scanner;

public class ConsoleUi {
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private MovieConsoleUi movieConsoleUi;

    public ConsoleUi(UserService userService, MovieConsoleUi movieConsoleUi) {
        this.userService = userService;
        this.movieConsoleUi = movieConsoleUi;
    }

    public void start() {
        while (true) {
            User currentUser = userService.getCurrentUser();
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showHandongMovieMenu(currentUser);
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\n== 초기 화면 ==");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 종료");
        System.out.print("메뉴 선택: ");

        try {
            int menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1) registerUser();
            else if (menu == 2) loginUser();
            else if (menu == 3) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            } else {
                System.out.println("없는 메뉴입니다. 다시 선택해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요!");
        }
    }

    private void registerUser() {
        System.out.println("\n[회원가입]");
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        System.out.print("통신사 멤버십에 가입하시겠습니까? (Y/N): ");
        boolean isMembership = scanner.nextLine().equalsIgnoreCase("Y");

        // UserService의 register 메서드에 전달
        userService.register(id, pw, isMembership);
        System.out.println("회원가입 완료.");
    }

    private void loginUser() {
        System.out.println("\n[로그인]");
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        if (userService.login(id, pw)) {
            System.out.println("로그인 성공.");
        } else {
            System.out.println("정보가 일치하지 않습니다.");
        }
    }

    private void showHandongMovieMenu(User user) {
        System.out.println("\n== 한동 영화 ==");
        System.out.println("1. 영화 예매하기");
        System.out.println("2. 영화 예매내역 확인");
        System.out.println("3. 예매 취소");
        System.out.println("4. 로그인 내용 확인");
        System.out.println("5. 로그아웃");
        System.out.println("6. 종료");
        System.out.println("===========");
        System.out.print("메뉴 선택: ");

        try {
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1:
                    movieConsoleUi.startBooking(user);
                    break;
                case 2:
                    movieConsoleUi.showReservationHistory(user);
                    break;
                case 3:
                    movieConsoleUi.cancelReservation(user);
                    break;
                case 4:
                    showCurrentUserInfo(user);
                    break;
                case 5:
                    userService.logout();
                    System.out.println("로그아웃 되었습니다.");
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("없는 메뉴입니다. 다시 선택해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요!");
        }
    }

    private void showCurrentUserInfo(User user) {
        System.out.println("\n[내 정보]");
        System.out.println("아이디: " + user.getId());
        // null 체크를 통해 에러 방지
        if (user != null) {
            System.out.println("멤버십 가입 여부: " + (user.isMembership() ? "O" : "X"));
        }
    }
}