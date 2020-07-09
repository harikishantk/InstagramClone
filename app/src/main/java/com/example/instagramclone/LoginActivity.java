package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLLoginEmail,edtLoginPassword;
    private Button btnLogInActivity,btnLogInLoginactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtLLoginEmail = findViewById(R.id.edtLogInEmail);
        edtLoginPassword = findViewById(R.id.edtLogInPassword);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==keyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN) {
                    onClick(btnLogInLoginactivity);
                }
                return false;
            }
        });
        btnLogInActivity = findViewById(R.id.btnLogInActivity);
        btnLogInLoginactivity = findViewById(R.id.btnLogInloginactivity);

        btnLogInLoginactivity.setOnClickListener(this);
        btnLogInActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null) {
            transitionSocialMediaActivity();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogInloginactivity:
                if (edtLLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this,"Email,Password is required",Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                }
                else {
                    ParseUser.logInInBackground(edtLLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + " has been logged in successfully", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionSocialMediaActivity();
                            }
                        }
                    });
                }
                break;
            case R.id.btnLogInActivity:

                break;
        }
    }
    public void LayoutIsTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void transitionSocialMediaActivity() {
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}