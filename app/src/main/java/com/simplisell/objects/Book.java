package com.simplisell.objects;

final class Book extends Ad
{
    private final String bookName;


    private Book()
    {
        bookName = null;
    }


    private Book(String bookName)
    {
        this.bookName = bookName;
    }
}