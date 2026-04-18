package ui;

import data.Movie;
import java.util.List;

public class SeatUi {
    // standard 좌석 ui
    public void printStandard(Movie movie) {
        System.out.println("========== SCREEN (Standard) ==========");

        List<String> bookedSeats = movie.getBookedSeats();

        for (char row = 'A'; row <= 'F'; row++) {
            System.out.print(row + "  ");
            for (int col = 1; col <= 8; col++) {
                String seatName = row + String.valueOf(col);

                if (bookedSeats.contains(seatName)) {
                    System.out.print("[ X ] ");
                } else {
                    System.out.print("[" + seatName + "] ");
                }
            }
            System.out.println();
        }
    }
    // premium 좌석 ui
    public void printPremium(Movie movie) {
        System.out.println("========== SCREEN (Premium) ==========");

        List<String> bookedSeats = movie.getBookedSeats();

        System.out.print("P  ");
        for (int i = 1; i <= 12; i++) {
            String seatName = "P" + i;
            if (bookedSeats.contains(seatName)) {
                System.out.print("[ X ] ");
            } else {
                System.out.print("[" + seatName + "] ");
            }
        }
        System.out.println();

        System.out.println();

        System.out.print("N  ");
        for (int i = 1; i <= 12; i++) {
            String seatName = "N" + i;
            if (bookedSeats.contains(seatName)) {
                System.out.print("[ X ] ");
            } else {
                System.out.print("[" + seatName + "] ");
            }
        }
        System.out.println();
    }

}