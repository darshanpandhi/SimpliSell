package com.simplisell.persistence.hsqldb;

import com.simplisell.objects.User;
import com.simplisell.objects.UserAdmin;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.persistence.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPersistenceHSQLDB implements UserPersistence
{
    private static final String USER_ADVERTISER = "UserAdvertiser";
    private final String dbPath;


    public UserPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }


    private Connection connection() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
        if (con != null)
        {
            System.out.println("Connection created successfully");
        }
        else
        {
            System.out.println("Problem with creating connection");
        }
        return con;
    }


    private User fromResultSet(final ResultSet rs) throws SQLException
    {
        User requiredUser;

        final String userName = rs.getString("USERNAME");
        final String password = rs.getString("PASSWORD");
        final String securityQuestion = rs.getString("SECURITYQUESTION");
        final String securityAnswer = rs.getString("SECURITYANSWER");
        final String discriminatorColumn = rs.getString("DISCRIMINATOR");

        if (discriminatorColumn.equals(USER_ADVERTISER))
        {
            final String firstAndLastName = rs.getString("FULLNAME");
            final String email = rs.getString("EMAIL");
            final String phoneNumber = rs.getString("PHONENUMBER");
            final int numReports = rs.getInt("NUMREPORTS");

            requiredUser = new UserAdvertiser(firstAndLastName, userName, password, securityQuestion, securityAnswer,
                    email, phoneNumber, numReports);
        }
        else
        {
            requiredUser = new UserAdmin(userName, password, securityQuestion, securityAnswer);
        }

        return requiredUser;
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
            if (rs.next())
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
    public UserAdvertiser insertUserAdvertiser(final UserAdvertiser userAdvertiser)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, userAdvertiser.getFirstAndLastName());
            st.setString(2, userAdvertiser.getUserName());
            st.setString(3, userAdvertiser.getPassword());
            st.setString(4, userAdvertiser.getSecurityQuestion());
            st.setString(5, userAdvertiser.getSecurityAnswer());
            st.setString(6, userAdvertiser.getEmail());
            st.setString(7, userAdvertiser.getPhoneNumber());
            st.setInt(8, userAdvertiser.getNumReports());
            st.setString(9, USER_ADVERTISER);

            st.executeUpdate();
            return userAdvertiser;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void updatePassword(final String userName, final String newPassword)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?");
            st.setString(1, newPassword);
            st.setString(2, userName);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public void updateProfileInformation(String userName, String newFullName, String newEmail, String newPhoneNumber,
                                         String newSecurityQuestion, String newSecurityAnswer)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE USERS SET FULLNAME = ?, EMAIL = ?, " +
                    "PHONENUMBER = ?, SECURITYQUESTION = ?, SECURITYANSWER = ? WHERE USERNAME = ?");
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


    public final UserAdvertiser getUserAdvertiser(final String userName)
    {
        User requiredUser = getUser(userName);
        UserAdvertiser requiredUserAdvertiser = null;

        if (requiredUser instanceof UserAdvertiser)
        {
            requiredUserAdvertiser = (UserAdvertiser) requiredUser;
        }

        return requiredUserAdvertiser;
    }


    @Override
    public void deleteUser(String userName)
    {
        try (final Connection c = connection())
        {

            final PreparedStatement sc = c.prepareStatement("DELETE FROM USERS WHERE USERNAME = ?");
            sc.setString(1, userName);
            sc.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
}
