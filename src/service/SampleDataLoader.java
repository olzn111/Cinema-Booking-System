package service;
import data.Movie;
import java.util.ArrayList;
import java.util.List;

public class SampleDataLoader {
    private List<Movie> movies = new ArrayList<>();

    public void loadData() {
        movies.add(new Movie("프로젝트 헤일메리", "SF", "10:00", "IMAX"));
        movies.add(new Movie("살목지", "공포", "14:00", "Standard"));
        movies.add(new Movie("왕과 사는 남자", "사극", "19:00", "Premium"));
        movies.add(new Movie("귀멸의 칼날", "애니메이션", "23:00", "Standard"));
    }

    public List<Movie> getMovies() { return movies; }
}