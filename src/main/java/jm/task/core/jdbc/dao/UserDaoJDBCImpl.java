package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT NOT NULL AUTO_INCREMENT,  name VARCHAR(255), lastName VARCHAR(255), age INT, PRIMARY KEY (id))");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void dropUsersTable() {
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) Values (?, ?, ?)";
        try  {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException  e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("User с именем – "+ name +" добавлен в базу данных" );
    }

    public void removeUserById(long id) {
        try  {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & preparedStatement != null) {
                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try  {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(userList);
        return userList;
    }
    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE users";
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null & statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



