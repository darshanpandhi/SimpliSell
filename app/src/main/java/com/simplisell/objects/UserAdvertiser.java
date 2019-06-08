package com.simplisell.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User implements Serializable
{

    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
    }

}
