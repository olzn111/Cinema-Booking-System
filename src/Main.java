import service.UserService;
import ui.ConsoleUi;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        ConsoleUi consoleUi = new ConsoleUi(userService);

        boolean run = true;

        while (run) {
            consoleUi.showStartMenu();
            int menu = consoleUi.inputMenuNumber();

            switch (menu) {
                case 1:
                    consoleUi.registerUser();
                    break;
                case 2:
                    consoleUi.loginUser();
                    break;
                case 3:
                    consoleUi.showCurrentUserInfo();
                    break;
                case 4:
                    consoleUi.logoutUser();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    run = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.\n");
            }
        }
    }
}
