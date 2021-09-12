package edu.nlu.probooktic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.nlu.probooktic.Model.TripInfo;
import edu.nlu.probooktic.R;

public class AddTripInfomation extends AppCompatActivity {
    private EditText edtDBD,edtDKT,edtTGBD,edtTGKT,edtBS;
    private String strDBD, strDKT,strTGBD,strTGKT,strBS;
    private Button btnAdd,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip_infomation);
        //////
        edtDBD = (EditText)findViewById(R.id.edtAddDBD);
        edtDKT = (EditText)findViewById(R.id.edtAddDKT);
        edtTGBD = (EditText)findViewById(R.id.edtAddTGBD);
        edtTGKT = (EditText)findViewById(R.id.edtAddTGKT);
        edtBS = (EditText)findViewById(R.id.edtAddBS);
        btnAdd = (Button)findViewById(R.id.btnAddd);
        btnCancel= (Button)findViewById(R.id.btnCancelll);

        ///////

         /////
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strDBD=edtDBD.getText().toString();
                strDKT=edtDKT.getText().toString();
                strTGBD=edtTGBD.getText().toString();
                strTGKT=edtTGKT.getText().toString();
                strBS=edtBS.getText().toString();
                if (strDBD.isEmpty()||strDKT.isEmpty()|| strTGBD.isEmpty()||strTGKT.isEmpty()||strBS.isEmpty()){
                    Toast.makeText(AddTripInfomation.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }else {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("TripInfomation");
                TripInfo tr = new TripInfo(strDBD,strDKT,strTGBD,strTGKT,strBS);
                mDatabase.push().setValue(tr);
                Toast.makeText(AddTripInfomation.this, "Đã thêm thông tin chuyến đi", Toast.LENGTH_LONG).show();
                Admin_TripManagement.arrTripInf.add(tr);
                Admin_TripManagement.adapter.notifyDataSetChanged();
                finish();
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}