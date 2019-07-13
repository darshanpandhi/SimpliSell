package com.simplisell.presentation.userprofileactivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;

public class ProfileInformation extends AppCompatActivity
{

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final String USERNAME_TEXT = "USER";

    private String userName;
    private EditText firstAndLastName;   // name of user
    private EditText password;          // password of user
    private TextView securityQuestion;  // security question of user
    private EditText securityAnswer;    // security answer of user
    private EditText phoneNumber;    // security answer of user
    private EditText email;
    private TextView photoTextView;
    private AccessUsers accessUsers;      // helps  access users


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        userName = getIntent().getStringExtra(USERNAME_TEXT);
        accessUsers = new AccessUsers();
        password = findViewById(R.id.profileEditBoxPassword);
        firstAndLastName = findViewById(R.id.profileEditBoxName6);
        securityQuestion = findViewById(R.id.profileEditBoxQuestion);
        securityAnswer = findViewById(R.id.profileEditBoxAnswer);
        phoneNumber = findViewById(R.id.profileEditBoxNumber);
        email = findViewById(R.id.profileEditBoxEmail);


        setUp();
    }


    private void setUp()
    {
        User currUser = accessUsers.getUser(userName);



        if (currUser.getFirstAndLastName() != null)
        {
            firstAndLastName.setHint(currUser.getFirstAndLastName());
        }

        if (currUser.getEmail() != null)
        {
            email.setHint(currUser.getEmail());
        }

        if (currUser.getPhoneNumber() != null)
        {
            phoneNumber.setHint(currUser.getPhoneNumber());
        }

        securityQuestion.setText(currUser.getSecurityQuestion());
        securityAnswer.setHint(currUser.getSecurityAnswer());
    }


    public void profileInformationSaveClick(View view)
    {

        User currUser = accessUsers.getUser(userName);

        String userName = currUser.getUserName();
        String userFullName = currUser.getFirstAndLastName();
        String userEmail = currUser.getEmail();
        String userPhoneNumber = currUser.getPhoneNumber();
        String userSecurityQuestion = currUser.getSecurityQuestion();
        String userSecurityAnswer = currUser.getSecurityAnswer();

        boolean userUpdatedProfile = false;

        if (!isEditTextEmpty(firstAndLastName))
        {
            userFullName = firstAndLastName.getText().toString();
            userUpdatedProfile = true;
        }

        if (!isEditTextEmpty(email))
        {
            userEmail = email.getText().toString();
            userUpdatedProfile = true;
        }

        if (!isEditTextEmpty(phoneNumber))
        {
            userPhoneNumber = phoneNumber.getText().toString();
            userUpdatedProfile = true;
        }

        if (!isEditTextEmpty(securityAnswer))
        {
            userSecurityAnswer = securityAnswer.getText().toString();
            userUpdatedProfile = true;
        }

        if (!isEditTextEmpty(password))
        {
            accessUsers.updatePassword(userName, password.getText().toString());
        }

        if (userUpdatedProfile)
        {
            accessUsers.updateProfileInformation(userName, userFullName, userEmail, userPhoneNumber,
                    userSecurityQuestion, userSecurityAnswer);
        }


        Toast.makeText(this, "PROFILE INFORMATION UPDATED", Toast.LENGTH_LONG).show();

        finish();
        Intent i = new Intent(getApplicationContext(), UserProfileMenu.class);
        i.putExtra(USERNAME_TEXT, userName);
        startActivity(i);
    }


    private boolean isEditTextEmpty(EditText userAttribute)
    {
        return (userAttribute.getText().toString()).isEmpty();
    }




    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go back
        finish();
        Intent i = new Intent(this, UserProfileMenu.class);
        i.putExtra(USERNAME_TEXT, userName);
        startActivity(i);
    }
}