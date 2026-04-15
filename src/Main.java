import data.Movie;
import service.MovieService;
import service.SampleDataLoader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎬 Cinema Booking System 시작...\n");

        // 1. 서비스 준비
        MovieService movieService = new MovieService();

        // 2. 데이터 로더로 영화 4개 집어넣기
        SampleDataLoader loader = new SampleDataLoader(movieService);
        loader.loadData();

        // 3. 잘 들어갔는지 화면에 출력해보기 (하은님의 Getter 활용!)
        System.out.println("\n========= 현재 상영작 목록 =========");
        List<Movie> list = movieService.getMovieList();
        for (int i = 0; i < list.size(); i++) {
            Movie m = list.get(i);
            System.out.println((i + 1) + ". " + m.getTitle()
                    + " | 상영시간: " + m.getStartTime()
                    + " | 상영관: " + m.getTheaterType());
        }
        System.out.println("====================================");
    }
}