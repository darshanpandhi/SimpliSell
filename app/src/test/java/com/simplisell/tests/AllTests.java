package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.objects.UserTest;
import com.simplisell.tests.business.BusinessTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        BusinessTest.class,
})

public class AllTests
{

}
