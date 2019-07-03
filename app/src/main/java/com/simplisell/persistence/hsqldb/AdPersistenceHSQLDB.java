package com.simplisell.persistence.hsqldb;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdPersistenceHSQLDB implements AdPersistence
{
    private final String dbPath;

    public AdPersistenceHSQLDB(String dbPath)
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

    private Ad fromResultSet(final ResultSet rs) throws SQLException
    {
        final int adID = rs.getInt("ADID");
        final String adOwner = rs.getString("ADOWNER");
        final int adTypeNum = rs.getInt("ADTYPE");
        final AdType adType = AdType.values()[adTypeNum];
        final int categoryNum = rs.getInt("CATEGORY");
        final Category category = Category.values()[categoryNum];
        final String title = rs.getString("TITLE");
        final String description = rs.getString("DESCRIPTION");
        final double price = rs.getDouble("PRICE");
        final int numReports = rs.getInt("NUMREPORTS");
        return new Ad(adID, adOwner, adType, category, title, description, price, numReports);
    }

    @Override
    public List<Ad> getAds()
    {
        List<Ad> ads = new ArrayList<>();

        try (final Connection c = connection())
        {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ADS");
            while (rs.next())
            {
                final Ad ad = fromResultSet(rs);
                ads.add(ad);
            }
            rs.close();
            st.close();

            return ads;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Ad getAd(int adId)
    {
        Ad returnAd;

        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ADS WHERE ADID = ?");
            st.setString(1, String.valueOf(adId));
            final ResultSet rs = st.executeQuery();

            rs.next();
            returnAd = fromResultSet(rs);
            rs.close();
            st.close();

            return returnAd;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    public int getAdID()
    {
        try (final Connection c= connection())
        {
            final Statement stm = c.createStatement();
            final ResultSet rs = stm.executeQuery("SELECT ADID FROM ADS ORDER BY ADID DESC LIMIT 1");
            //Set new ad ID to value 1 greater than highest ad ID
            int newAdID = 0;
            if (rs.next())
            {
                newAdID = rs.getInt("ADID") + 1;
            }

            return newAdID;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Ad insertAd(final Ad ad)
    {
        try (final Connection c = connection())
        {
            int newAdID = getAdID();
            final PreparedStatement st = c.prepareStatement("INSERT INTO ADS VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, newAdID);
            st.setString(2, ad.getAdOwner());
            st.setInt(3, ad.getAdType().ordinal());
            st.setInt(4, ad.getCategory().ordinal());
            st.setString(5, ad.getTitle());
            st.setString(6, ad.getDescription());
            st.setDouble(7, ad.getPrice());
            st.setInt(8, ad.getNumReports());

            st.executeUpdate();
            st.close();

            return ad;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public Ad removeAd(final Ad ad)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM ADS WHERE ADID = ?");
            sc.setString(1, String.valueOf(ad.getAdId()));
            sc.executeUpdate();
            sc.close();
            return ad;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public final void reportAd(final int adID)
    {
        try (final Connection c = connection())
        {
            Ad reportedAd = getAd(adID);
            int numReports = reportedAd.getNumReports() + 1;
            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET NUMREPORTS = ? WHERE ADID = ?");
            st.setInt(1, numReports);
            st.setInt(2, adID);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public final void updateAd(Ad ad)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET CATEGORY = ?, TITLE = ?, DESCRIPTION = ?, PRICE = ? WHERE ADID = ?");
            st.setInt(1, ad.getCategory().ordinal());
            st.setString(2, ad.getTitle());
            st.setString(3, ad.getDescription());
            st.setDouble(4, ad.getPrice());
            st.setInt(5, ad.getAdId());

            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public final void repostAd(final int adID)
    {
        // Guys I need help here XD
        // Not sure how to reset expiry date within the database
    }
}
