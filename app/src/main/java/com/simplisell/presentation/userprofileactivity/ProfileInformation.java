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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.EncoderDecoder;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

public class ProfileInformation extends AppCompatActivity
{

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final String USERNAME_TEXT = "USER";

    private ImageButton profileImage;
    private String userName;
    private EditText firstAndLastName;   // name of user
    private EditText password;          // password of user
    private EditText securityQuestion;  // security question of user
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

        userName = getIntent().getStringExtra(USERNAME_TEXT);
        accessUsers = new AccessUsers();
        profileImage = findViewById(R.id.profileImage);
        password = findViewById(R.id.profileEditBoxPassword);
        firstAndLastName = findViewById(R.id.profileEditBoxName6);
        securityQuestion = findViewById(R.id.profileEditBoxQuestion);
        securityAnswer = findViewById(R.id.profileEditBoxAnswer);
        phoneNumber = findViewById(R.id.profileEditBoxNumber);
        email = findViewById(R.id.profileEditBoxEmail);
        photoTextView = findViewById(R.id.textView);


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
        UserAdvertiser userAdvertiser = accessUsers.getUserAdvertiser(userName);

        if (userAdvertiser.getProfilePhoto() != null)
        {
            Bitmap photo = EncoderDecoder.stringToBitMap(userAdvertiser.getProfilePhoto());
            Bitmap displayProfile = Bitmap.createScaledBitmap(photo, (int) (photo.getWidth() * 2.8),
                    (int) (photo.getHeight() * 2.8), true);
            profileImage.setImageBitmap(displayProfile);
            photoTextView.setText("");
        }

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

        securityQuestion.setHint(userAdvertiser.getSecurityQuestion());
        securityAnswer.setHint(userAdvertiser.getSecurityAnswer());
    }


    public void profileInformationSaveClick(View view)
    {

        UserAdvertiser userAdvertiser = accessUsers.getUserAdvertiser(userName);

        String userName = userAdvertiser.getUserName();
        String userFullName = userAdvertiser.getFirstAndLastName();
        String userEmail = userAdvertiser.getEmail();
        String userPhoneNumber = userAdvertiser.getPhoneNumber();
        String userSecurityQuestion = userAdvertiser.getSecurityQuestion();
        String userSecurityAnswer = userAdvertiser.getSecurityAnswer();
        String userProfilePhoto = userAdvertiser.getProfilePhoto();

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

        if (!isEditTextEmpty(securityQuestion))
        {
            userSecurityQuestion = securityQuestion.getText().toString();
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
            Bitmap displayProfile = Bitmap.createScaledBitmap(photo, (int) (photo.getWidth() * 2.8),
                    (int) (photo.getHeight() * 2.8), true);
            profileImage.setImageBitmap(displayProfile);
            photoTextView.setText("");

            User currUser = accessUsers.getUser(userName);
            String userName = currUser.getUserName();

            accessUsers.updateProfileImage(userName, EncoderDecoder.bitMapToString(photo));
        }
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