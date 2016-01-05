package com.example.jaas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

public class RestLoginDao {
    private static final Logger LOG = Logger.getLogger(RestLoginDao.class);
    public static final String USER_QUERY = "select user_name from users where user_name=? and user_pass=?";
    public static final String ROLE_QUERY = "select role_name from  user_roles where user_name=?";

    public boolean isValidUser(final String userName, final char[] passWord) throws LoginException {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(RestLoginDao.USER_QUERY);) {
            stmt.setString(1, userName);
            stmt.setString(2, new String(passWord));
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (final Exception e) {
            RestLoginDao.LOG.error("Error when loading user from the database " + e);
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getRoles(final String userName, final RestUserPrincipal user) {
        final List<String> roleList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(RestLoginDao.ROLE_QUERY);) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    roleList.add(rs.getString("role_name"));
                    user.addRole(new RestRolePrincipal(rs.getString("role_name")));
                }
            }
        } catch (final Exception e) {
            RestLoginDao.LOG.error("Error when loading user from the database " + e);
            e.printStackTrace();
        }
        return roleList;
    }

    private Connection getConnection() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_service_book", "root", "root");
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
