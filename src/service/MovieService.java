package service;

import data.Main;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private List<Main> movieList;

    public MovieService() {
        this.movieList = new ArrayList<>();
    }

    // 1. 영화 데이터 추가 (SampleDataLoader에서 호출할 예정)
    public void addMovie(Main movie) {
        movieList.add(movie);
    }

    // 2. 전체 영화 목록 가져오기 (UI에서 영화 목록 출력할 때 사용)
    public List<Main> getMovieList() {
        return movieList;
    }

    // 3. 번호로 특정 영화 선택하기 (사용자가 1번 영화를 고르면 인덱스 0 반환)
    public Main getMovieByNumber(int num) {
        // 사용자가 입력한 번호가 정상적인 범위 내에 있는지 확인
        if (num > 0 && num <= movieList.size()) {
            return movieList.get(num - 1);
        }
        return null; // 잘못된 번호 입력 시
    }
}