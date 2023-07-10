package com.example.uas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    //region View Declaration
    EditText loginEmailEditText, loginPasswordEditText;
    TextView wrongPw;
    //endregion

    String loginEmail;
    String loginPw;

    //region Login activity method
    /*
    public User check(){
        for(int i=0; i< users.size(); i++) {
            if (loginEmail.equals(users.get(i).email) && loginPw.equals(users.get(i).pw)){
                return users.get(i);
            }
        }
        return null;
    }
    */
    //endregion

    //region button handler
    public void buttonHandlerLogin(View view){
        this.loginEmail = loginEmailEditText.getText().toString();
        this.loginPw = loginPasswordEditText.getText().toString();

        Global.logUserIn(this.loginEmail, this.loginPw);
        User logged = Global.getLogged();

        if(logged == null) {
            wrongPw.setVisibility(View.VISIBLE);
            return;
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //remove after test
        Global.initiateTestList();

        loginEmailEditText = (EditText) findViewById(R.id.login_email);
        loginPasswordEditText = (EditText) findViewById(R.id.login_pw);

        wrongPw = (TextView) findViewById(R.id.login_wrongpw);
        wrongPw.setVisibility(View.INVISIBLE);

        Button btnLogin = (Button) findViewById(R.id.login_button_login);
        btnLogin.setOnClickListener(this::buttonHandlerLogin);
    }

    protected void onResume() {
        super.onResume();
        Global.logUserOut();
    }
}