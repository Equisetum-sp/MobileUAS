package com.example.uas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    User logged;
    HistoryAdapter adapter;

    //region button handler
    protected void buttonHandlerHistory(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Global.isLogged(this);
        this.logged = Global.getLogged();

        RecyclerView rv = (RecyclerView) findViewById(R.id.history_recycler);
        ArrayList<Order> data = logged.getOrders();

        adapter = (HistoryAdapter) new HistoryAdapter(data, HistoryActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter((RecyclerView.Adapter) adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        Button buttonReturn = (Button) findViewById(R.id.history_buttonReturn);
        buttonReturn.setOnClickListener(this::buttonHandlerHistory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.logged = Global.getLogged();
    }
}