package edu.nlu.probooktic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;
import edu.nlu.probooktic.View.TripsAdapter;

public class Findtrip3 extends AppCompatActivity {

    ListView lVtripNgaydi,lVtripNgayVe;
    ArrayList<Trip> arTripNgaydi,arTripNgayve;
    TripsAdapter adapterNgaydi,adapterNgayve;
    TextView tVdiemKhoihanh1, tVdiemKetthuc1,tVdiemKhoihanh2, tVdiemKetthuc2,tVngaydi,tVngayve;
    String[] ssdmy;
    Button buttonPre;

    private DatabaseReference mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtrip3);


        lVtripNgaydi = (ListView) findViewById(R.id.listViewTrips);
        lVtripNgayVe = (ListView) findViewById(R.id.listViewTrips1);
        tVdiemKhoihanh1 = (TextView) findViewById(R.id.tVdp);
        tVdiemKetthuc1 = (TextView) findViewById(R.id.tVdiemden);
        tVdiemKhoihanh2 = (TextView) findViewById(R.id.tVdpdiemdi);
        tVdiemKetthuc2 = (TextView) findViewById(R.id.tVdiemden2);
        tVngaydi = (TextView) findViewById(R.id.tVngay);
        tVngayve = (TextView) findViewById((R.id.tVngayve));
        buttonPre = (Button) findViewById(R.id.buttonPre);

        //truyen du lieu tu man hinh 1 qua man hinh 2
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dulieu");
        String diemKhoihanh = bundle.getString("txtdiemKhoihanh");
        String diemKetthuc = bundle.getString("txtdiemKetthuc");
        String date = bundle.getString("txtNgay");
        String dateReturn = bundle.getString("txtNgayve");
        ssdmy = date.split("/");

        tVdiemKhoihanh1.setText(diemKhoihanh);
        tVdiemKhoihanh1.setTextColor(Color.parseColor("#244CBA"));
        tVdiemKetthuc1.setText(diemKetthuc);
        tVdiemKetthuc1.setTextColor(Color.parseColor("#244CBA"));
        tVdiemKhoihanh2.setText(diemKetthuc);
        tVdiemKhoihanh2.setTextColor(Color.parseColor("#244CBA"));
        tVdiemKetthuc2.setText(diemKhoihanh);
        tVdiemKetthuc2.setTextColor(Color.parseColor("#244CBA"));
        tVngaydi.setText(date);
        tVngayve.setText(dateReturn);//


        arTripNgaydi =  new ArrayList<Trip>();

        adapterNgaydi = new TripsAdapter(this, R.layout.list_trip,arTripNgaydi);
        lVtripNgaydi.setAdapter(adapterNgaydi);

        arTripNgayve =  new ArrayList<Trip>();

        adapterNgayve = new TripsAdapter(this, R.layout.list_trip,arTripNgayve);
        lVtripNgayVe.setAdapter(adapterNgayve);

        //lay du lieu tu firebase
        mdata = FirebaseDatabase.getInstance().getReference();

        mdata.child("ChuyenDi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                Trip chuyendi = snapshot.getValue(Trip.class);
                searchTrip(diemKhoihanh,diemKetthuc,chuyendi,arTripNgaydi,date);
                searchTrip(diemKetthuc,diemKhoihanh,chuyendi,arTripNgayve,dateReturn);
                adapterNgaydi.notifyDataSetChanged();
                adapterNgayve.notifyDataSetChanged();
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
                Intent mhDau = new Intent(Findtrip3.this,Findtrip1.class);
                startActivity(mhDau);
            }
        });
    }

    //ham tim kiem chuyen di theo diem khỏi hanh, diem ket thuc và ngay dat
    public void searchTrip(String diemKhoihanh,String diemKetthuc,Trip chuyendi,ArrayList<Trip> arTrip, String date){
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

}