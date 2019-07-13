package com.simplisell.presentation.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

public class SignUp extends AppCompatActivity
{

    private final String USERNAME_TEXT = "USER";

    private static String uniqueUserName;
    private EditText name;              // name of user
    private EditText userName;             // firstNLastName of user
    private EditText password;          // password of user
    private EditText confirmPassword;   // confirm password of user
    private Spinner securityQuestion;  // security question of user
    private EditText securityAnswer;    // security answer of user
    private AccessUsers accessUsers;      // helps  access users
    private Credentials credentials;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uniqueUserName = null;

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // initializing the buttons and edit textboxes
        userName = findViewById(R.id.editText_name_user);
        password = findViewById(R.id.editText_name_registrationPass);
        name = findViewById(R.id.editText_name_registrationName);
        confirmPassword = findViewById(R.id.editText_name_registrationConfirmPass);
        securityQuestion = findViewById(R.id.spinner_signUp_securityQuestions);
        securityAnswer = findViewById(R.id.editText_name_registrationSecurityAnswer);

        accessUsers = new AccessUsers();
        credentials = new Credentials();
    }


    public void signUpBtnClickRegister(View view)
    {

        // get the input from user
        String firstNLastName = name.getText().toString().trim();
        String userName = this.userName.getText().toString().trim();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();
        String userSecurityQuestion = String.valueOf(securityQuestion.getSelectedItem());
        String userSecurityAnswer = securityAnswer.getText().toString();
        validate(firstNLastName, userName, userPassword, userConfirmPassword, userSecurityQuestion, userSecurityAnswer);
    }


    public void backToLoginTextClick(View view)
    {

        // takes to sign in page
        Intent loginPage = new Intent(getApplicationContext(), Login.class);
        startActivity(loginPage);
    }


    private void validate(String firstNLastName, String userName, String userPassword, String userConfirmPassword,
                          String userSecurityQuestion, String userSecurityAnswer)
    {
        int number;

        // are the fields empty
        boolean empty =
                (!firstNLastName.isEmpty() && !userName.isEmpty() && !userPassword.isEmpty() && !userConfirmPassword.isEmpty() && !userSecurityQuestion.isEmpty() && !userSecurityAnswer.isEmpty());

        boolean selectedSecurityQuestion = !userSecurityQuestion.equals("Select a security question");

        if (empty)  // if any field is empty
        {
            if (selectedSecurityQuestion)
            {
                if (userConfirmPassword.equals(userPassword))  // if both fields have the same password
                {
                    if (credentials.validPassword(userPassword))  // if password meets the standards
                    {

                        UserAdvertiser newUserAdvertiser = new UserAdvertiser(firstNLastName, userName, userPassword,
                                userSecurityQuestion, userSecurityAnswer, null, null,
                                0);   // create a new userAdvertiser

                        if (accessUsers.insertNewUserAdvertiser(newUserAdvertiser) != null)
                        {   // check if userName is in the database and insert

                            String registrationSuccessMessage = "Registration successful";
                            Toast.makeText(this, registrationSuccessMessage, Toast.LENGTH_SHORT).show();

                            String successMessage = "Logged in as " + userName;
                            Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();
                            uniqueUserName = userName;

                            // Pass this domain object across activities
                            Intent signedUp = new Intent(getApplicationContext(), MainActivity.class);
                            signedUp.putExtra(USERNAME_TEXT, uniqueUserName);
                            RecyclerViewAdapter.login(userName);
                            startActivity(signedUp);
                        }
                        else
                        {
                            Toast.makeText(this, "Username has been taken! Please try another", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Password should only be composed of letters or " +
                                "numbers, and have a minimum of 6 characters and a maximum of 12 characters",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Please select a security question", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed()
    {
        finish();
        Intent loginPage = new Intent(getApplicationContext(), Login.class);
        startActivity(loginPage);
    }
}
