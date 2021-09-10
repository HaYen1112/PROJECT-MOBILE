package edu.nlu.probooktic.View;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

public class Findtrip1 extends AppCompatActivity {

    RadioGroup radioGroup;
    Spinner spDsOrign,spDsDes;
    EditText eTngaydi, eTngayVe;
    ImageButton imgBttChonngaydi, imgBttChonngayve;
    ImageButton imgBttTimchuyendi;
    ArrayList<String> ar;
    Calendar calendar1;
    String isOneway;

//    TextView tvdp;
    String diemkhoihanh, diemden, date, dateReturn;

    private DatabaseReference mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtrip1);

        radioGroup        = (RadioGroup) findViewById(R.id.radioGr);
        spDsOrign         = (Spinner) findViewById(R.id.spnOrign);
        spDsDes           = (Spinner) findViewById(R.id.spnDes);
        eTngaydi          = (EditText) findViewById(R.id.editTextngayDi);
        eTngayVe          = (EditText) findViewById(R.id.editTextngayVe);
        imgBttChonngaydi  = (ImageButton) findViewById(R.id.imageBttChonngayDi);
        imgBttChonngayve  = (ImageButton) findViewById(R.id.imageBttChonngayVe);
        imgBttTimchuyendi = (ImageButton) findViewById(R.id.imgBttTimchuyendi);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1chieu:
                        isOneway = "1";
                        Toast.makeText(Findtrip1.this, isOneway,Toast.LENGTH_SHORT).show();
//                        Toast.makeText(Findtrip1.this,"n",Toast.LENGTH_SHORT);
                        imgBttChonngayve.setBackgroundResource(R.drawable.lich);
                        eTngayVe.setText("");
                        imgBttChonngayve.setMaxWidth(20);
                        imgBttChonngayve.setMaxHeight(20);
                        imgBttChonngayve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });
                        break;
                    case R.id.radioButtonKhuHoi:
                        isOneway = "2";
                        Toast.makeText(Findtrip1.this, isOneway,Toast.LENGTH_SHORT).show();
//                        Toast.makeText(Findtrip1.this,"n",Toast.LENGTH_SHORT);
                        imgBttChonngayve.setBackgroundResource(R.drawable.calendar_icon);
                        imgBttChonngayve.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ChonNgayVe();

                            }
                        });
                        break;
         }
            }
        });



        ArrayList<String> arrDsDiaDiem = new ArrayList<String>();
//        arrDsDiaDiem.add("TP HCM");
//        arrDsDiaDiem.add("HA NOI");
//        arrDsDiaDiem.add("DA LAT");
//        arrDsDiaDiem.add("NHA TRANG");
//        arrDsDiaDiem.add("PHAN THIET");
//        arrDsDiaDiem.add("HUE");
//        arrDsDiaDiem.add("TP HCM");
//        arrDsDiaDiem.add("TP HCM");

        ar = new ArrayList<String>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrDsDiaDiem);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDsOrign.setAdapter(arrayAdapter);
        spDsDes.setAdapter(arrayAdapter);

        mdata = FirebaseDatabase.getInstance().getReference();

//        ArrayList<Trip> arrTrip = new ArrayList<>();
//        arrTrip.add(new Trip(11090, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"6H30", "11H30"));
//        arrTrip.add(new Trip(11091, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"7H30", "12H30"));
//        arrTrip.add(new Trip(11092, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"8H30", "13H30"));
//        arrTrip.add(new Trip(11096, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"9H30", "14H30"));
//        arrTrip.add(new Trip(11097, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"10H30", "15H30"));
//        arrTrip.add(new Trip(11098, "TP HCM", "HA NOI", 150000, new Date(26,12,2021),"11H30", "16H30"));
//
//
//        arrTrip.add(new Trip(11093, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"6H30", "11H30"));
//        arrTrip.add(new Trip(11094, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"7H30", "12H30"));
//        arrTrip.add(new Trip(11095, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"8H30", "13H30"));
//        arrTrip.add(new Trip(11099, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"9H30", "14H30"));
//        arrTrip.add(new Trip(11100, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"10H30", "15H30"));
//        arrTrip.add(new Trip(11101, "HA NOI", "TP HCM", 150000, new Date(28,12,2021),"11H30", "16H30"));
//
//        arrTrip.add(new Trip(11083, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"6H30", "11H30"));
//        arrTrip.add(new Trip(11084, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"7H30", "12H30"));
//        arrTrip.add(new Trip(11085, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"8H30", "13H30"));
//        arrTrip.add(new Trip(11086, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"9H30", "14H30"));
//        arrTrip.add(new Trip(11087, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"10H30", "15H30"));
//        arrTrip.add(new Trip(11088, "HUE", "NHA TRANG", 150000, new Date(29,10,2021),"11H30", "16H30"));
//
//        arrTrip.add(new Trip(11071, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"6H30", "11H30"));
//        arrTrip.add(new Trip(11072, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"7H30", "12H30"));
//        arrTrip.add(new Trip(11073, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"8H30", "13H30"));
//        arrTrip.add(new Trip(11074, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"9H30", "14H30"));
//        arrTrip.add(new Trip(11075, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"10H30", "15H30"));
//        arrTrip.add(new Trip(11076, "NHA TRANG", "HUE", 150000, new Date(30,10,2021),"11H30", "16H30"));
//
//        for (Trip x:arrTrip) {
//            mdata.child("ChuyenDi").push().setValue(x);
//        }

        //        arrTrip.add(new Trip(11090, "TP HCM", "HA NOI", 150000, new Date(2,3,2021),"1H30", "11H30"));
