import ui.SeatUi;
import service.SampleDataLoader;
import data.Movie;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("========= 영화 예약 시스템 테스트 ========="); //테스트는 나중에 빼기

        // 1. 데이터 로더 실행
        SampleDataLoader loader = new SampleDataLoader();
        loader.loadData();

        // 2. 로드된 영화 목록 확인하기
        List<Movie> movies = loader.getMovies();
        System.out.println("🎬 등록된 영화 개수: " + movies.size() + "개");
        for (Movie m : movies) {
            System.out.println("- " + m.getTitle() + " (" + m.getStartTime() + ")");
        }

        // 3. SeatUi 확인
        SeatUi ui = new SeatUi();
        ui.printStandard();
    }
}