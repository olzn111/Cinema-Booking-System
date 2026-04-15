package service;

import data.Movie;

public class SampleDataLoader {

    private MovieService movieService;

    // 생성자를 통해 MovieService를 받아옴
    public SampleDataLoader(MovieService movieService) {
        this.movieService = movieService;
    }

    // 영화 데이터 세팅
    public void loadData() {
        // 1. 조조 영화 (09시) / IMAX
        movieService.addMovie(new Movie("프로젝트 헤일메리", "SF", "09:00", "IMAX"));

        // 2. 일반 영화 / Standard
        movieService.addMovie(new Movie("살목지", "공포", "14:00", "Couple"));

        // 3. 저녁 영화 / Premium
        movieService.addMovie(new Movie("왕과 사는 남자", "사극", "19:00", "Premium"));

        // 4. 심야 영화 (23시 30분) / Standard
        movieService.addMovie(new Movie("귀멸의 칼날: 무한성편", "애니메이션", "23:30", "Standard"));

        System.out.println("✅ 초기 영화 데이터 4건 로딩 완료!");
    }
}