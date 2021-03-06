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
import java.sql.Date;

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
        final Date expiryDate = rs.getDate("EXPIRYDATE");
        final int numreports = rs.getInt("NUMREPORTS");
        return new Ad(adID, adOwner, adType, category, title, description, price, expiryDate, numreports);
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


    @Override
    public Ad insertAd(final Ad ad)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("INSERT INTO ADS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, ad.getAdId());
            st.setString(2, ad.getAdOwner());
            st.setInt(3, ad.getAdType().ordinal());
            st.setInt(4, ad.getCategory().ordinal());
            st.setString(5, ad.getTitle());
            st.setString(6, ad.getDescription());
            st.setDouble(7, ad.getPrice());
            st.setDate(8, ad.getExpiryDate());
            st.setInt(9, ad.getNumReports());

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
    public List<Ad> getReportedAds()
    {
        List<Ad> reportedAdList = new ArrayList<>();
        List<Ad> allAdList = getAds();

        for (Ad currentAd : allAdList)
        {
            if (currentAd.getNumReports() > 0)
            {
                reportedAdList.add(currentAd);
            }
        }

        return reportedAdList;
    }


    @Override
    public final void updateAd(Ad ad)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET CATEGORY = ?, TITLE = ?, DESCRIPTION = ?,"
                    + " PRICE = ? WHERE ADID = ?");
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
        Ad ad = getAd(adID);

        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET EXPIRYDATE = ? WHERE ADID = ?");
            st.setDate(1, ad.calculateExpiryDate());
            st.setInt(2, ad.getAdId());

            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    @Override
    public final void changeExpiryDate(final int adId, Date newDate)
    {
        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET EXPIRYDATE = ? WHERE ADID = ?");
            st.setDate(1, newDate);
            st.setInt(2, adId);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    public final void reportAd(final int adID)
    {
        try (final Connection c = connection())
        {
            Ad ad = getAd(adID);
            ad.reportAd();

            final PreparedStatement st = c.prepareStatement("UPDATE ADS SET NUMREPORTS = ? WHERE ADID = ?");
            st.setInt(1, ad.getNumReports());
            st.setInt(2, adID);
            st.executeUpdate();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }


    public final int getNewAdId()
    {
        int currentAdId = findMaxId();

        return currentAdId + 1;
    }


    private int findMaxId()
    {
        int maxId = -1;
        int currAdId;

        List<Ad> adList = getAds();

        for (Ad ad : adList)
        {
            currAdId = ad.getAdId();

            if (currAdId > maxId)
            {
                maxId = currAdId;
            }
        }

        return maxId;
    }
}
