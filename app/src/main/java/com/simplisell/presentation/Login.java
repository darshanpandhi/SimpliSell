package com.simplisell.presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;


public class Login extends AppCompatActivity
{

    private final String USERNAME_TEXT="USER";


    private EditText userName;             // the edit text box for userName of the user
    private EditText password;          // the edit text box for password of the user
    private ProgressDialog progressDialog;  // progress Dialogue
    private AccessUsers accessUsers;      // helps  access users

    private static String uniqueUserName;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uniqueUserName=null;

        // initializing the buttons and edit textboxes
        userName = findViewById(R.id.editText_login_enterUserName);
        password = findViewById(R.id.editText_login_enterPassword);

        accessUsers=new AccessUsers();


    }


    public void loginBtnClick(View view)
    {
        //------------------------------------------------------
        // loginBtnClick
        //
        // PURPOSE: Performs actions when the Login button is clicked on Login activity
        //
        // Parameter- View view- is the display view.
        //------------------------------------------------------

        String userName;       // The userName in the textbox will be stored here.
        String userPassword;    // The userName in the password will be stored here.


        // check if these fields are empty
        boolean userNameEmpty = this.userName.getText().toString().isEmpty();
        boolean passwordEmpty = password.getText().toString().isEmpty();


        if (userNameEmpty||passwordEmpty)   // if userName or password field is empty
        {

            // Make a toast to display error message
            Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            userName = this.userName.getText().toString();
            userPassword = password.getText().toString();


            // Show a progress Dialog while the authentication is loading
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging In");
            progressDialog.show();

            User loggedInUser = authenticate (userName,userPassword);

            if (loggedInUser!=null)
            {  // if logging in is successful

                progressDialog.dismiss();   // dismiss the progress bar

                // show user that login was successful
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                uniqueUserName=userName;

                // login and go to homepage
                finish();

                Intent logIn=new Intent(getApplicationContext(),MainActivity.class);
                logIn.putExtra(USERNAME_TEXT,uniqueUserName);
                startActivity(logIn);


            }
            else    // if authentication is not successful.
            {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "INVALID userName OR PASSWORD", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void signUpBtnClick(View view)
    {
        //------------------------------------------------------
        // signUpBtnClick
        //
        // PURPOSE: Performs actions when the sign up button is clicked on Login activity
        //
        // Parameter- View view- is the display view.
        //------------------------------------------------------


        // takes to sign in page
        Intent signUp = new Intent(Login.this, SignUp.class);
        startActivity (signUp);


    }


    public User authenticate(String userName, String userPassword)
    {
        //------------------------------------------------------
        // authenticate
        //
        // PURPOSE: Performs an authentication and returns true if authentication was successful
        //
        // Parameter- String userName- the userName of the user
        //              String userPassword- the userName of the user
        // Return- True if authentication is successful
        //------------------------------------------------------

        User retValue=null;


        boolean credintalsValid=accessUsers.correctPassword(userName,userPassword);

        if (credintalsValid)    // if the credintals are valid
        {
            retValue=accessUsers.getUser(userName);
        }

        return retValue;
    }


    @Override
    public void onBackPressed() {
        finish();
        Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginPage);    }
}
