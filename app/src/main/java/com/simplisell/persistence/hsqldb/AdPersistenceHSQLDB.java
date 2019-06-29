package com.simplisell.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdPersistenceHSQLDB implements AdPersistence
{
    private final String dbPath;

    public AdPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    //    public Ad(final String adOwner, final AdType adType, final Category category, final
    //    String title, final String description, final double price)

    private Ad fromResultSet(final ResultSet rs) throws SQLException {
        final int adId = Integer.parseInt(rs.getString("adId"));
        final String adOwner = rs.getString("adOwner");
        final AdType adType = AdType.valueOf(rs.getString("adType"));
        final Category category = Category.valueOf(rs.getString("category"));
        final String title = rs.getString("title");
        final String description = rs.getString("description");
        final double price = Double.parseDouble(rs.getString("price"));
        return new Ad(adId, adOwner, adType, category, title, description, price);
    }

    @Override
    public List<Ad> getAds()
    {
        List ads = new ArrayList<>();

        try (final Connection c = connection())
        {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ads");
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

        return ads;
    }

    @Override
    public Ad getAd(int adId)
    {
        Ad returnAd;

        try (final Connection c = connection())
        {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ads WHERE adId=?");
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
            final PreparedStatement st = c.prepareStatement("INSERT INTO ads VALUES(?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, String.valueOf(ad.getAdId()));
            st.setString(2, ad.getAdOwner());
            st.setString(3, String.valueOf(ad.getAdType()));
            st.setString(4, String.valueOf(ad.getCategory()));
            st.setString(5, ad.getTitle());
            st.setString(6, ad.getDescription());
            st.setString(7, String.valueOf(ad.getPrice()));

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
            final PreparedStatement sc = c.prepareStatement("DELETE FROM ads WHERE adId = ?");
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

}
