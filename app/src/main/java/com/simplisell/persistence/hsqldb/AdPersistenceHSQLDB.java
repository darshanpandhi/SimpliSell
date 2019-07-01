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
    private List<Ad> ads;
    private final String dbPath;

    public AdPersistenceHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Ad fromResultSet(final ResultSet rs) throws SQLException {
        final int adID = rs.getInt("ADID");
        final String adOwner = rs.getString("ADOWNER");
        final int adTypeNum = rs.getInt("ADTYPE");
        final AdType adType = AdType.values()[adTypeNum];
        final int categoryNum = rs.getInt("CATEGORY");
        final Category category = Category.values()[categoryNum];
        final String title = rs.getString("TITLE");
        final String description = rs.getString("DESCRIPTION");
        final double price = rs.getDouble("PRICE");
        return new Ad(adID, adOwner, adType, category, title, description, price);
    }

    public List<Ad> getAds()
    {
        final List<Ad> courses = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ADS");
            while (rs.next())
            {
                final Ad course = fromResultSet(rs);
                courses.add(course);
            }
            rs.close();
            st.close();

            return courses;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    public final Ad getAd(int adId)
    {
        Ad ad = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ADS WHERE ADID = ?");
            st.setInt(1, adId);
            final ResultSet rs = st.executeQuery();
            if(rs.next()) {
                ad = fromResultSet(rs);
            }
            return ad;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }


    public final Ad insertAd(final Ad newAd)
    {
        Ad insertedAd = null;

        if (newAd != null)
        {
            final int newAdId = newAd.getAdId();

            // verify the presence of an ad with the same ad id
            if (getAd(newAdId) == null)
            {
                ads.add(newAd);
                insertedAd = newAd;
            }
        }

        return insertedAd;
    }


    public final Ad removeAd(final Ad adToBeRemoved)
    {
        Ad removedAd = null;
        final int index = ads.indexOf(adToBeRemoved);

        if (index >= 0)
        {
            ads.remove(adToBeRemoved);
            removedAd = adToBeRemoved;
        }

        return removedAd;
    }
}
