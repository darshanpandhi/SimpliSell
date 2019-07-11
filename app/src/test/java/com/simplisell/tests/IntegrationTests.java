package com.simplisell.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.business.AccessUsersIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessUsersIT.class
})
public class IntegrationTests {
}
