package com.simplisell.application;

import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.stubs.UserPersistenceStub;

public class Services {
    public static UserPersistence userPersistence = null;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }

        return userPersistence;
    }
}
