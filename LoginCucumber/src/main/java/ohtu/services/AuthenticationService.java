package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // check username
        if (username.length() < 3 || !username.matches("[a-z]{3,}")) {
            return true;
        }
        // check password
        if(password.length() < 8 || !(password.matches("[a-zA-Z]*[0-9!@#%^*()_\\-=+\\[\\]{};:,.?/]+[a-zA-Z]*"))) {
            return true;
        }
        return false;
    }
}
