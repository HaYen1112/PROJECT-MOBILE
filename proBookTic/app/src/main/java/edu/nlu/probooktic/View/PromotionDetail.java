package edu.nlu.probooktic.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.nlu.probooktic.Admin.TrangChuAdmin;
import edu.nlu.probooktic.R;

public class PromotionDetail extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_program_detail);
        imageView = findViewById(R.id.home_promotion);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PromotionDetail.this, TrangChuAdmin.class);
                startActivity(intent);
            }
        });
    }
}
