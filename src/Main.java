import ui.SeatUi;
import service.SampleDataLoader;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 영화 예약 시스템을 시작합니다!");

        // 1. 하은님이 만든 데이터 로더 테스트
        SampleDataLoader loader = new SampleDataLoader();
        loader.loadData();
        System.out.println("✅ 영화 데이터 로드 완료!");

        // 2. 하은님이 만든 좌석 UI 테스트
        SeatUi ui = new SeatUi();
        ui.printStandard();
    }
}