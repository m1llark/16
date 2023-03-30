package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL , name VARCHAR(20) NOT NULL , lastName VARCHAR(20) NOT NULL , age TINYINT NOT NULL )";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            String sqlCommand = "DROP TABLE IF EXISTS users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users (name, lastName, age) Values (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM users WHERE Id =" + id);
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            String query = "SELECT * FROM users";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setAge(resultSet.getByte(4));
                    userList.add(user);
                }
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);
            String sqlCommand = "TRUNCATE TABLE users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sqlCommand);
            } catch (SQLException e) {
                connection.rollback();
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}



