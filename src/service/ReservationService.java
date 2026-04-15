package service;

import data.Movie;
import data.Reservation;
import data.Theater;
import data.User;

public class ReservationService {

    // 1. 최종 결제 금액 계산 알고리즘
    // User 객체에 getBirthYear(), getTelecom(), getPrivilege() 등이 있다고 가정합니다.
    public int calculatePrice(Theater theater, Movie movie, int birthYear, String telecom, String privilege, boolean isWednesday) {
        double price = theater.getBasePrice();

        // [나이 계산] 2026년 기준
        int age = 2026 - birthYear;

        // ① 시간대 할인 (조조: 10시 전 / 심야: 23시 이후)
        int hour = Integer.parseInt(movie.getStartTime().split(":")[0]);
        if (hour < 10) {
            price *= 0.8; // 조조 20% 할인
        } else if (hour >= 23) {
            price *= 0.9; // 심야 10% 할인
        }

        // ② 연령 할인
        if (age <= 4) {
            price = 0; // 유아 무료 (48개월 이하)
        } else if (age <= 18) {
            price *= 0.7; // 청소년 30% 할인
        } else if (age >= 65) {
            price *= 0.5; // 경로 우대 50% 할인
        }

        // ③ 우대 할인 (군인, 장애인) - 2000원 정액 할인
        if (privilege.equals("군인") || privilege.equals("장애인")) {
            price -= 2000;
        }

        // ④ 문화의 날 (매주 수요일) - 1000원 추가 할인
        if (isWednesday) {
            price -= 1000;
        }

        // ⑤ 통신사 VIP 할인
        if (telecom.contains("VIP")) {
            price -= 1000;
        }

        // 금액이 0원 밑으로 떨어지는 것 방지
        return Math.max((int) price, 0);
    }

    // 2. 예매 내역 생성 (최종 영수증 만들기)
    public Reservation makeReservation(String userId, Movie movie, Theater theater, String seatNum, int finalPrice) {
        // 실제 좌석 예약 처리
        // 만약 seatNum이 "A1"이라면 행렬 인덱스로 변환하는 로직이 필요하지만, 여기서는 내역만 생성
        return new Reservation(userId, movie.getTitle(), seatNum, finalPrice);
    }
}
