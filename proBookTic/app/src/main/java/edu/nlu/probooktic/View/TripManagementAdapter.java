package edu.nlu.probooktic.View;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.List;


import edu.nlu.probooktic.Model.Payment;
import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.Model.TripInfo;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class TripManagementAdapter extends BaseAdapter {

    TextView tvSTT, tvDBD, tvDKT, tvTGBD, tvTGKT;
    private Context context;
    private int layout;
    private List<TripInfo> dstrip;
    Button btnAddTripItem;
    public static String DBD;//cho dialog
    public static String DKT;
    public static String TGBD;
    public static String TGKT;
    public static String BS;
    public static Date date;
    public static boolean  isCreate;
    public static String[]  seat ={"A1","A2","A3","A4",
                                    "B1","B2","B3","B4",
            "C1","C2","C3","C4",
            "D1","D2","D3","D4",
            "E1","E2","E3","E4",
            "F1","F2","F3","F4",
            "G1","G2","G3","G4",
            "H1","H2","H3","H4",
            "I1","I2","I3","I4",
            "J1","J2","J3","J4"};
    private long idTrip=0;
    private long idTic=0;

    public TripManagementAdapter(Context context, int layout, List<TripInfo> dstrip) {
        this.context = context;
        this.layout = layout;
        this.dstrip = dstrip;
    }

    @Override
    public int getCount() {
        return dstrip.size();
    }

    @Override
    public Object getItem(int position) {
        return dstrip.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position+1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);


        //Anh xa
        //tvIDTrip = (TextView) convertView.findViewById(R.id.idTrippp);
        tvSTT = (TextView) convertView.findViewById(R.id.idSTT);
        tvDBD = (TextView) convertView.findViewById(R.id.idDBD);
        tvDKT = (TextView) convertView.findViewById(R.id.idDKT);
        tvTGBD = (TextView) convertView.findViewById(R.id.idTGBD);
        tvTGKT = (TextView) convertView.findViewById(R.id.idTGKT);
        btnAddTripItem = (Button) convertView.findViewById(R.id.btnAddTripItem);

        //gán gtri
        TripInfo trip = dstrip.get(position);
        tvSTT.setText(getItemId(position)+"");
        tvTGBD.setText(trip.getTgKH());
        tvTGKT.setText("- "+trip.getTgKT());
        tvDBD.setText(trip.getDiemKH());
        tvDKT.setText("- "+trip.getDiemKT());

        btnAddTripItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBD=trip.getDiemKH();
                DKT=trip.getDiemKT();
                TGBD=trip.getTgKH();
                TGKT=trip.getTgKT();
                BS=trip.getBienSo();

                TripAddDia.AddTripListener listener = new TripAddDia.AddTripListener(){

                    @Override
                    public void addTrip() {
                    countTrip(new MyCallback() {
                        @Override
                        public void onCallback(String value) {
                            insertTrip(new MyCallback() {
                                @Override
                                public void onCallback(String value) {
                                    countTicket(new MyCallback() {
                                        @Override
                                        public void onCallback(String value) {
                                            insertTicket(new MyCallback() {
                                                @Override
                                                public void onCallback(String value) {
                                                    if(isCreate)
                                                    Toast.makeText(context, "Đã thêm chuyến đi và vé", Toast.LENGTH_LONG).show();
                                                    else Toast.makeText(context, "Đã thêm chuyến đi", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                    }


                };
                final TripAddDia dialog = new TripAddDia(context, listener);
                dialog.show();

            }
        });
        //tvIDTrip.setText(trip.getIdtrip());



        if (position%2==0){
            convertView.setBackgroundColor(Color.parseColor("#7CDADBDD"));
        }
        return convertView;

    }
    public void countTrip( MyCallback myCallback){
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idTrip=0;
                if (dataSnapshot.exists()) {
                    idTrip=  dataSnapshot.getChildrenCount();
                    myCallback.onCallback("finish");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference myDatabase2 = FirebaseDatabase.getInstance().getReference("Trip");
        myDatabase2.addListenerForSingleValueEvent(valueEventListener);

    }
    public void countTicket(MyCallback myCallback){
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                idTic=0;
                if (dataSnapshot.exists()) {
                    idTic=  dataSnapshot.getChildrenCount();
                    myCallback.onCallback("finish");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference myDatabase2 = FirebaseDatabase.getInstance().getReference("ticket");
        myDatabase2.addListenerForSingleValueEvent(valueEventListener);

    }
    public void insertTrip( MyCallback myCallback){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Trip");
        idTrip++;
            Trip trip = new Trip("TR000"+idTrip,DBD,DKT,date,TGBD,TGKT,BS);
            mDatabase.child("TR000"+idTrip).setValue(trip);

        myCallback.onCallback("finish");
    }
    public void insertTicket(MyCallback myCallback){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("ticket");

        for (String seat:seat) {
            idTic++;
            Ticket tic= new Ticket("TR000"+idTrip,"IDVE0"+idTic,seat,250000,"Trống","null");
            mDatabase.child("IDVE0"+idTic).setValue(tic);

        }
        myCallback.onCallback("finish");
    }
    public interface MyCallback {
        void onCallback(String value);
    }
}
