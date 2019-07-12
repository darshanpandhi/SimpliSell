package com.simplisell.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.simplisell.R;

public class Messages extends Activity
{
    public static void warning(Activity owner, String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle(owner.getString(R.string.warning));
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}
