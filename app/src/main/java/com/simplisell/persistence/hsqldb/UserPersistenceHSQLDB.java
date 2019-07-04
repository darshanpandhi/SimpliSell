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

    private Connection connection() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
        if (con != null) {
            System.out.println("Connection created successfully");
        }
        else {
            System.out.println("Problem with creating connection");
        }
        return con;
    }

    private User fromResultSet(final ResultSet rs) throws SQLException
    {
        final String firstAndLastName = rs.getString("FULLNAME");
        final String userName = rs.getString("USERNAME");
        final String password = rs.getString("PASSWORD");
        final String securityQuestion = rs.getString("SECURITYQUESTION");
        final String securityAnswer = rs.getString("SECURITYANSWER");
        final String email = rs.getString("EMAIL");
        final String phoneNumber = rs.getString("PHONENUMBER");
        final String profilePhoto = rs.getString("PROFILEPHOTO");
        return new User(firstAndLastName, userName, password, securityQuestion, securityAnswer, email, phoneNumber, profilePhoto);
    }

    @Override
    public User getUser(final String userName)
    {
        User user = null;

        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            st.setString(1, userName);
            final ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                user = fromResultSet(rs);
            }
            return user;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public User insertUser(final User user)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, user.getFirstAndLastName());
            st.setString(2, user.getUserName());
            st.setString(3, user.getPassword());
            st.setString(4, user.getSecurityQuestion());
            st.setString(5, user.getSecurityAnswer());
            st.setString(6, user.getEmail());
            st.setString(7, user.getPhoneNumber());
            st.setString(8, user.getProfilePhoto());
            st.executeUpdate();
            return user;

        } catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }

    }


    @Override
    public void updatePassword(final String userName, final String newPassword)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE users SET PASSWORD = ? WHERE USERNAME = ?");
            st.setString(1, newPassword );
            st.setString(2, userName);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void updateProfileInformation(String userName, String newFullName, String newEmail, String newPhoneNumber, String newSecurityQuestion, String newSecurityAnswer)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE users SET FULLNAME = ?, EMAIL = ?, PHONENUMBER = ?, SECURITYQUESTION = ?, SECURITYANSWER = ? WHERE USERNAME = ?");
            st.setString(1, newFullName);
            st.setString(2, newEmail);
            st.setString(3, newPhoneNumber);
            st.setString(4, newSecurityQuestion);
            st.setString(5, newSecurityAnswer);
            st.setString(6, userName);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updateProfileImage(String userName, String profilePhoto)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE users SET PROFILEPHOTO = ? WHERE USERNAME = ?");
            st.setString(1, profilePhoto );
            st.setString(2, userName);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
}
