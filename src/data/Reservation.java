package data;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Movie movie;
    private List<String> seats;
    private int totalPeople;
    private int finalPrice;

    public Reservation(Movie movie, List<String> seats, int totalPeople, int finalPrice) {
        this.movie = movie;
        this.seats = new ArrayList<>(seats);
        this.totalPeople = totalPeople;
        this.finalPrice = finalPrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<String> getSeats() {
        return new ArrayList<>(seats);
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    @Override
    public String toString() {
        String seatNames = String.join(", ", seats);
        return movie.getTitle() + " (" + movie.getStartTime() + ") - "
                + totalPeople + "명 / 좌석: [" + seatNames + "] / 결제금액: " + finalPrice + "원";
    }
}