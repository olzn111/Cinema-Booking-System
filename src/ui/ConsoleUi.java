// 패키지 선언 및 라이브러리 파일 불러오기
package ui;

import data.User;
import java.util.Scanner;
import service.UserService;

public class ConsoleUi {
    // 회원 , 영화 예매 콘솔 처리 위한 객체
    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private MovieConsoleUi movieConsoleUi;
    // 필요한 서비스와 객체 저장
    public ConsoleUi(UserService userService, MovieConsoleUi movieConsoleUi) {
        this.userService = userService;
        this.movieConsoleUi = movieConsoleUi;
    }
    // 프로그램 메서드 - 현재 사용자 존재 유무에 따라 매서드 결정 
    public void start() {
        while (true) {
            User currentUser = userService.getCurrentUser();
            if (currentUser == null) showLoginMenu();
            else showHandongMovieMenu(currentUser);
        }
    }
    // 로그인 화면 구성
    private void showLoginMenu() {
        System.out.println("\n== 초기 화면 ==");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 종료");
        System.out.print("메뉴 선택: ");

        try {
            // 해당 화면에 원하는 번호 값 입력받기
            int menu = Integer.parseInt(scanner.nextLine());
            if (menu == 1) {
                registerUser(); // 회원가입
            } else if (menu == 2) {
                loginUser(); // 로그인
            } else if (menu == 3) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0); // 종료
            } else {
                System.out.println("없는 메뉴입니다. 다시 선택해주세요."); //예외
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요."); // 숫자 아닌 것 예외 처리
        }
    }
    // 회원가입 메서드
    private void registerUser() {
        try {
            // 아이디, 비밀번호, 통신사 유무 (등급) 을 입력 받고 적절성 판단하기
            System.out.println("\n[회원가입]");
            System.out.print("아이디: ");
            String id = scanner.nextLine();
            System.out.print("비밀번호: ");
            String pw = scanner.nextLine();
            System.out.print("현재 통신사 멤버십이 있으십니까? (Y/N): ");
            boolean isMembership = scanner.nextLine().equalsIgnoreCase("Y");
            String telecomCompany = "없음";
            String membershipGrade = "없음";
            if (isMembership) {
                System.out.println("통신사를 선택하세요.");
                System.out.println("1. SKT");
                System.out.println("2. KT");
                System.out.println("3. LG U+");
                System.out.print("번호 선택: ");
                int telecomChoice = Integer.parseInt(scanner.nextLine());
                if (telecomChoice == 1) telecomCompany = "SKT";
                else if (telecomChoice == 2) telecomCompany = "KT";
                else if (telecomChoice == 3) telecomCompany = "LG U+";
                else {
                    System.out.println("잘못된 통신사 번호입니다.");
                    return;
                }
                System.out.println("멤버십 등급을 선택하세요.");
                System.out.println("1. VIP (9000원 할인)");
                System.out.println("2. GOLD (5000원 할인)");
                System.out.println("3. SILVER (2000원 할인)");
                System.out.print("번호 선택: ");
                int gradeChoice = Integer.parseInt(scanner.nextLine());
                if (gradeChoice == 1) membershipGrade = "VIP";
                else if (gradeChoice == 2) membershipGrade = "GOLD";
                else if (gradeChoice == 3) membershipGrade = "SILVER";
                else { System.out.println("잘못된 등급 번호입니다.");
                    return;
                }
            }
            // 만약 정상적 인풋이라면 새로운 user로 저장
            boolean success = userService.register(id, pw, isMembership, telecomCompany, membershipGrade);
            // 정상적 입력에 따라 메세지 출력
            if (success)  System.out.println("회원가입이 완료되었습니다.");
            else System.out.println("회원가입에 실패했습니다. 아이디 중복 또는 입력값을 확인해주세요.");
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요."); // 숫자 이외 값은 예외처리
        }
    }
    // 로그인 ui - 아이디 비번 입력 받고 성공 유무 판단
    private void loginUser() {
        System.out.println("\n[로그인]");
        System.out.print("아이디: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        if (userService.login(id, pw)) {
            System.out.println("로그인 성공.");
            showRecommendedDiscounts(userService.getCurrentUser());
        } else {
            System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
    // 추천하는 할인 정보 출력 메서드
    private void showRecommendedDiscounts(User user) {
        System.out.println("\n[추천 할인 안내]");
        // 멤버십 있으면 등급에 따른 할인 정보 출력
        if (user.isMembership()) {
            System.out.println("- 멤버십 할인 가능: "
                    + user.getTelecomCompany() + " / "
                    + user.getMembershipGrade() + " / "
                    + user.getMembershipDiscount() + "원");
        } else {
            System.out.println("- 멤버십 할인 없음");
        }
        // 나이 할인에 대한 정보 출력
        System.out.println("- 나이 할인 안내");
        System.out.println("  · 경로: 1인당 5000원 할인");
        System.out.println("  · 청소년: 1인당 3000원 할인");
        System.out.println("  · 유아(7세 이하): 무료");
        System.out.println("- 시간대 할인 안내");
        // 시간대에 따른 할인 정보 출력
        System.out.println("  · 조조영화(10시 이하 시작): 1인당 2000원 할인");
        System.out.println("  · 심야영화(22시 이상 시작): 1인당 1000원 할인");
        System.out.println("- 예매 시 연령 할인과 조조/심야 할인은 자동 적용됩니다.");
        System.out.println("- 멤버십 할인은 추가 할인으로 선택 적용됩니다.");
    }
    // 영화 예매 메뉴 출력
    private void showHandongMovieMenu(User user) {
        System.out.println("\n== 한동 영화 ==");
        System.out.println("1. 영화 예매하기");
        System.out.println("2. 영화 예매내역 확인");
        System.out.println("3. 예매 취소");
        System.out.println("4. 로그인 정보 확인");
        System.out.println("5. 로그아웃");
        System.out.println("6. 종료");
        System.out.print("메뉴 선택: ");
        try { // 사용자로 부터 메뉴 번호 입력받기
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
            System.out.println("숫자만 입력해주세요.");
        }
    }
    // 로그인 회원 정보 보여주는 메서드 (아이디 멤버십 정보)
    private void showCurrentUserInfo(User user) {
        System.out.println("\n[내 정보]");
        System.out.println("아이디: " + user.getId());
        System.out.println("멤버십 보유 여부: " + (user.isMembership() ? "O" : "X"));

        if (user.isMembership()) {
            System.out.println("통신사: " + user.getTelecomCompany());
            System.out.println("등급: " + user.getMembershipGrade());
            System.out.println("멤버십 할인 금액: " + user.getMembershipDiscount() + "원");
        }
    }
}