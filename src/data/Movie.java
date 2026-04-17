package data;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String genre;
    private String startTime;
    private String theaterType;
    private List<String> bookedSeats;

    public Movie(String title, String genre, String startTime, String theaterType) {
        this.title = title;
        this.genre = genre;
        this.startTime = startTime;
        this.theaterType = theaterType;
        this.bookedSeats = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTheaterType() {
        return theaterType;
    }

    public List<String> getBookedSeats() {
        return new ArrayList<>(bookedSeats);
    }

    public boolean isSeatBooked(String seat) {
        return bookedSeats.contains(seat);
    }

    public boolean bookSeat(String seat) {
        if (bookedSeats.contains(seat)) {
            return false;
        }
        bookedSeats.add(seat);
        return true;
    }

    public void cancelSeat(String seat) {
        bookedSeats.remove(seat);
    }

    @Override
    public String toString() {
        return title + " / " + genre + " / " + startTime + " / " + theaterType;
    }
}