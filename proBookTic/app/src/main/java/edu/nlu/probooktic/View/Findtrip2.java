package edu.nlu.probooktic.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;
import edu.nlu.probooktic.View.TripsAdapter;


public class Findtrip2 extends AppCompatActivity {

    ListView lVtrip;
    ArrayList<Trip> arTrip;
    TripsAdapter adapter;
    TextView tVdiemKhoihanh, tVdiemKetthuc,tVngay;
    String[] ssdmy;
    Button buttonPre;

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

        //truyen du lieu tu man hinh 1 qua man hinh 2
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String diemKhoihanh = bundle.getString("txtdiemKhoihanh");
        String diemKetthuc = bundle.getString("txtdiemKetthuc");
        String date = bundle.getString("txtNgay");
        ssdmy = date.split("/");
        tVdiemKhoihanh.setText(diemKhoihanh);
        tVdiemKhoihanh.setTextColor(Color.parseColor("#008EFF"));
        tVdiemKetthuc.setText(diemKetthuc);
        tVdiemKetthuc.setTextColor(Color.parseColor("#008EFF"));
        tVngay.setText(date);


        arTrip =  new ArrayList<Trip>();

        adapter = new TripsAdapter(this, R.layout.list_trip,arTrip);
        lVtrip.setAdapter(adapter);

        //lay du lieu tu firebase
       mdata = FirebaseDatabase.getInstance().getReference();


               mdata.child("ChuyenDi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot,String previousChildName) {
                Trip chuyendi = snapshot.getValue(Trip.class);
                searchTrip(diemKhoihanh,diemKetthuc,chuyendi,date);
                adapter.notifyDataSetChanged();
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

        buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent mhDau = new Intent(Findtrip2.this,Findtrip1.class);
                    startActivity(mhDau);
            }
        });


    }

    //ham tim kiem chuyen di theo diem khỏi hanh, diem ket thuc và ngay dat
    public void searchTrip(String diemKhoihanh,String diemKetthuc,Trip chuyendi,String date){
        String[] splt = date.split("/");
        for(int i = 0;i<splt.length;i++){
            if(splt[i].charAt(0)=='0') {
                splt[i] = splt[i].replace("0", "");
            }
        }
        if(chuyendi.getDiemKH().equals(diemKhoihanh)&&
                chuyendi.getDiemKT().equals(diemKetthuc)&&
                (chuyendi.getDate().getDay()+"").equals(splt[0])&&
                (chuyendi.getDate().getMonth()+"").equals(splt[1])&&
                (chuyendi.getDate().getYear()+"").equals(splt[2])){
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