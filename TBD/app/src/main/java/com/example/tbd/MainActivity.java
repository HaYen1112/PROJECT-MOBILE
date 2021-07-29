package com.example.tbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tbd.Promotion.PromotionProgram;
import com.example.tbd.Ticket.CheckTicket;
import com.example.tbd.Ticket.ViewTicketFragment;

public class MainActivity extends AppCompatActivity {

    Button btnKBYT, btnKTV, btnCTKM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnKBYT = findViewById(R.id.btn_kbyt);
        btnKTV = findViewById(R.id.btn_ktra_ve);
        btnCTKM = findViewById(R.id.btn_ctkm);


        btnKBYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new KhaiBaoYTe();
                loadFragment(f);
            }
        });
        btnKTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CheckTicket.class));
            }
        });
        btnCTKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PromotionProgram.class));
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        // replace fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.main_screen, fragment).commit();
    }
}