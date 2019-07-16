package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.business.AccessAdsIT;
import com.simplisell.tests.business.AccessUsersIT;
import com.simplisell.tests.business.UserCredentialsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessUsersIT.class,
        AccessAdsIT.class,
        UserCredentialsTest.class
})
public class IntegrationTests {
}
