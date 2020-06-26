package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText edtPunchPower;
    private EditText edtKickPower;
    private EditText edtPunchSpeed;
    private EditText edtKickSpeed;
    private EditText edtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
    }

    public void helloWorldTapped(View view) {
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null) {
//                    Toast.makeText(SignUp.this,"Boxer Object saved successfully",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        try {
            final ParseObject kick_boxer = new ParseObject("kick_boxer");
            kick_boxer.put("name",edtName.getText().toString());
            kick_boxer.put("kickSpeed",Integer.parseInt(edtKickSpeed.getText().toString()));
            kick_boxer.put("kickPower",Integer.parseInt(edtKickPower.getText().toString()));
            kick_boxer.put("punchSpeed",Integer.parseInt(edtPunchSpeed.getText().toString()));
            kick_boxer.put("punchPower",Integer.parseInt(edtPunchPower.getText().toString()));
            kick_boxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kick_boxer.get("name") + "is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
