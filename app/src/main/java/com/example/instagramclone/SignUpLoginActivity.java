package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp;
    private EditText edtUserNameLogIn;
    private EditText edtPasswordSignUp;
    private EditText edtPasswordLogIn;
    private Button btnSignUp;
    private Button btnLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogIn = findViewById(R.id.edtUserNameLogIn);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null) {
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username") + "is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtUserNameLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                       if(user!=null && e==null) {
                           FancyToast.makeText(SignUpLoginActivity.this,user.get("username") + "is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                       }
                       else {
                           FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                       }
                    }
                });
            }
        });

    }
}
