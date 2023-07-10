package com.example.uas2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    User logged;

    TextView welcome;


    //region button handler
    protected void buttonHandlerHistory(View view){
        Intent i = new Intent(this, HistoryActivity.class);
        startActivity(i);
    }
    protected void buttonHandlerLocation(View view){
        Intent i = new Intent(this, LocationActivity.class);
        startActivity(i);
    }
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logged = Global.getLogged();
        Global.isLogged(this);

        welcome = (TextView) findViewById(R.id.main_welcome);
        welcome.setText("Welcome, "+ logged.getName());

        Button btnHistory = (Button) findViewById(R.id.main_buttonHistory);
        btnHistory.setOnClickListener(this::buttonHandlerHistory);

        Button btnLocation = (Button) findViewById(R.id.main_buttonLocation);
        btnLocation.setOnClickListener(this::buttonHandlerLocation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Global.isLogged(this);
    }
}