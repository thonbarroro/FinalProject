package com.example.anton.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button button1;
    EditText username, password;
    TextView txtview;
    Button show;
    LoginDataBaseAdapter LoginDataBaseAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.btn);
        username=(EditText)findViewById(R.id.email_address);
        password=(EditText)findViewById(R.id.pwd);
        txtview=(TextView)findViewById(R.id.signUpBtn);
        show = (Button)findViewById(R.id.btnShow);

        LoginDataBaseAdapter = new LoginDataBaseAdapter(this);
        LoginDataBaseAdapter = LoginDataBaseAdapter.open();


        assert button1 != null;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString();
                String pword = password.getText().toString();
                String uname = username.getText().toString();
                String savePassword = LoginDataBaseAdapter.getSinlgeEntry(email);
                String savePassword1 = LoginDataBaseAdapter.getUsername(uname);


                if (pword.equals(savePassword1)|pword.equals(savePassword)) {
                    Toast.makeText(MainActivity.this, uname + " has logged in. \n Password: " + pword, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, New.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "invalid email or password ", Toast.LENGTH_LONG).show();
                }

            }

        });

        txtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }

        });

        show.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                        final int cursor = password.getSelectionStart();
                                        int event = motionEvent.getAction();


                                        switch (event) {
                                            case MotionEvent.ACTION_DOWN:
                                                password.setTransformationMethod(null);
                                                password.setSelection(cursor);
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                password.setTransformationMethod(new PasswordTransformationMethod());
                                                password.setSelection(cursor);
                                                break;

                                        }

                                        return false;
                                    }
                                }
        );
    }


    private boolean isValidEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    private boolean isValidUsername(String uname) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(uname);
        return matcher.matches();
    }

}
