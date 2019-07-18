package com.simplisell;

import com.simplisell.allAcceptanecTests.AdPostingFeatureAcceptanceTest;
import com.simplisell.allAcceptanecTests.AdminAndReportFeaturesAcceptanceTest;
import com.simplisell.allAcceptanecTests.LoginFeatureAcceptanceTest;
import com.simplisell.allAcceptanecTests.SearchFeatureAcceptanceTest;
import com.simplisell.allAcceptanecTests.UserProfileFeatureAcceptanceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            LoginFeatureAcceptanceTest.class,
            UserProfileFeatureAcceptanceTest.class,
            AdPostingFeatureAcceptanceTest.class,
            SearchFeatureAcceptanceTest.class,
            AdminAndReportFeaturesAcceptanceTest.class
    })

    public class AllAcceptanceTest
    {

    }


