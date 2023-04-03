package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Joe", "Moe", (byte) 23);
        userService.saveUser("Tom", "Jerriev", (byte) 22);
        userService.saveUser("Cristiano", "Ronaldo", (byte) 37);
        userService.saveUser("a", "Jerriev", (byte) 22);
        userService.removeUserById(1);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
   }
}
