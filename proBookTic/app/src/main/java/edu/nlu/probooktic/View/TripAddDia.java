package edu.nlu.probooktic.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;


import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class TripAddDia extends Dialog {

    interface AddTripListener {
        public void addTrip();
    }

    public Context context;
    private String tDBD,tDKT,tTGBD,tTGKT,BS;
    private TextView tvTD,tvTG;
    private EditText edtDate;
    private Button buttonOK;
    private Button buttonCancel;
    private CheckBox cb;
    private TripAddDia.AddTripListener listener;

    public TripAddDia(Context context, TripAddDia.AddTripListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.trip_management_dialog);
        tDBD= TripManagementAdapter.DBD;
        tDKT= TripManagementAdapter.DKT;
        tTGBD=TripManagementAdapter.TGBD;
        tTGKT=TripManagementAdapter.TGKT;
        BS = TripManagementAdapter.BS;
        this.edtDate = (EditText) findViewById(R.id.id_dia_date);
        this.tvTD= (TextView) findViewById(R.id.id_dia_TD);
        this.tvTG=(TextView) findViewById(R.id.id_dia_TG);
        this.buttonOK = (Button) findViewById(R.id.btn_dia_OK);
        this.buttonCancel  = (Button) findViewById(R.id.btn_dia_cancel);
        this.cb= (CheckBox)findViewById(R.id.chbCreatDSVe);
        /////
        tvTD.setText(tDBD +" - "+tDKT);
        tvTG.setText(tTGBD +" - "+tTGKT);
       cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked)
                   TripManagementAdapter.isCreate=true;
               else TripManagementAdapter.isCreate=false;
           }
       });
        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        String date = this.edtDate.getText().toString();

        if(date== null || date.isEmpty())  {
            Toast.makeText(this.context, "Vui lòng nhập ngày cần tạo", Toast.LENGTH_LONG).show();
            return;
        }else {
            TripManagementAdapter.date = checkDate(date);
            if(this.listener!= null)  {
                this.listener.addTrip();
            }
            this.dismiss(); // Close Dialog
        }


    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }
    private Date checkDate (String date){
        try {
            Date dateN =Trip.stringToDate(date);
            Date dateC= new Date();
            if (dateN.getTime()-dateC.getTime()<0){
                Toast.makeText(this.context, "Ngày phải bằng hoặc lớn hơn ngày hiện tại", Toast.LENGTH_LONG).show();
                return null;
            }else {
                return dateN;
            }
        } catch (ParseException e) {
            Toast.makeText(this.context, "Sai định dạng ngày!", Toast.LENGTH_LONG).show();
            return null;
        }


    }
}