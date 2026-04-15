package ui;

public class SeatUi {
    // 1. Standard: 8x6 꽉 찬 배열
    public void printStandard() {
        System.out.println("========== SCREEN (Standard) ==========");
        for (char row = 'A'; row <= 'F'; row++) {
            System.out.print(row + "  ");
            for (int col = 1; col <= 8; col++) {
                System.out.print("[" + row + col + "] ");
            }
            System.out.println();
        }
    }

    // 2. Premium / IMAX: P/N 구역 및 넓은 간격
    public void printPremium() {
        System.out.println("========== SCREEN (Premium) ==========");
        // P구역과 N구역을 나눠서 출력 (이미지 참고)
        printRowWithPrefix('A', "P", 1, 6);
        printRowWithPrefix('B', "P", 7, 12);
        System.out.println(); // 중간 통로
        printRowWithPrefix('C', "N", 1, 6);
        printRowWithPrefix('D', "N", 7, 12);
    }

    // 3. Couple: [A1-A2] 쌍으로 묶기
    public void printCouple() {
        System.out.println("========== SCREEN (Couple) ==========");
        for (char row = 'A'; row <= 'D'; row++) {
            System.out.print(row + "  ");
            for (int col = 1; col <= 7; col += 2) {
                System.out.print("[" + row + col + "-" + row + (col + 1) + "]   ");
            }
            System.out.println("\n");
        }
    }

    private void printRowWithPrefix(char rowName, String prefix, int start, int end) {
        System.out.print(rowName + "  ");
        for (int i = start; i <= end; i++) {
            System.out.print("[" + prefix + i + "]  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SeatUi testUi = new SeatUi();

        System.out.println("\n[1. Standard 관 테스트]");
        testUi.printStandard();

        System.out.println("\n[2. Premium 관 테스트]");
        testUi.printPremium();

        System.out.println("\n[3. Couple 관 테스트]");
        testUi.printCouple();
    }
}