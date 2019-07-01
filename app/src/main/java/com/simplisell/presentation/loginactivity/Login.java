package com.simplisell.presentation.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

public class Login extends AppCompatActivity
{
    private EditText userNameEntered;             // the edit text box for userName of the user
    private EditText passwordEntered;          // the edit text box for password of the user

    private Credentials credentials;

    private static boolean isLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initializing the buttons and edit textboxes
        userNameEntered = findViewById(R.id.editText_login_enterUserName);
        passwordEntered = findViewById(R.id.editText_login_enterPassword);

        credentials = new Credentials();

        isLoggedIn = false;
    }


    public void loginBtnClick(View view)
    {
        String userName;       // The userName from the textbox will be stored here.
        String userPassword;    // The userName in the password will be stored here.

        ProgressDialog progressDialog;  // progress Dialogue

        // check if these fields are empty
        boolean userNameEmpty = userNameEntered.getText().toString().isEmpty();
        boolean passwordEmpty = passwordEntered.getText().toString().isEmpty();

        if (userNameEmpty || passwordEmpty)   // if userName or password field is empty
        {
            // Make a toast to display error message
            Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            userName = userNameEntered.getText().toString();
            userPassword = passwordEntered.getText().toString();

            // Show a progress Dialog while the authentication is loading
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging In");
            progressDialog.show();

            User loggedInUser = credentials.authenticate(userName, userPassword);

            if (loggedInUser != null)
            {  // if login is successful

                logIn();

                progressDialog.dismiss();   // dismiss the progress bar

                // show user that login was successful
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                finish();

                Intent logIn = new Intent(getApplicationContext(), loggedInUser.logInClass());
                logIn.putExtra(Intent.EXTRA_TEXT, userName);
                RecyclerViewAdapter.login(userName);
                startActivity(logIn);
            }
            else
            {
                // if authentication is not successful.
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void forgetPasswordBtnClick(View view)
    {
        Intent forgetPass = new Intent(Login.this, ForgetPassword.class);
        startActivity(forgetPass);
    }


    public void signUpBtnClick(View view)
    {
        // takes to sign in page
        Intent signUp = new Intent(Login.this, SignUp.class);
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

    private void logIn()
    {
        isLoggedIn = true;
    }

    public static void logOut()
    {
        isLoggedIn = false;
    }
}
