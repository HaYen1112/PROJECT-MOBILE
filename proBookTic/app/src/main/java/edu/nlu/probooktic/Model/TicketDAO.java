package edu.nlu.probooktic.Model;

import android.app.DownloadManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class TicketDAO {

    public static final String BOOKED_TICKET="BOOKED";
    public static final String AVAILABLE_TICKET="AVAILABLE";
    public static final String RESERVED_TICKET="RESERVED";
    public static final String RESERVED_TICKET_TIME="RESERVED_TIME";
    public static void updateSeat(String idTic,String status ) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("ticket");
        switch (status) {
            case "BOOKED":
                myRef.child(idTic).child("status").setValue("Đã bán");
                break;
            case "AVAILABLE":
                myRef.child(idTic).child("status").setValue("Trống");
                break;
            case "RESERVED":
                myRef.child(idTic).child("status").setValue("Đang bán");
                break;
            case "RESERVED_TIME":
                myRef.child(idTic).child("isPayment").setValue(dateExpire());
                break;
            default:
                break;
        }
    }
     ArrayList<Ticket> list= new ArrayList<>();

    public  ArrayList<Ticket> getTicket(String idTrip){

         list.clear();
        Query query = FirebaseDatabase.getInstance().getReference("ticket")
                .orderByChild("idTrip")
                .equalTo(idTrip);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Ticket tic = snapshot.getValue(Ticket.class);
                            if (list.add(tic))
                                Log.d(TAG, "them dc" + list.size());
                            else Log.d(TAG, "them ko dc");
//                        if(snapshot!=null) {
//                            String idTrip = snapshot.child("idTrip").getValue().toString();
//                            String idTic = snapshot.child("idTic").getValue().toString();
//                            String numOfSeat = snapshot.child("numOfSeat").getValue().toString();
//                            long price = Long.parseLong(snapshot.child("price").getValue().toString());
//                            String status = snapshot.child("status").getValue().toString();
//                            String isPayment = snapshot.child("isPayment").getValue().toString();
//                            Ticket tic = new Ticket(idTrip, idTic, numOfSeat, price, status, isPayment);
//                            list.add(tic);
//                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );
        Log.d(TAG,"11"+list.size());
        return list;
    }
    public static String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String s=format.format(date);
        return s;
    }
    public static Date stringToDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        d = format.parse(s.trim());
        return d;
    }
    public static String dateExpire(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d= new Date();
        String s=format.format(new Date(d.getTime()+1200000));
        return s;
    }
}

