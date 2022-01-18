package project.bfour.debtormaintenance.dao;

import project.bfour.debtormaintenance.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;

public class UserDao {
//    Register
    public int register (User user) throws SQLException {
        int status = 0;
        Connection c = MySqlConn.getCon();
        PreparedStatement ps = c.prepareStatement("select * from user where username=?");
        ps.setString(1, user.getUsername());
        ResultSet resultSet = ps.executeQuery();

//        Checking User for the existence
        while (resultSet.next()) {
            if (user.getUsername().equals(resultSet.getString(1))) {
//                User already Registered
                return status;
            }
        }

        PreparedStatement pst = c.prepareStatement("insert into user(username, password, uid) values (?,?,?)");
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        pst.setString(3, getNewDebId(c));
        status = pst.executeUpdate();
        return status;
    }

//    Login
    public int validate (User user, String who) throws SQLException {
        Connection c = MySqlConn.getCon();
        PreparedStatement ps;
        if (who.equals("deb")) {
            ps = c.prepareStatement("select * from user where username=? and uid like 'deb-%'");
        } else {
            ps = c.prepareStatement("select * from user where username=? and uid like 'adm-%'");
        }
        ps.setString(1, user.getUsername());
        ResultSet resultSet = ps.executeQuery();
        int status = 0;
        if (resultSet.next()) {
            if (resultSet.getString("username").equals(user.getUsername()) &&
                    resultSet.getString("password").equals(user.getPassword())) {
//                sout(resultSet.getString("username") + " "
//                        + resultSet.getString("password"));
//
//                sout(user.getUsername() + " " + user.getPassword());
//                User Exists"
                status = 1;
            }

//            Checking for incorrect password
            else if (resultSet.getString(1).equals(user.getUsername()) &&
                    !resultSet.getString(2).equals(user.getPassword())) {
//                sout(resultSet.getString(1) + " "
//                        + resultSet.getString(2));
//                sout(user.getUsername() + " " + user.getPassword());

//                PassWord Incorrect
                status = 10;
            }
        }
        return status;
    }

    private static String getNewDebId (Connection connection) throws SQLException {
        String uid;
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        String checkQuery = "select count(*) as count from user where uid like \"deb-%\"";
        resultSet = statement.executeQuery(checkQuery);
        resultSet.next();
        if (resultSet.getInt("count") == 0) {
            return "deb-100";
        }
        String query = "select max(uid) as max from user where uid like \"deb-%\"";
        resultSet = statement.executeQuery(query);
        resultSet.next();
        uid = resultSet.getString("max");
        uid = uid.replace("deb-", "");
        int id = Integer.parseInt(uid);
        return "deb-" + (++id);
    }

//    returns uid->who
    public String getUid(Connection connection, String username) throws SQLException {
        String query = "select uid from user where username=\"" + username + "\"";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getString("uid");

    }

//    applies 256-bit encryption
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}