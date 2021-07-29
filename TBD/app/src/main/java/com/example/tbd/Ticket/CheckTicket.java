package com.example.tbd.Ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tbd.R;

public class CheckTicket extends AppCompatActivity {
    CardView cvTicket;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kiem_tra_ve);

        cvTicket = findViewById(R.id.cv_ticket);

        cvTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckTicket.this, TicketDetails.class));
            }
        });
    }
}
