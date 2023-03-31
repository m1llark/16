package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("g","d",(byte) 25);
        userService.saveUser("b","f",(byte) 30);
        userService.saveUser("c","g",(byte) 35);
        userService.saveUser("a", "h",(byte) 4);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();










//       UserServiceImpl userService = new UserServiceImpl();
//      userService.createUsersTable();
//       userService.saveUser("John", "Watson", (byte) 45);
//       userService.saveUser("Joe", "Moe", (byte) 23);
//       userService.saveUser("Tom", "Jerriev", (byte) 22);
//       userService.saveUser("lous", "Mous", (byte) 17);
//         userService.cleanUsersTable();
//       userService.saveUser("Cristiano", "Ronaldo", (byte) 37);
//        userService.saveUser("a", "Jerriev", (byte) 22);
//        userService.saveUser("b", "Mous", (byte) 17);
//        userService.getAllUsers();
//        userService.removeUserById(3);

       // userService.dropUsersTable();
    }
}
