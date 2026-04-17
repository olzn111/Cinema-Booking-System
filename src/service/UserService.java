package service;

import data.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap;
    private User currentUser;

    public UserService() {
        userMap = new HashMap<>();
        currentUser = null;
    }

    public boolean register(String id, String pw, boolean isMembership, String telecomCompany, String membershipGrade) {
        if (id == null || pw == null || id.trim().isEmpty() || pw.trim().isEmpty()) {
            return false;
        }

        if (userMap.containsKey(id)) {
            return false;
        }

        User newUser = new User(id, pw, isMembership, telecomCompany, membershipGrade);
        userMap.put(id, newUser);
        return true;
    }

    public boolean login(String id, String pw) {
        User user = userMap.get(id);

        if (user != null && user.getPw().equals(pw)) {
            currentUser = user;
            return true;
        }

        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}