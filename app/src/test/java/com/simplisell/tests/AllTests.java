package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.business.AccessUsersTest;
import com.simplisell.tests.business.UserCredentialsTest;
import com.simplisell.tests.objects.AdTest;
import com.simplisell.tests.business.AccessAdsTest;
import com.simplisell.tests.objects.UserTest;
import com.simplisell.tests.business.ValidPasswordCheckerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        AdTest.class,
        UserCredentialsTest.class,
        AccessUsersTest.class,
        AccessAdsTest.class,
        ValidPasswordCheckerTest.class
})

public class AllTests
{

}
