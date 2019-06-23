package com.simplisell.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class EncoderDecoder

{
    public static String bitMapToString(Bitmap bitmap)
    {
        String temp=null;

        if(bitmap==null)
        {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);
        }

        return temp;
    }


    public static Bitmap stringToBitMap(String encodedString){

        Bitmap retValue =null;

        try
        {

            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        } catch(Exception e)
        {

            e.getMessage();
        }

        return retValue;
    }

}
