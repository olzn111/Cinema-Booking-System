package service;

import data.Movie;
import data.Reservation;
import data.Theater;
import java.util.List;

public class ReservationService {

    public int calculateTotalPeople(int senior, int adult, int teen, int toddler) {
        if (senior < 0 || adult < 0 || teen < 0 || toddler < 0) {
            return -1;
        }
        return senior + adult + teen + toddler;
    }

    public boolean isMorningMovie(Movie movie) {
        int hour = Integer.parseInt(movie.getStartTime().split(":")[0]);
        return hour <= 10;
    }

    public boolean isLateNightMovie(Movie movie) {
        int hour = Integer.parseInt(movie.getStartTime().split(":")[0]);
        return hour >= 22;
    }

    public int getTimeDiscountPerPerson(Movie movie) {
        if (isMorningMovie(movie)) {
            return 2000;
        } else if (isLateNightMovie(movie)) {
            return 1000;
        }
        return 0;
    }

    public String getTimeDiscountLabel(Movie movie) {
        if (isMorningMovie(movie)) {
            return "조조 할인";
        } else if (isLateNightMovie(movie)) {
            return "심야 할인";
        }
        return "해당 없음";
    }

    public int calculatePrice(Movie movie, Theater theater, int senior, int adult, int teen, int toddler, int membershipDiscount) {
        if (movie == null || theater == null) {
            return -1;
        }

        if (senior < 0 || adult < 0 || teen < 0 || toddler < 0 || membershipDiscount < 0) {
            return -1;
        }

        int basePrice = theater.getBasePrice();
        int timeDiscount = getTimeDiscountPerPerson(movie);

        int adultPrice = Math.max(basePrice - timeDiscount, 0);
        int teenPrice = Math.max(basePrice - 3000 - timeDiscount, 0);
        int seniorPrice = Math.max(basePrice - 5000 - timeDiscount, 0);
        int toddlerPrice = 0; // 유아(7세 이하)는 무료

        int totalPrice = 0;
        totalPrice += adult * adultPrice;
        totalPrice += teen * teenPrice;
        totalPrice += senior * seniorPrice;
        totalPrice += toddler * toddlerPrice;

        // 멤버십 할인: 본인 1표만 추가 적용
        totalPrice -= membershipDiscount;

        return Math.max(totalPrice, 0);
    }

    public Reservation createReservation(Movie movie, List<String> seats, int totalPeople, int finalPrice) {
        if (movie == null || seats == null || seats.isEmpty() || totalPeople <= 0 || finalPrice < 0) {
            return null;
        }

        return new Reservation(movie, seats, totalPeople, finalPrice);
    }
}