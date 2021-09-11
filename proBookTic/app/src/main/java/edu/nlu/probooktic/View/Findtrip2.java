package edu.nlu.probooktic.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;
import edu.nlu.probooktic.View.TripsAdapter;

import static android.content.ContentValues.TAG;


public class Findtrip2 extends AppCompatActivity {

    ListView lVtrip;
    ArrayList<Trip> arTrip;
    TripsAdapter adapter;
    TextView tVdiemKhoihanh, tVdiemKetthuc, tVngay;
    String[] ssdmy;
    Button buttonPre;
    Button buttonNext;
    public static Trip tripPri=null;

    private DatabaseReference mdata;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_findtrip2);

        lVtrip = (ListView) findViewById(R.id.listViewTrips);
        tVdiemKhoihanh = (TextView) findViewById(R.id.tVdp);
        tVdiemKetthuc = (TextView) findViewById(R.id.tVdiemden);
        tVngay = (TextView) findViewById(R.id.tVngay);
        buttonPre = (Button) findViewById(R.id.buttonPre);
        buttonNext=(Button) findViewById(R.id.button2);
        //truyen du lieu tu man hinh 1 qua man hinh 2
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String diemKhoihanh = bundle.getString("txtdiemKhoihanh");
        Log.e(TAG, diemKhoihanh+"22222");
        String diemKetthuc = bundle.getString("txtdiemKetthuc");
        Log.e(TAG, diemKetthuc);
        String date = bundle.getString("txtNgay");
        Log.e(TAG, date);
        ssdmy = date.split("/");
        tVdiemKhoihanh.setText(diemKhoihanh);
        tVdiemKhoihanh.setTextColor(Color.parseColor("#008EFF"));
        tVdiemKetthuc.setText(diemKetthuc);
        tVdiemKetthuc.setTextColor(Color.parseColor("#008EFF"));
        tVngay.setText(date);


        arTrip = new ArrayList<Trip>();

        adapter = new TripsAdapter(this, R.layout.list_trip, arTrip);
        lVtrip.setAdapter(adapter);


        //lay du lieu tu firebase
        mdata = FirebaseDatabase.getInstance().getReference();


        mdata.child("Trip").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Trip chuyendi = snapshot.getValue(Trip.class);
                searchTrip(diemKhoihanh, diemKetthuc, chuyendi, date);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhDau = new Intent(Findtrip2.this, Findtrip1.class);
                startActivity(mhDau);
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(tripPri==null){
                    Toast.makeText(Findtrip2.this, "Vui lòng chọn 1 vé!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent1 = new Intent(Findtrip2.this, ChooseSeat.class);
                    intent1.putExtra("trip", tripPri);
                    startActivity(intent1);
                }
            }
        });
        ////////////////////////
        lVtrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TripsAdapter.mSelectedItem = position;
                tripPri=(Trip)adapter.getItem(position);
                adapter.notifyDataSetChanged();
            }
        });

        ///////////////////////
    }

    //ham tim kiem chuyen di theo diem khỏi hanh, diem ket thuc và ngay dat
    public void searchTrip(String diemKhoihanh, String diemKetthuc, Trip chuyendi, String date) {
        String[] splt = date.split("/");
        String date1 = splt[2] + "-" + splt[1] + "-" + splt[0];
        Log.e(TAG, date1);
        Date date2 = null;
        try {
            date2 = Trip.stringToDate(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (chuyendi.getDiemKH().equals(diemKhoihanh) &&
                chuyendi.getDiemKT().equals(diemKetthuc) &&
                chuyendi.getDate().getTime() - date2.getTime() == 0
        ) {
            arTrip.add(chuyendi);
        }
    }

//public void searchTrip(ArrayList<Trip> tripArrayList){
//    for (int i=0;i<arTrip.size();i++){
//        if((arTrip.get(i).getDate().getDay()+"").equals(ssdmy[0].replace("0",""))&&
//                (arTrip.get(i).getDate().getMonth()+"").equals(ssdmy[1].replace("0",""))&&
//                    (arTrip.get(i).getDate().getYear()+"").equals(ssdmy[2])){
//            tripArrayList.add(arTrip.get(i));
//        }
//    }
//}


}