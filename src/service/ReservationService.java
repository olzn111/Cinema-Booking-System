// 패키지 선언 및 라이브러리 불러오기
package service;
import data.Movie;
import data.Reservation;
import data.Theater;
import java.util.List;

public class ReservationService {
    // 사람 총 수 계산 하는 메서드
    public int calculateTotalPeople(int senior, int adult, int teen, int toddler) {
        if (senior<0||adult<0 ||teen<0 ||toddler<0) { // 총 수가 0명이라면 
            return -1;
        }
        return senior + adult + teen + toddler; // 총 사람 수 반환
    }
    // 조조 영화인지 확인매서드 - 영화 시작 시작이 오 전 10시 이전인지 판단
    public boolean isMorningMovie(Movie movie) {
        int hour = Integer.parseInt(movie.getStartTime().split(":")[0]);
        return hour<=10;
    }
    // 심야 영화인지 판단하는 매서드 - 영화 시작 시간 오후 10시 이후인지 판단
    public boolean isLateNightMovie(Movie movie) {
        int hour = Integer.parseInt(movie.getStartTime().split(":")[0]);
        return hour >= 22;
    }
    //영화시간에 따른 영화 할인 가격 반환 메서드 
    public int getTimeDiscountPerPerson(Movie movie) {
        if (isMorningMovie(movie)) { return 2000; }  // 조조라면 2000원 할인
        else if (isLateNightMovie(movie)) { return 1000; } // 심야라면1000원 할인
        return 0;
    }
    // 해당 영화가 시간 할인에 해당하는 영화인지 문자열로 반환하는 매서드
    public String getTimeDiscountLabel(Movie movie) {
        if (isMorningMovie(movie)) { return "조조 할인"; }
        else if (isLateNightMovie(movie)) { return "심야 할인"; }
        return "해당 없음";
    }
    //영화 가격 최종으로 계산하는 메서드
    public int calculatePrice(Movie movie, Theater theater, int senior, int adult, int teen, int toddler, int membershipDiscount) {
        // 영화 없거나 인원 수 없으면 오류 
        if (movie == null || theater == null) { return -1; }
        if (senior<0 ||adult< 0||teen < 0 ||toddler < 0||membershipDiscount < 0) { return -1; }
        // 기본 가격, 시간 할인에 대한 변수 선언
        int basePrice = theater.getBasePrice();
        int timeDiscount = getTimeDiscountPerPerson(movie);
        // 가격 기본가격에서 할인가격 계산하고, 0원보다 작으면 0원으로 반환
        int adultPrice = Math.max(basePrice - timeDiscount, 0);
        int teenPrice = Math.max(basePrice - 3000 - timeDiscount, 0);
        int seniorPrice = Math.max(basePrice - 5000 - timeDiscount, 0);
        int toddlerPrice = 0; // 유아(7세 이하)는 무료
        //총 가격에 인원수 * 가격 더하기
        int totalPrice = 0;
        totalPrice = adult * adultPrice + teen * teenPrice + senior * seniorPrice + toddler * toddlerPrice;
        // 멤버십 할인: 본인 1표만 추가 적용
        totalPrice -= membershipDiscount;
        return Math.max(totalPrice, 0); // 총가격이 0보다 적으면 0원이상 반환
    }
    // reservation 객체를 만들어주는 기능 (영화, 좌석, 총 사람 수 , 촤종 가격을 저장함)
    public Reservation createReservation(Movie movie, List<String> seats, int totalPeople, int finalPrice) {
        if (movie ==null||seats== null|| seats.isEmpty()||totalPeople <= 0 || finalPrice < 0) { return null; }
        return new Reservation(movie, seats, totalPeople, finalPrice); 
    } 
}