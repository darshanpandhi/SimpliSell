package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.business.AccessUsersTest;
import com.simplisell.tests.business.CredentialsTest;
import com.simplisell.tests.objects.UserTest;
import com.simplisell.tests.objects.AdTest;
import com.simplisell.tests.business.AccessAdsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        CredentialsTest.class,
        AdTest.class,
        AccessUsersTest.class,
        AccessAdsTest.class
})

public class AllTests
{

}
