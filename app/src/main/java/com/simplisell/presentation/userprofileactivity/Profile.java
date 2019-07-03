package com.simplisell.presentation.userprofileactivity;

import android.widget.EditText;

abstract class Profile
{
    boolean isEmpty(EditText userAttribute)
    {
        return (userAttribute.getText().toString()).isEmpty();
    }
}
