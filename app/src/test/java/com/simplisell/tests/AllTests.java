package com.simplisell.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.simplisell.tests.objects.ObjectTest;
import com.simplisell.tests.business.BusinessTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ObjectTest.class,
        BusinessTest.class,
})

public class AllTests
{

}
