package ui;

import data.Movie;
import java.util.List;

public class SeatUi {

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

    public void printCouple(Movie movie) {
        System.out.println("========== SCREEN (Couple) ==========");

        List<String> bookedSeats = movie.getBookedSeats();

        for (char row = 'A'; row <= 'D'; row++) {
            System.out.print(row + "  ");
            for (int col = 1; col <= 7; col += 2) {
                String seat1 = row + String.valueOf(col);
                String seat2 = row + String.valueOf(col + 1);

                if (bookedSeats.contains(seat1) || bookedSeats.contains(seat2)) {
                    System.out.print("[ X ]   ");
                } else {
                    System.out.print("[" + seat1 + "-" + seat2 + "]   ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }
}