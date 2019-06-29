package com.simplisell.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceHSQLDB implements UserPersistence
{
    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException
    {
        final String userName = rs.getString("username");
        final String userPassword = rs.getString("password");
        final String userSQ = rs.getString("securityQ");
        final String userSA = rs.getString("securityA");

        return new User(userName, userPassword, userSQ, userSA);
    }

    public List<User> getUsers()
    {
        List<User> userList = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                final User user = fromResultSet(rs);
                userList.add(user);
            }
            rs.close();
            st.close();

            return userList;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public User getUser(String userName)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM users WHERE username=?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            rs.next();
            final User user = fromResultSet(rs);
            st.close();
            rs.close();
            return user;

        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public String getPassword(String userName)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT password FROM users WHERE username=?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            rs.next();
            final String password = rs.getString("password");
            st.close();
            rs.close();
            return password;

        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public String getSecurityQuestion(String userName)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT password FROM users WHERE username=?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            rs.next();
            final String securityQ = rs.getString("securityQ");
            st.close();
            rs.close();
            return securityQ;

        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public String getSecurityAnswer(String userName)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT password FROM users WHERE username=?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            rs.next();
            final String securityA = rs.getString("securityA");
            st.close();
            rs.close();
            return securityA;

        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public User insertUser(User user)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?)");
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getSecurityQuestion());
            st.setString(4, user.getSecurityAnswer());
            st.executeUpdate();
            return user;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public User updatePassword(String userName, String newPassword)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            st.setString(1, newPassword);
            st.setString(2, userName);
            st.executeUpdate();
            return getUser(userName);
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

}
