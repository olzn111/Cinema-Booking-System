package service;

import data.Theater;

public class ReservationService {

    // MovieConsoleUi에서 넘겨주는 파라미터(상영관, 경로, 성인, 청소년, 유아, 할인금액)에 맞춘 최종 계산 로직
    public int calculatePrice(Theater theater, int senior, int adult, int teen, int toddler, int discount) {
        int basePrice = theater.getBasePrice(); // 상영관 기본 가격 (예: 15000원)

        int totalPrice = 0;

        // 1. 인원별 요금 합산
        totalPrice += adult * basePrice;
        totalPrice += teen * (int)(basePrice * 0.8);   // 청소년 20% 할인
        totalPrice += senior * (int)(basePrice * 0.5); // 경로 50% 할인
        // 유아(toddler)는 무료로 계산하여 더하지 않음

        // 2. 단일 할인 적용 (연령 할인 또는 멤버십 할인)
        totalPrice -= discount;

        // 3. 결제 금액이 마이너스가 되는 것을 방지 (최소 0원)
        return Math.max(totalPrice, 0);
    }
}