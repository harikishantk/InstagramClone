package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private EditText edtPunchPower;
    private EditText edtKickPower;
    private EditText edtPunchSpeed;
    private EditText edtKickSpeed;
    private EditText edtName;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickBoxers;
    private Button btnTransition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kick_boxer");
                parseQuery.getInBackground("SJCHwLegXW", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null) {
                            txtGetData.setText(object.get("name") + "-" + "Punch Power:" + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("kick_boxer");
                queryAll.whereGreaterThan("punchPower",100);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                       if(e == null) {
                           if(objects.size()>0) {
                               for(ParseObject kickBoxer: objects) {
                                   allKickBoxers = allKickBoxers + kickBoxer.get("name") + kickBoxer.get("kickSpeed") + kickBoxer.get("kickPower") + kickBoxer.get("punchSpeed") + kickBoxer.get("punchPower") + "\n";
                               }
                               FancyToast.makeText(SignUp.this,allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                           } else {
                               FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }
                       }
                    }
                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);
            }
        });
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
