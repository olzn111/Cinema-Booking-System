package service;

import data.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    // 유저 정보를 저장할 저장소 (아이디를 키값으로 사용)
    private Map<String, User> userMap = new HashMap<>();
    // 현재 로그인한 유저 정보를 기억하는 변수
    private User currentUser = null;

    // 회원가입 처리 (UI에서 넘겨준 3가지 정보를 받아 User 객체를 만듦)
    public void register(String id, String pw, boolean isMembership) {
        User newUser = new User(id, pw, isMembership);
        userMap.put(id, newUser);
    }

    // 로그인 처리
    public boolean login(String id, String pw) {
        User user = userMap.get(id);
        if (user != null && user.getPw().equals(pw)) {
            currentUser = user; // 로그인 성공 시 현재 유저로 등록
            return true;
        }
        return false; // 실패 시
    }

    // 로그아웃 처리
    public void logout() {
        currentUser = null;
    }

    // 현재 로그인한 유저 정보 넘겨주기
    public User getCurrentUser() {
        return currentUser;
    }
}