//        arrTrip.add(new Trip(11020, "HA NOI", "TP HCM", 150000, new Date(4,5,2021),"14H30", "18H30"));
//        arrTrip.add(new Trip(11030, "KHANH HOA", "TP HCM", 150000, new Date(3,6,2021),"7H40", "10H40"));
//        arrTrip.add(new Trip(11040, "TP HCM", "KHANH HOA", 150000,new Date(5,7,2021),"7H50", "10H45"));
//        arrTrip.add(new Trip(11011, "TP HCM", "HA NOI", 150000, new Date(2,3,2021),"2H30", "11H30"));
//        arrTrip.add(new Trip(11022, "HA NOI", "TP HCM", 150000, new Date(4,5,2021),"15H30", "18H30"));
//        arrTrip.add(new Trip(11023, "KHANH HOA", "TP HCM", 150000, new Date(3,6,2021),"8H40", "10H40"));
//        arrTrip.add(new Trip(11024, "TP HCM", "KHANH HOA", 150000,new Date(5,7,2021),"8H50", "10H45"));
//        arrTrip.add(new Trip(11021, "TP HCM", "HA NOI", 150000, new Date(2,3,2021),"3H30", "11H30"));
//        arrTrip.add(new Trip(11032, "HA NOI", "TP HCM", 150000, new Date(4,5,2021),"9H30", "18H30"));
//        arrTrip.add(new Trip(11043, "KHANH HOA", "TP HCM", 150000, new Date(3,6,2021),"1H40", "10H40"));
//        arrTrip.add(new Trip(110054, "TP HCM", "KHANH HOA", 150000,new Date(5,7,2021),"4H50", "10H45"));
//                //push du lieu len database firebase
//        for (Trip x:arrTrip) {
//            mdata.child("ChuyenDi").push().setValue(x);
//        }

        mdata.child("ChuyenDi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Trip chuyendi = snapshot.getValue(Trip.class);
                arrDsDiaDiem.add(chuyendi.getDiemKH());
                addChuyendi(arrDsDiaDiem,ar);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot,String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot,String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        spDsOrign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Findtrip1.this, arrDsDiaDiem.get(position),Toast.LENGTH_SHORT).show();
                diemkhoihanh = arrDsDiaDiem.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDsDes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Findtrip1.this, arrDsDiaDiem.get(position),Toast.LENGTH_SHORT).show();
                diemden = arrDsDiaDiem.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgBttChonngaydi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgayDi();
            }
        });



        imgBttTimchuyendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOneway.equals("1")){
                    Intent mhChuyendi = new Intent(Findtrip1.this,Findtrip2.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("txtdiemKhoihanh", diemkhoihanh);
                    bundle.putString("txtdiemKetthuc", diemden);
                    bundle.putString("txtNgay", date);

                    mhChuyendi.putExtra("dulieu", bundle);
                    startActivity(mhChuyendi);
                }
                else if(isOneway.equals("2"))
                {
                    Intent mhChuyendi1 = new Intent(Findtrip1.this,Findtrip3.class);

                    Bundle bundle1 = new Bundle();
                    bundle1.putString("txtdiemKhoihanh", diemkhoihanh);
                    bundle1.putString("txtdiemKetthuc", diemden);
                    bundle1.putString("txtNgay", date);
                    bundle1.putString("txtNgayve", dateReturn);

                    mhChuyendi1.putExtra("dulieu", bundle1);
                    startActivity(mhChuyendi1);
                }
            }
        });

    }
    private void ChonNgayDi(){

        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán lại ngày tháng năm
                calendar.set(year,month,dayOfMonth);
                calendar1 = calendar;
                // dinh dang ngay
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                eTngaydi.setText(simpleDateFormat1.format(calendar.getTime()));
                date = simpleDateFormat1.format(calendar.getTime());
            }
        },nam,thang,ngay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();
        datePickerDialog.show();
    }
    private void ChonNgayVe(){

        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //gán lại ngày tháng năm
                calendar.set(year,month,dayOfMonth);
                // dinh dang ngay
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                eTngayVe.setText(simpleDateFormat2.format(calendar.getTime()));
                dateReturn = simpleDateFormat2.format(calendar.getTime());
            }
        },nam,thang,ngay);
        datePickerDialog2.getDatePicker().setMinDate(calendar1.getTimeInMillis());
        datePickerDialog2.show();
    }
    public void addChuyendi(ArrayList<String> arlisdau,ArrayList<String> themVao){
           for (int i = 0;i< arlisdau.size();i++){
               if(!themVao.contains(arlisdau.get(i))){
                   themVao.add(arlisdau.get(i));
               }
           }
           arlisdau.clear();
           arlisdau.addAll(themVao);
    }

}