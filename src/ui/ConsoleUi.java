package ui;

import data.User;
import java.util.Scanner;
import service.UserService;

public class ConsoleUi {
    private Scanner scanner;
    private UserService userService;

    public ConsoleUi(UserService userService) {
        this.scanner = new Scanner(System.in);
        this.userService = userService;
    }

    public void showStartMenu() {
        System.out.println("===== 영화 예매 프로그램 =====");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 현재 회원 정보 보기");
        System.out.println("4. 로그아웃");
        System.out.println("5. 종료");
        System.out.print("메뉴 선택: ");
    }

    public int inputMenuNumber() {
        while (!scanner.hasNextInt()) {
            System.out.print("숫자만 입력하세요: ");
            scanner.nextLine();
        }
        int menu = scanner.nextInt();
        scanner.nextLine();
        return menu;
    }

    public void registerUser() {
        System.out.println("\n[회원가입]");

        System.out.print("아이디 입력: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호 입력: ");
        String password = scanner.nextLine();

        System.out.print("이름 입력: ");
        String name = scanner.nextLine();

        System.out.print("나이 입력: ");
        int age = inputInt();

        String ageDiscountType = getAgeDiscountType(age);
        System.out.println("연령 할인 구분: " + ageDiscountType);

        System.out.print("멤버십 가입 여부 입력 (1: 예, 0: 아니오): ");
        boolean membership = inputBooleanByZeroOne();

        int telecom = 0;
        String membershipGrade = "없음";

        if (membership) {
            System.out.println("통신사 선택");
            System.out.println("1. SKT");
            System.out.println("2. KT");
            System.out.println("3. LG U+");
            System.out.print("통신사 번호 입력: ");
            telecom = inputTelecom();

            System.out.print("멤버십 등급 입력 (예: COMMON, VIP, GOLD): ");
            membershipGrade = scanner.nextLine();
        }

        System.out.print("군인 여부 입력 (1: 예, 0: 아니오): ");
        boolean soldier = inputBooleanByZeroOne();

        System.out.print("장애인 여부 입력 (1: 예, 0: 아니오): ");
        boolean disabled = inputBooleanByZeroOne();

        boolean result = userService.register(
                id, password, name,
                age, ageDiscountType,
                membership, telecom, membershipGrade,
                soldier, disabled
        );

        if (result) {
            System.out.println("회원가입 성공\n");
        } else {
            System.out.println("이미 존재하는 아이디입니다.\n");
        }
    }

    public void loginUser() {
        System.out.println("\n[로그인]");

        System.out.print("아이디 입력: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호 입력: ");
        String password = scanner.nextLine();

        boolean result = userService.login(id, password);

        if (result) {
            User currentUser = userService.getCurrentUser();
            System.out.println(currentUser.getName() + "님, 로그인 성공\n");
        } else {
            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.\n");
        }
    }

    public void showCurrentUserInfo() {
        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            System.out.println("\n현재 로그인한 사용자가 없습니다.\n");
            return;
        }

        System.out.println("\n[현재 로그인 사용자 정보]");
        System.out.println("이름: " + currentUser.getName());
        System.out.println("아이디: " + currentUser.getId());
        System.out.println("나이: " + currentUser.getAge());
        System.out.println("연령 할인 구분: " + currentUser.getAgeDiscountType());
        System.out.println("멤버십 여부: " + (currentUser.isMembership() ? "예" : "아니오"));

        if (currentUser.isMembership()) {
            System.out.println("통신사: " + telecomToString(currentUser.getTelecom()));
            System.out.println("멤버십 등급: " + currentUser.getMembershipGrade());
        }

        System.out.println("군인 여부: " + (currentUser.isSoldier() ? "예" : "아니오"));
        System.out.println("장애인 여부: " + (currentUser.isDisabled() ? "예" : "아니오"));
        System.out.println();
    }

    public void logoutUser() {
        if (userService.getCurrentUser() == null) {
            System.out.println("\n현재 로그인된 사용자가 없습니다.\n");
            return;
        }

        userService.logout();
        System.out.println("\n로그아웃 되었습니다.\n");
    }

    private int inputInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("숫자만 입력하세요: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private boolean inputBooleanByZeroOne() {
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("1 또는 0만 입력하세요: ");
                scanner.nextLine();
            }

            int value = scanner.nextInt();
            scanner.nextLine();

            if (value == 1) {
                return true;
            } else if (value == 0) {
                return false;
            } else {
                System.out.print("잘못된 입력입니다. 1 또는 0만 입력하세요: ");
            }
        }
    }

    private int inputTelecom() {
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("1, 2, 3 중 하나를 입력하세요: ");
                scanner.nextLine();
            }

            int telecom = scanner.nextInt();
            scanner.nextLine();

            if (telecom >= 1 && telecom <= 3) {
                return telecom;
            } else {
                System.out.print("잘못된 입력입니다. 1, 2, 3 중 하나를 입력하세요: ");
            }
        }
    }

    private String getAgeDiscountType(int age) {
        if (age >= 0 && age <= 6) {
            return "유아";
        } else if (age >= 7 && age <= 18) {
            return "청소년";
        } else if (age >= 65) {
            return "경로우대";
        } else {
            return "일반";
        }
    }

    private String telecomToString(int telecom) {
        switch (telecom) {
            case 1:
                return "SKT";
            case 2:
                return "KT";
            case 3:
                return "LG U+";
            default:
                return "없음";
        }
    }
}