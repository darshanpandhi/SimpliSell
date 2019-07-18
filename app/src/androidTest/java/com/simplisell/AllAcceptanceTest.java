package com.simplisell;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            LoginFeatureAcceptanceTest.class,
            UserProfileFeatureAcceptanceTest.class,
            AdPostingFeatureAcceptanceTest.class,
            SearchFeatureAcceptanceTest.class
    })

    public class AllAcceptanceTest
    {

    }


