package com.simplisell.presentation.userprofileactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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

    private ImageButton profileImage;
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
        User userAdvertiser = accessUsers.getUser(userName);

        if (userAdvertiser.getFirstAndLastName() != null)
        {
            firstAndLastName.setHint(userAdvertiser.getFirstAndLastName());
        }

        if (userAdvertiser.getEmail() != null)
        {
            email.setHint(userAdvertiser.getEmail());
        }

        if (userAdvertiser.getPhoneNumber() != null)
        {
            phoneNumber.setHint(userAdvertiser.getPhoneNumber());
        }

        securityQuestion.setText(userAdvertiser.getSecurityQuestion());
        securityAnswer.setHint(userAdvertiser.getSecurityAnswer());
    }


    public void profileInformationSaveClick(View view)
    {

        User userAdvertiser = accessUsers.getUser(userName);

        String userName = userAdvertiser.getUserName();
        String userFullName = userAdvertiser.getFirstAndLastName();
        String userEmail = userAdvertiser.getEmail();
        String userPhoneNumber = userAdvertiser.getPhoneNumber();
        String userSecurityQuestion = userAdvertiser.getSecurityQuestion();
        String userSecurityAnswer = userAdvertiser.getSecurityAnswer();

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