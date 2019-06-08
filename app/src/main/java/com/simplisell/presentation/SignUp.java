package com.simplisell.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.ValidPasswordChecker;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;


public class SignUp extends AppCompatActivity {


    private EditText name;              // name of user
    private EditText userName;             // firstNLastName of user
    private EditText password;          // password of user
    private EditText confirmPassword;   // confirm passord of user

    private AccessUsers accessUsers;      // helps  access users




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initializing the buttons and edit textboxes
        userName = findViewById(R.id.editText_name_user);
        password = findViewById(R.id.editText_name_registrationPass);
        name = findViewById(R.id.editText_name_registrationName);
        confirmPassword = findViewById(R.id.editText_name_registrationConfirmPass);

        accessUsers=new AccessUsers();

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

        if(empty)  // if any field is empty
        {

            if(userConfirmPassword.equals(userPassword))  // if both fields have the same password
            {
                if (ValidPasswordChecker.validPassword(userPassword))  // if password meets the standards
                {

                    User newUser=new UserAdvertiser(userName,userPassword);   // create a new user

                    if(accessUsers.insertNewUser(newUser)!=null){   // check if userName is in the database and insert

                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

                        // Pass this domain object across activities
                        Intent signedUp=new Intent(getApplicationContext(),MainActivity.class);
                        signedUp.putExtra("User",newUser);
                        startActivity(signedUp);

                    }
                    else
                    {
                        Toast.makeText(this, "Username has been taken! Please try another", Toast.LENGTH_SHORT).show();

                    }



                } else {
                    {
                        Toast.makeText(this, "Password should have a minimum of 6 characters and a maximum of 12 characters", Toast.LENGTH_SHORT).show();

                    }

                }
            }else{
                Toast.makeText(this, "Passwords Dont Match!", Toast.LENGTH_SHORT).show();

            }


        }else{

            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();

        }

    }

}
