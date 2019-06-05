package com.simplisell.objects;

import java.util.List;

public class UserAdvertiser extends User
{
    private List<String> postings;

    public UserAdvertiser(String userName, String password, List<String> userPostings)
    {
        super(userName, password);
        postings = userPostings;
    }

    public List<String> getPostings() {
        return postings;
    }

}
