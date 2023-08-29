package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE if NOT EXISTS User (ID bigint AUTO_INCREMENT PRIMARY KEY,  Name varchar(25), LastName varchar(30), Age tinyint (100))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if EXISTS User";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        String sql ="DELETE FROM USER WHERE ID =?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() throws SQLException {
        List <User> userList = new ArrayList<>();

        String sql = "SELECT * FROM USER";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User allUsers = new User();
                allUsers.setId(resultSet.getLong("ID"));
                allUsers.setName(resultSet.getString("NAME"));
                allUsers.setLastName(resultSet.getString("LASTNAME"));
                allUsers.setAge(resultSet.getByte("AGE"));

                userList.add(allUsers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return userList;
    }

    public void cleanUsersTable() {

        String sql ="DELETE FROM USER";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
