package com.simplisell.persistence.hsqldb;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdPersistenceHSQLDB implements AdPersistence
{
    private List<Ad> ads;
//
//    public AdPersistenceHSQLDB(String dbPath) {
//        this.dbPath = dbPath;
//    }
//
//    private Connection connectizon() throws SQLException {
//        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
//    }


//    private Ad fromResultSet(final ResultSet rs) throws SQLException {
//        final String adOwner = rs.getString("adOwner");
//        final String adTypeTitle = rs.getString("adType");
//        final AdType adType = AdType.valueOf(AdType.class, adTypeTitle);
//        final String categoryTitle = rs.getString("category");
//        final Category category = Category.valueOf(Category.class, categoryTitle);
//        final String title = rs.getString("title");
//        final String description = rs.getString("description");
//        final double price = rs.getDouble("price");
//        return new Ad(adOwner, adType, category, title, description, price);
//    }

    public AdPersistenceHSQLDB()
    {
        ads = new ArrayList<>();

        String adOwner = "Bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.ELECTRONICS;
        String title = "iPad 6th Gen";
        String description = "iPad Ad description";
        double price = 554.99;

        Ad newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);

        adOwner = "Allice";
        adType = AdType.WANT;
        category = Category.JOBS_SERVICES;
        title = "Wanted Tutoring Services";
        description = "Tutoring Services Description";
        price = 40;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);

        adOwner = "Jay";
        adType = AdType.OFFERING;
        category = Category.TRANSPORTATION;
        title = "CarPooling Ad Title";
        description = "CarPooling Ad Description";
        price = 100;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);


        adOwner = "Bob";
        adType = AdType.OFFERING;
        category = Category.BOOKS;
        title = "Transcendence- A spiritual journey with pramukh swami maharaj";
        description = "A nice book by Dr APJ abdul kalaam";
        price = 0;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);


        adOwner = "Allice";
        adType = AdType.OFFERING;
        category = Category.BOOKS;
        title = "Analysis of Algorithms";
        description = "Book for COMP2080 and COMP3170";
        price = 20;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);
    }

    public List<Ad> getAds()
    {
        return ads;
    }

//    public List<Ad> getAdsDB()
//    {
//        final List<Ad> courses = new ArrayList<>();
//
//        try (final Connection c = connection()) {
//            final Statement st = c.createStatement();
//            final ResultSet rs = st.executeQuery("SELECT * FROM courses");
//            while (rs.next())
//            {
//                final Ad course = fromResultSet(rs);
//                courses.add(course);
//            }
//            rs.close();
//            st.close();
//
//            return courses;
//        }
//        catch (final SQLException e)
//        {
//            throw new PersistenceException(e);
//        }
//    }

    public final Ad getAd(int adId)
    {
        Ad currentAd;
        Ad requiredAd = null;
        boolean adFound = false;
        int currentIndex = 0;

        while (!adFound && currentIndex < ads.size())
        {
            currentAd = ads.get(currentIndex);

            if (currentAd.getAdId() == adId)
            {
                requiredAd = currentAd;
                adFound = true;
            }

            currentIndex++;
        }

        return requiredAd;
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
