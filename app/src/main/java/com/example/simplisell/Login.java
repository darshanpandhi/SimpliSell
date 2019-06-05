package com.example.simplisell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private EditText email;             // the edit text box for email of the user
    private EditText password;          // the edit text box for password of the user
    private ProgressDialog progressDialog;  // progress Dialogue


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initializing the buttons and edit textboxes
        email= findViewById(R.id.editText_login_enterEmail);
        password= findViewById(R.id.editText_login_enterPassword);


    }


    public void loginBtnClick(View view){
        //------------------------------------------------------
        // loginBtnClick
        //
        // PURPOSE: Performs actions when the Login button is clicked on Login activity
        //
        // Patameter- View view- is the display view.
        //------------------------------------------------------

        String userEmail;       // The email in the textbox will be stored here.
        String userPassword;    // The email in the password will be stored here.


        // check if these fields are empty
        boolean emailEmpty = email.getText().toString().isEmpty();
        boolean passwordEmpty = password.getText().toString().isEmpty();


        if (emailEmpty||passwordEmpty)   // if email or password field is empty
        {

            // Make a toast to display error message
            Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            userEmail=email.getText().toString();
            userPassword=password.getText().toString();


            // Show a progress Dialog while the authentication is loading
            progressDialog= new ProgressDialog(this);
            progressDialog.setMessage("Logging In");
            progressDialog.show();

            boolean isSuccessful = authenticate (userEmail,userPassword);

            if (isSuccessful){  // if logging in is successful

                progressDialog.dismiss();   // dismiss the progress bar

                // show user that login was successful
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                // login and go to homepage
                finish();
                Intent logIn=new Intent(Login.this,MainActivity.class);
                startActivity(logIn);


            }
            else    // if authentication is not successful.
            {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "INVALID EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void signUpBtnClick(View view){
        //------------------------------------------------------
        // signUpBtnClick
        //
        // PURPOSE: Performs actions when the sign up button is clicked on Login activity
        //
        // Patameter- View view- is the display view.
        //------------------------------------------------------


        // takes to sign in page
        Intent signUp= new Intent(Login.this,SignUp.class);
        startActivity (signUp);


    }


    public boolean authenticate(String userEmail,String userPassword){
        //------------------------------------------------------
        // authenticate
        //
        // PURPOSE: Performs an authentication and returns true if authentication was successful
        //
        // Patameter- String userEmail- the email of the user
        //              String userPassword- the email of the user
        // Return- True if authentication is successful
        //------------------------------------------------------

        boolean retValue=false;


        //**********AUTHENTICATE WITH BUSINESS LOGIC and DB


        return retValue;
    }


    @Override
    public void onBackPressed() {
        // if the back button is pressed. Do nothing for now.

    }
}
