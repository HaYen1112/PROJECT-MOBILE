package edu.nlu.probooktic.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import edu.nlu.probooktic.R;

public class PromotionProgram extends AppCompatActivity {
    CardView cvTrian;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_program);

        cvTrian = findViewById(R.id.cv_sukien);
        cvTrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PromotionProgram.this, PromotionDetail.class));
            }
        });
    }
}
