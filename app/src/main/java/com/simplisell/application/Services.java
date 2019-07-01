package com.simplisell.application;

import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.hsqldb.UserPersistenceHSQLDB;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.persistence.hsqldb.AdPersistenceHSQLDB;

public class Services
{
    private static UserPersistence userPersistence = null;
    private static AdPersistence adPersistence = null;


    public static synchronized UserPersistence getUserPersistence()
    {
        if (userPersistence == null)
        {
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
        }

        return userPersistence;
    }


    public static synchronized AdPersistence getAdPersistence()
    {
        if (adPersistence == null)
        {
            adPersistence = new AdPersistenceHSQLDB();
        }

        return adPersistence;
    }
}
