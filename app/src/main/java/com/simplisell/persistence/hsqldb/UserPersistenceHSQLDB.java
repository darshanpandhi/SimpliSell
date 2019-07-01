package com.simplisell.persistence.hsqldb;

import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceHSQLDB implements UserPersistence
{
    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        System.out.println("Path:" + dbPath);
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
        if (con != null) {
            System.out.println("Connection created successfully");
        }
        else {
            System.out.println("Problem with creating connection");
        }
        return con;
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {
        final String firstAndLastName = rs.getString("FULLNAME");
        final String userName = rs.getString("USERNAME");
        final String password = rs.getString("PASSWORD");
        final String securityQuestion = rs.getString("SECURITYQUESTION");
        final String securityAnswer = rs.getString("SECURITYANSWER");
        return new User(firstAndLastName, userName, password, securityQuestion, securityAnswer);
    }

    @Override
    public List<User> getUsers()
    {
        final List<User> users = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS");

            while (rs.next())
            {
                final User user = fromResultSet(rs);
                users.add(user);
            }
            rs.close();
            st.close();

            return users;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public User getUser(final String userName)
    {
        User user = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            if(rs.next()) {
                user = fromResultSet(rs);
            }
            return user;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }


    @Override
    public User insertUser(final User user)
    {
        try (final Connection c = connection()) {

            final PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, user.getFirstAndLastName());
            st.setString(2, user.getUserName());
            st.setString(3, user.getPassword());
            st.setString(4, user.getSecurityQuestion());
            st.setString(5, user.getSecurityAnswer());
            st.executeUpdate();
            return user;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }


    @Override
    public void updatePassword(final String userName, final String newPassword)
    {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE users SET password = ? WHERE userName = ?");
            st.setString(1, newPassword );
            st.setString(2, userName);
            st.executeUpdate();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

}
