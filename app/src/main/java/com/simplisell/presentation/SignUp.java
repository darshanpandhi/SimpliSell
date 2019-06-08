package com.simplisell.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simplisell.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {


    private EditText name;              // name of user
    private EditText userName;             // firstNLastName of user
    private EditText password;          // password of user
    private EditText confirmPassword;   // confirm passord of user




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initializing the buttons and edit textboxes
        userName = findViewById(R.id.editText_name_user);
        password = findViewById(R.id.editText_name_registrationPass);
        name = findViewById(R.id.editText_name_registrationName);
        confirmPassword = findViewById(R.id.editText_name_registrationConfirmPass);

    }

    public void signUpBtnClickRegister(View view){
        //------------------------------------------------------
        // signUpBtnClickRegister
        //
        // PURPOSE: Performs actions when the signUp button is clicked.
        //
        // Parameter- View view- is the display view.
        //------------------------------------------------------


        // get the input from user
        String firstNLastName = name.getText().toString().trim();
        String userName = this.userName.getText().toString().trim();
        String userPassword = password.getText().toString();
        String userConfirmPassword = confirmPassword.getText().toString();


        validate(firstNLastName,userName,userPassword,userConfirmPassword);


    }

    public void backToLoginTextClick(View view){
        //------------------------------------------------------
        // signUpBtnClickRegister
        //
        // PURPOSE: Performs actions when the back to login text is clicked.
        //
        // Parameter- View view- is the display view.
        //------------------------------------------------------

        // takes to sign in page
        Intent loginPage = new Intent(getApplicationContext(), Login.class);
        startActivity(loginPage);

    }


    private void validate(String firstNLastName,String userName,String userPassword, String userConfirmPassword){
        //------------------------------------------------------
        // validate
        //
        // PURPOSE: Performs a validation and adds the user to the DB
        //
        //------------------------------------------------------


        int number;

        // are the fields empty
        boolean empty = (!firstNLastName.isEmpty()&&!userName.isEmpty()&&!userPassword.isEmpty()&&!userConfirmPassword.isEmpty());

        if(empty){



            if (passwordChecker(userPassword,userConfirmPassword))  // if password meets the standards
                {

                    // Animation starts



                    // check for duplicate username in DB and pop a toast


                    // ************add the data to the DB and Log in this user!************************





                }


        }else{

            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();

        }

    }




    public boolean passwordChecker(String userPassword,String userConfirmPassword){
        //------------------------------------------------------
        // passwordChecker
        //
        // PURPOSE: checks password and sees if it meets the standards, returns true if it does
        //
        //------------------------------------------------------

        boolean retValue  = false;


        if (userPassword.equals(userConfirmPassword))   // if both passwords are same
        {

            if (password.length()>=6) // if password is at least 6 characters
            {
                retValue=true;
            }
            else
            {
                Toast.makeText(this, "Password should have a minimum of 6 characters", Toast.LENGTH_SHORT).show();

            }


        }
        else
        {

            Toast.makeText(this, "Passwords Dont Match!", Toast.LENGTH_SHORT).show();

        }

        return retValue;

    }

}
