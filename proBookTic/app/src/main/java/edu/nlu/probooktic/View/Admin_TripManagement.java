package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.nlu.probooktic.Admin.TrangChuAdmin;
import edu.nlu.probooktic.Model.TripInfo;
import edu.nlu.probooktic.R;

public class Admin_TripManagement extends AppCompatActivity {
    public static  ArrayList<TripInfo> arrTripInf;
    private Button btnAddTrIn, btnAddTripAll;
    private ListView lvTripInfo;
    public static String test="No";
    ImageView imageView;
    String name;
    public  static TripManagementAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__trip_management);
        imageView = findViewById(R.id.home_chuyendi);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Admin_TripManagement.this, TrangChuAdmin.class);
                intent1.putExtra("name", name);
                startActivity(intent1);
            }
        });

        btnAddTrIn = (Button)findViewById(R.id.buttonAddTrIn);
        btnAddTripAll = (Button)findViewById(R.id.buttonAddTripAll);
        lvTripInfo = (ListView)findViewById(R.id.listViewTripInfo);
        arrTripInf = new ArrayList<TripInfo>();
        btnAddTrIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_TripManagement.this,AddTripInfomation.class);
                startActivity(intent);
            }
        });
        adapter = new TripManagementAdapter(this, R.layout.trip_item_for_list, arrTripInf);
        lvTripInfo.setAdapter(adapter);


        Query query = FirebaseDatabase.getInstance().getReference("TripInfomation");

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrTripInf.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TripInfo tic = snapshot.getValue(TripInfo.class);
                        arrTripInf.add(tic);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}