package edu.nlu.probooktic.View;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import edu.nlu.probooktic.Model.Date;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

public class Findtrip1 extends AppCompatActivity {

    RadioGroup radioGroup;
    Spinner spDsOrign,spDsDes;
    EditText eTngaydi, eTngayVe;
    ImageButton imgBttChonngaydi, imgBttChonngayve;
    Button imgBttTimchuyendi;
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
        imgBttTimchuyendi = (Button) findViewById(R.id.imgBttTimchuyendi);


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

        /*ArrayList<Trip> arrTrip = new ArrayList<>();
        try {
            arrTrip.add( new Trip("TR0001", "TP HCM", "HA NOI", Trip.stringToDate("2021-12-26"), "9H00", "13H00", "63F-5236"));


            arrTrip.add(new Trip("TR00001", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"6H30", "11H30","59B-00156"));
            arrTrip.add(new Trip("TR00002", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"7H30", "12H30","57B-85586"));
            arrTrip.add(new Trip("TR00003", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"8H30", "13H30","59B-08964"));
            arrTrip.add(new Trip("TR00004", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"9H30", "14H30","56B-22545"));
            arrTrip.add(new Trip("TR00005", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"10H30", "15H30","59D-01578"));
            arrTrip.add(new Trip("TR00006", "TP HCM", "HA NOI",  Trip.stringToDate("2021-12-26"),"11H30", "16H30","59A-58814"));


            arrTrip.add(new Trip("TR00007", "HA NOI", "TP HCM",  Trip.stringToDate("2021-12-28"),"6H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00008", "HA NOI", "TP HCM", Trip.stringToDate("2021-12-28"),"7H30", "12H30","51C-25896"));
            arrTrip.add(new Trip("TR00009", "HA NOI", "TP HCM",  Trip.stringToDate("2021-12-28"),"8H30", "13H30","51C-25896"));
            arrTrip.add(new Trip("TR00010", "HA NOI", "TP HCM",  Trip.stringToDate("2021-12-28"),"9H30", "14H30","51C-25896"));
            arrTrip.add(new Trip("TR00011", "HA NOI", "TP HCM",  Trip.stringToDate("2021-12-28"),"10H30", "15H30","51C-25896"));
            arrTrip.add(new Trip("TR00012", "HA NOI", "TP HCM",  Trip.stringToDate("2021-12-28"),"11H30", "16H30","51C-25896"));

            arrTrip.add(new Trip("TR00013", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"6H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00014", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"7H30", "12H30","51C-25896"));
            arrTrip.add(new Trip("TR00015", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"8H30", "13H30","51C-25896"));
            arrTrip.add(new Trip("TR00016", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"9H30", "14H30","51C-25896"));
            arrTrip.add(new Trip("TR00017", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"10H30", "15H30","51C-25896"));
            arrTrip.add(new Trip("TR00018", "HUE", "NHA TRANG",  Trip.stringToDate("2021-10-29"),"11H30", "16H30","51C-25896"));

            arrTrip.add(new Trip("TR00019", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"6H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00020", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"7H30", "12H30","51C-25896"));
            arrTrip.add(new Trip("TR00021", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"8H30", "13H30","51C-25896"));
            arrTrip.add(new Trip("TR00022", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"9H30", "14H30","51C-25896"));
            arrTrip.add(new Trip("TR00023", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"10H30", "15H30","51C-25896"));
            arrTrip.add(new Trip("TR00024", "NHA TRANG", "HUE",  Trip.stringToDate("2021-10-30"),"11H30", "16H30","51C-25896"));

            arrTrip.add(new Trip("TR00025", "TP HCM", "HA NOI", Trip.stringToDate("2021-10-1"),"1H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00026", "HA NOI", "TP HCM",  Trip.stringToDate("2021-10-2"),"14H30", "18H30","51C-25896"));
            arrTrip.add(new Trip("TR00027", "KHANH HOA", "TP HCM", Trip.stringToDate("2021-10-3"),"7H40", "10H40","51C-25896"));
            arrTrip.add(new Trip("TR00028", "TP HCM", "KHANH HOA", Trip.stringToDate("2021-10-4"),"7H50", "10H45","51C-25896"));
            arrTrip.add(new Trip("TR00029", "TP HCM", "HA NOI",  Trip.stringToDate("2021-10-5"),"2H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00030", "HA NOI", "TP HCM",  Trip.stringToDate("2021-1-6"),"15H30", "18H30","51C-25896"));
            arrTrip.add(new Trip("TR00031", "KHANH HOA", "TP HCM",  Trip.stringToDate("2021-1-7"),"8H40", "10H40","51C-25896"));
            arrTrip.add(new Trip("TR00033", "TP HCM", "KHANH HOA",Trip.stringToDate("2021-10-8"),"8H50", "10H45","51C-25896"));
            arrTrip.add(new Trip("TR00034", "TP HCM", "HA NOI",  Trip.stringToDate("2021-10-9"),"3H30", "11H30","51C-25896"));
            arrTrip.add(new Trip("TR00035", "HA NOI", "TP HCM",  Trip.stringToDate("2021-10-10"),"9H30", "18H30","51C-25896"));
            arrTrip.add(new Trip("TR00036", "KHANH HOA", "TP HCM",  Trip.stringToDate("2021-10-11"),"1H40", "10H40","51C-25896"));
            arrTrip.add(new Trip("TR00037", "TP HCM", "KHANH HOA", Trip.stringToDate("2021-10-12"),"4H50", "10H45","51C-25896"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Trip x:arrTrip) {
            mdata.child("Trip").child(x.getIdtrip()).setValue(x);
        }

*/
        mdata.child("Trip").addChildEventListener(new ChildEventListener() {
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