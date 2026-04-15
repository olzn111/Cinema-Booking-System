package service;

import data.User;
import java.util.ArrayList;

public class UserService {
    private ArrayList<User> users;
    private User currentUser;

    public UserService() {
        users = new ArrayList<>();
        currentUser = null;
    }

    public boolean isIdDuplicated(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(String id, String password, String name,
                            int age, String ageDiscountType,
                            boolean membership, int telecom, String membershipGrade,
                            boolean soldier, boolean disabled) {

        if (isIdDuplicated(id)) {
            return false;
        }

        User newUser = new User(
                id, password, name,
                age, ageDiscountType,
                membership, telecom, membershipGrade,
                soldier, disabled
        );

        users.add(newUser);
        return true;
    }

    public boolean login(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}