package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Statement statement = null;
        try (Connection connection = getConnection()) {
            statement = connection.createStatement();
            int sql = statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR (20), LASTNAME VARCHAR(20), AGE SMALLINT)");
            System.out.println("Table is created");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Not created");
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String sqlDrop = "DROP TABLE IF EXISTS Users";
            statement.executeUpdate(sqlDrop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users ( NAME , LASTNAME, AGE) VALUES ( ?, ?, ?)";


        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + " с именем -" + name + " добавлен в базу.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public void removeUserById(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    public List<User> getAllUsers() {

        List<User> usersList = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM Users";

        try {
            connection = getConnection();
            statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(Byte.valueOf(resultSet.getString("AGE")));

                usersList.add(user);

            }
        } catch (SQLException e) {
            System.out.println(usersList);
            e.printStackTrace();

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (User list : usersList) {
            System.out.println(list.toString());
        }

        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            String sql = "DELETE FROM Users";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
