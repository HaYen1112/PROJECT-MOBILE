package edu.nlu.probooktic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.nlu.probooktic.R;

public class Kbyt extends AppCompatActivity {
static final int RESULT_NO=1025;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbyt);
        Button confirm = (Button)findViewById(R.id.btn_confirm_KBYT);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("RESULT_KB",true);
                setResult(RESULT_NO,intent);
                finish();
            }
        });

    }
}