package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.business.AccessUsersTest;
import com.simplisell.tests.objects.UserTest;
import com.simplisell.tests.objects.AdTest;
import com.simplisell.tests.business.ValidPasswordCheckerTest;
import com.simplisell.tests.business.SearchTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        ValidPasswordCheckerTest.class,
        AdTest.class,
        SearchTest.class,
        AccessUsersTest.class
})

public class AllTests
{

}
