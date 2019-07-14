package com.simplisell.presentation.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.UserCredentials;
import com.simplisell.objects.User;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

public class Login extends AppCompatActivity
{
    private final String USERNAME_TEXT = "USER";

    private EditText userNameEntered;             // the edit text box for userName of the user
    private EditText passwordEntered;          // the edit text box for password of the user

    private UserCredentials userCredentials;

    private static boolean isLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // initializing the buttons and edit textboxes
        userNameEntered = findViewById(R.id.editText_login_enterUserName);
        passwordEntered = findViewById(R.id.editText_login_enterPassword);

        userCredentials = new UserCredentials();

        isLoggedIn = false;
    }


    public void loginBtnClick(View view)
    {
        String userName;       // The userName from the textbox will be stored here.
        String userPassword;    // The userName in the password will be stored here.


        userName = userNameEntered.getText().toString();
        userPassword = passwordEntered.getText().toString();

        User loggedInUser = userCredentials.correctPassword(userName, userPassword);

        if (loggedInUser != null)
        {
            // if login is successful
            // show user that login was successful
            Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

            finish();

            logIn(loggedInUser);
        }
        else
            {
                // if authentication is not successful.
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
    }

    private void logIn(User loggedInUser)
    {
        isLoggedIn = true;

        String userName = loggedInUser.getUserName();

        Intent logIn;

        logIn = new Intent(getApplicationContext(), MainActivity.class);
        logIn.putExtra(USERNAME_TEXT, userName);
        RecyclerViewAdapter.login(userName);
        finish();
        startActivity(logIn);
    }


    public void forgetPasswordBtnClick(View view)
    {
        Intent forgetPass = new Intent(getApplicationContext(), ForgetPassword.class);
        startActivity(forgetPass);
    }


    public void signUpBtnClick(View view)
    {
        // takes to sign in page
        Intent signUp = new Intent(getApplicationContext(), SignUp.class);
        startActivity(signUp);
    }


    @Override
    public void onBackPressed()
    {
        finish();
        Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginPage);
    }

    public static boolean isLoggedIn()
    {
        return isLoggedIn;
    }

    public static void logOut()
    {
        isLoggedIn = false;
    }
}
