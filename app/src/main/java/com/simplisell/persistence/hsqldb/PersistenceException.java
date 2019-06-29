package com.simplisell.persistence.hsqldb;

/**
 * java.sql.SQLException is a checked exception, but our interface doesn't have any
 * checked exceptions, so wrap java.sql.SQLException in an unchecked java.lang.RuntimeException
 * so we can throw them around, but not *have* to catch them if we don't want to.
 */

//Taken from the sample project
public class PersistenceException extends RuntimeException
{
    public PersistenceException(final Exception cause) {
        super(cause);
    }
}