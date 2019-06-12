package com.simplisell.application;

import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.stubs.UserPersistenceStub;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.persistence.stubs.AdPersistenceStub;

public class Services
{

    private static UserPersistence userPersistence = null;
    private static AdPersistence adPersistence = null;

    public static synchronized UserPersistence getUserPersistence()
    {
        if (userPersistence == null)
        {
            userPersistence = new UserPersistenceStub();
        }

        return userPersistence;
    }

    public static synchronized AdPersistence getAdPersistence()
    {
        if (adPersistence == null)
        {
            adPersistence = new AdPersistenceStub();
        }

        return adPersistence;
    }

}
