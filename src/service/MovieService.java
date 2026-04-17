package service;

import data.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private List<Movie> movieList;

    public MovieService() {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        if (movie != null) {
            movieList.add(movie);
        }
    }

    public List<Movie> getMovieList() {
        return new ArrayList<>(movieList);
    }

    public Movie getMovieByNumber(int num) {
        if (num > 0 && num <= movieList.size()) {
            return movieList.get(num - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return movieList.isEmpty();
    }
}