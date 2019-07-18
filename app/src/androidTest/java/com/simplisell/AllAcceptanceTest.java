package com.simplisell;

import com.simplisell.acceptancetests.AdPostingFeatureAcceptanceTest;
import com.simplisell.acceptancetests.AdminAndReportFeaturesAcceptanceTest;
import com.simplisell.acceptancetests.LoginFeatureAcceptanceTest;
import com.simplisell.acceptancetests.SearchFeatureAcceptanceTest;
import com.simplisell.acceptancetests.UserProfileFeatureAcceptanceTest;

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


