package com.simplisell.presentation.loginactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;

public class ForgetPassword extends AppCompatActivity
{
    private EditText userName;             // firstNLastName of user
    private TextView securityQuestion;
    private EditText securityAnswer;
    private AccessUsers accessUsers;      // helps  access users
    private Credentials credentials;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private boolean securityQuestionDisplayed;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forget_password);
        userName = findViewById(R.id.editText_forgetPassword_userName);
        securityAnswer = findViewById(R.id.editText_forgetPassword_securityAnswer);
        securityQuestion = findViewById(R.id.textView_forgetPassword_securityQuestion);
        securityQuestionDisplayed = false;

        accessUsers = new AccessUsers();
        credentials = new Credentials();
    }


    public void getSecurityQuestionBtnClick(View view)
    {
        // get the input from user
        String userName = this.userName.getText().toString().trim();
        validateUser(userName);
    }


    public void resetBtnClick(View view)
    {
        String userName = this.userName.getText().toString().trim();
        String answer = this.securityAnswer.getText().toString().trim();
        validateSecurityAnswer(userName, answer);
    }


    public void alertResetPassword(final User user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Password");

        // Set up the input
        newPassword = new EditText(this);
        confirmNewPassword = new EditText(this);

        newPassword.setHint(" New Password");
        confirmNewPassword.setHint(" Confirm New Password");
        newPassword.setHintTextColor(Color.RED);
        confirmNewPassword.setHintTextColor(Color.RED);


        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(newPassword);
        ll.addView(confirmNewPassword);

        builder.setView(ll);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String newPass = newPassword.getText().toString();
                String confirmNewPass = confirmNewPassword.getText().toString();
                if (newPass.equals(confirmNewPass))
                {
                    if (credentials.validPassword(newPass))
                    {
                        accessUsers.updatePassword(user.getUserName(), newPass);
                        Toast.makeText(getApplicationContext(), "Password has been changed", Toast.LENGTH_SHORT).show();

                        finish();
                        Intent loginPage = new Intent(getApplicationContext(), Login.class);
                        startActivity(loginPage);
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
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void validateUser(String userName)
    {
        // are the fields empty
        boolean empty = !userName.isEmpty();

        if (empty)  // if any field is empty
        {
            User user = accessUsers.getUser(userName);
            if (user != null)
            {
                String question = user.getSecurityQuestion();
                this.securityQuestion.setText(question);
                securityQuestionDisplayed = true;
            }
            else
            {
                Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
                this.securityQuestion.setText("SECURITY QUESTION");
            }
        }
        else
        {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            this.securityQuestion.setText("SECURITY QUESTION");
        }
    }


    private void validateSecurityAnswer(String userName, String answer)
    {
        boolean empty = !answer.isEmpty();

        if (securityQuestionDisplayed)
        {
            if (empty)  // if any field is empty
            {
                User user = accessUsers.getUser(userName);
                if (user != null)
                {
                    boolean correct = credentials.correctSecurityAnswer(userName, answer);
                    if (correct)
                    {
                        alertResetPassword(user);
                    }
                    else
                    {
                        Toast.makeText(this, "Incorrect answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Please enter an answer to the security question", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Please retrieve a security question", Toast.LENGTH_SHORT).show();
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
