package edu.nlu.probooktic.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.nlu.probooktic.R;


public class PromotionDetail extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_program_detail);

        btnBack = findViewById(R.id.btn_BackCTKM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PromotionDetail.this, PromotionProgram.class));
            }
        });
    }
}
