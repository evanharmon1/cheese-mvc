package org.launchcode.models;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static ArrayList<User> users = new ArrayList<>();

    public static void add(User user) {
        users.add(user);
    }

    public static List<User> getAll() {
        return users;
    }

    public static User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
