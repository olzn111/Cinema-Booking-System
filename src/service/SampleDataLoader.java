package service;

import data.Movie;

public class SampleDataLoader {
    private MovieService movieService;

    public SampleDataLoader(MovieService movieService) {
        this.movieService = movieService;
    }

    public void loadData() {
        if (!movieService.isEmpty()) {
            return;
        }

        movieService.addMovie(new Movie("헤일메리", "SF", "08:00", "Standard"));
        movieService.addMovie(new Movie("헤일메리", "SF", "11:30", "Standard"));
        movieService.addMovie(new Movie("헤일메리", "SF", "15:00", "Premium"));
        movieService.addMovie(new Movie("헤일메리", "SF", "19:00", "Standard"));
        movieService.addMovie(new Movie("헤일메리", "SF", "23:00", "Premium"));

        movieService.addMovie(new Movie("살목지", "공포", "09:30", "Premium"));
        movieService.addMovie(new Movie("살목지", "공포", "13:00", "Standard"));
        movieService.addMovie(new Movie("살목지", "공포", "16:30", "Premium"));
        movieService.addMovie(new Movie("살목지", "공포", "20:00", "Standard"));
        movieService.addMovie(new Movie("살목지", "공포", "22:30", "Premium"));

        movieService.addMovie(new Movie("귀멸의 칼날", "애니메이션", "07:30", "Standard"));
        movieService.addMovie(new Movie("귀멸의 칼날", "애니메이션", "10:30", "Standard"));
        movieService.addMovie(new Movie("귀멸의 칼날", "애니메이션", "14:00", "Premium"));
        movieService.addMovie(new Movie("귀멸의 칼날", "애니메이션", "18:30", "Standard"));
        movieService.addMovie(new Movie("귀멸의 칼날", "애니메이션", "23:50", "Standard"));

        movieService.addMovie(new Movie("왕과 사는 남자", "드라마", "10:00", "Premium"));
        movieService.addMovie(new Movie("왕과 사는 남자", "드라마", "12:30", "Standard"));
        movieService.addMovie(new Movie("왕과 사는 남자", "드라마", "15:30", "Standard"));
        movieService.addMovie(new Movie("왕과 사는 남자", "드라마", "21:00", "Premium"));
        movieService.addMovie(new Movie("왕과 사는 남자", "드라마", "23:10", "Premium"));
    }
}