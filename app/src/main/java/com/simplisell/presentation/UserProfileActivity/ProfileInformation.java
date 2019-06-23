package com.simplisell.presentation.UserProfileActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.EncoderDecoder;
import com.simplisell.objects.User;

public class ProfileInformation extends AppCompatActivity
{

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final String USERNAME_TEXT="USER";


    private ImageButton profileImage;
    private Button saveButton;
    private String userName;
    private EditText firstAndLastName;              // name of user
    private EditText password;          // password of user
    private EditText securityQuestion;  // security question of user
    private EditText securityAnswer;    // security answer of user
    private EditText phoneNumber;    // security answer of user
    private EditText email;
    private AccessUsers accessUsers;      // helps  access users



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);
        userName = getIntent().getStringExtra(USERNAME_TEXT);
        accessUsers = new AccessUsers();


        profileImage = findViewById(R.id.profileImage);
        password = findViewById(R.id.profileEditBoxPassword);
        firstAndLastName = findViewById(R.id.profileEditBoxName6);
        securityQuestion = findViewById(R.id.profileEditBoxQuestion);
        securityAnswer = findViewById(R.id.profileEditBoxAnswer);
        phoneNumber = findViewById(R.id.profileEditBoxNumber);
        email = findViewById(R.id.profileEditBoxEmail);


        setUp();



        profileImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }


    private void setUp()
    {
        User currUser = accessUsers.getUser(userName);

        if(currUser.getProfilePhoto()!=null)
        {
            profileImage.setImageBitmap(EncoderDecoder.stringToBitMap(currUser.getProfilePhoto()));
        }

        if(currUser.getFirstAndLastName()!=null)
        {
            firstAndLastName.setHint(currUser.getFirstAndLastName());
        }

        if(currUser.getEmail()!=null)
        {
            email.setHint(currUser.getEmail());
        }

        if(currUser.getPhoneNumber()!=null)
        {
            phoneNumber.setHint(currUser.getPhoneNumber());

        }


        securityQuestion.setHint(currUser.getSecurityQuestion());
        securityAnswer.setHint(currUser.getSecurityAnswer());


    }



    public void profileInformationSaveClick(View view)
    {

        User currUser = accessUsers.getUser(userName);


        if(!(firstAndLastName.getText().toString()).isEmpty())
        {
            currUser.setFirstAndLastName(firstAndLastName.getText().toString());
        }


        if(!(email.getText().toString()).isEmpty())
        {
            currUser.setEmail(email.getText().toString());
        }

        if(!(phoneNumber.getText().toString()).isEmpty())
        {
            currUser.setPhoneNumber(phoneNumber.getText().toString());
        }


        if(!(securityQuestion.getText().toString()).isEmpty())
        {
            currUser.setSecurityQuestion(securityQuestion.getText().toString());
        }

        if(!(securityAnswer.getText().toString()).isEmpty())
        {
            currUser.setSecurityAnswer(securityAnswer.getText().toString());
        }

        if(!(password.getText().toString()).isEmpty())
        {
            currUser.setPassword(password.getText().toString());
        }

    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "Camera permission denied!", Toast.LENGTH_LONG).show();
            }
        }


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profileImage.setImageBitmap(photo);

            User currUser = accessUsers.getUser(userName);

            currUser.setProfilePhoto(EncoderDecoder.bitMapToString(photo));
        }
    }

    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go back

        fileList();
        Intent i = new Intent(this,UserProfileMenu.class);
        startActivity(i);
    }


}