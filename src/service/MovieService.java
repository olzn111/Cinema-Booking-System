// 패키지 선언 및 필요 라이브러리 불러오기
package service;

import data.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    // 영화 목록에 저장하는 리스트
    private List<Movie> movieList;
    // 객체 만들 때 리스트 초기화
    public MovieService() {
        this.movieList = new ArrayList<>();
    }
    // 영화를 리스트에 추가
    public void addMovie(Movie movie) {
        if (movie != null) {
            movieList.add(movie);
        }
    }
    // 영화 목록 반한 getter 
    public List<Movie> getMovieList() {
        return new ArrayList<>(movieList);
    }
    // 번호를 받아서 해당 영화 정보 반환 getter
    public Movie getMovieByNumber(int num) {
        if (num > 0 && num <= movieList.size()) {
            return movieList.get(num - 1);
        }
        return null;
    }
    // 영화 목록이 비었는지에 확인하는 메서드
    public boolean isEmpty() {
        return movieList.isEmpty();
    }
}