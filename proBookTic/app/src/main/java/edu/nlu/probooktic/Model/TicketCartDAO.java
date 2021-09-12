package edu.nlu.probooktic.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class TicketCartDAO {
    public static final String ADD="ADD";
    public static final String REMOVE="REMOVE";

    public static void updateCart(String idTic,String status ) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("ticketCart");
        switch (status) {

            case "REMOVE":
                myRef.child(idTic).removeValue();
                break;

            default:
                break;
        }
    }
    public static void addToCart(TicketCartModel t ) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("ticketCart");
        myRef.child(t.getIdTicket()).setValue(t);


    }
    public static void writeToDB(ArrayList<TicketCartModel> arr) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        for (TicketCartModel tcm : arr) {
            mDatabase.child("ticketCart").child(tcm.getIdTicket()).setValue(tcm);

        }

    }
}
