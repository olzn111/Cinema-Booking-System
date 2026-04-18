// 패키지 선언 및 라이브러리 불러오기
package service;

import data.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    // 사용자 ID 를 key, User 객체를 value로 저장하는 맵 - 값을 짝지어 저장한다.
    private Map<String, User> userMap; 
    // 로그인 사용자저장 변수
    private User currentUser;
    // 생성자 : 사용자 저장소 만들기
    public UserService() {
        userMap = new HashMap<>();
        currentUser = null;
    }
    // 회원가입 매서드
    public boolean register(String id, String pw, boolean isMembership, String telecomCompany, String membershipGrade) {
        if (id == null || pw == null || id.trim().isEmpty() || pw.trim().isEmpty()) {
            return false;
        } // 해당하는 값이 없다면 false
        // 같은 id 값이 존재한다면 false
        if (userMap.containsKey(id)) {
            return false;
        }
        //사용자에 새로운 객체 저장하고 맵에도 사용자 정보 저장
        User newUser = new User(id, pw, isMembership, telecomCompany, membershipGrade);
        userMap.put(id, newUser);
        return true;
    }
    // 로그인 에 대한 메서드
    public boolean login(String id, String pw) {
        User user = userMap.get(id); // 유저 맵 가져와서
        // 해당 아이디가 존재하는지 비밀번호랑 같은지 판단
        if (user != null && user.getPw().equals(pw)) {
            currentUser = user;
            return true;
        }
        return false;
    }
    // 로그아웃 매서드 현재 사용자 값 없애기
    public void logout() {
        currentUser = null;
    }
    // 현재 사용자 getter
    public User getCurrentUser() {
        return currentUser;
    }
    // 로그인 상태인지 확인하는 메서드
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}