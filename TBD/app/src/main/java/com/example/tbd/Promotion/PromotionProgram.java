package com.example.tbd.Promotion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tbd.R;

public class PromotionProgram extends AppCompatActivity {
    CardView cvTrian;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chuong_trinh_khuyen_mai);

        cvTrian = findViewById(R.id.cv_triankhachhang);
        cvTrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PromotionProgram.this, PromotionDetail.class));
            }
        });
    }
}
