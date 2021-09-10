package edu.nlu.probooktic.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.nlu.probooktic.R;

public class Pay extends AppCompatActivity {
    TextView card = null;
    TextView expire = null;
    TextView cvv = null;
    TextView nameOnCard = null;
   public static boolean startFromCart=false; ////Kiểm tra lấy list vé từ đâu? thanh toán hay info....

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!= null){
            startFromCart= bundle.getBoolean("FLAG");
        }

        Button b = (Button)findViewById(R.id.btn_add);
         card = (TextView) findViewById(R.id.txtcard_number);
         expire = (TextView) findViewById(R.id.txtExpiry_date);
         cvv = (TextView) findViewById(R.id.txtCVV);
         nameOnCard = (TextView) findViewById(R.id.txtNameOnCard);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFull()) {
                    Intent intent = new Intent(Pay.this, ConfirmBooking.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("CARD",card.getText().toString());
                    bundle.putString("EXPIRE",expire.getText().toString());
                    bundle.putString("CVV",cvv.getText().toString());
                    bundle.putString("NAME_ON_CARD",nameOnCard.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    //Create dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(Pay.this);
                    builder.setTitle("Thông báo!");
                    builder.setMessage("Bạn phải điền thông tin đầy đủ cho tất cả các ô!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            }
        });

    }

    public boolean checkFull() {

        if (!TextUtils.isEmpty(card.getText().toString())
                && !TextUtils.isEmpty(expire.getText().toString())
                && !TextUtils.isEmpty(cvv.getText().toString())
                && !TextUtils.isEmpty(nameOnCard.getText().toString()))
            return true;
        return false;
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}