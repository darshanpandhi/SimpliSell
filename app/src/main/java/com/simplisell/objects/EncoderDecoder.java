package com.simplisell.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EncoderDecoder

{
    public static String bitMapToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }


    public static Bitmap stringToBitMap(String encodedString){

        Bitmap retValue = null;

        try
        {

            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            retValue = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        } catch(Exception e)
        {

            e.getMessage();
        }

        return retValue;
    }

}
