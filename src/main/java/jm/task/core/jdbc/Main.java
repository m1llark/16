package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        userService.createUsersTable();
        userService.saveUser("John", "Watson", (byte) 45);
        userService.saveUser("Joe", "Moe", (byte) 23);
        userService.saveUser("Tom", "Jerriev", (byte) 22);
        userService.saveUser("lous", "Mous", (byte) 17);
        userService.cleanUsersTable();
        userService.saveUser("Cristiano", "Ronaldo", (byte) 37);
        userService.removeUserById(1);
        userService.getAllUsers();

    }
}
