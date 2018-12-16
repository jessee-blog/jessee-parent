package com.jessee;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jessee on 2018/12/16.
 */
public class JDBCDemo {

    @Test
    public void test() {
        User user = new User();
        user.setAge(21);
        user.setName("张三");
        try {
            int num = insert(user);
            System.out.println(num);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123456");
            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("INSERT INTO `test`.`t_user` (`age`, `name`) VALUES (?, ?)");
            /*if (null != user.getAge()) {
                preparedStatement.setInt(1, user.getAge());
            } else {
                preparedStatement.setNull(1, INTEGER);
            }*/
            preparedStatement.setInt(1, user.getAge());
            preparedStatement.setString(2, user.getName());
            //connection.commit();
            return preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
