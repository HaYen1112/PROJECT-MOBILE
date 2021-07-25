package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import edu.nlu.probooktic.Database.TicketFormDB;
import edu.nlu.probooktic.Model.Payment;
import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartDAO;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.R;

public class PaymentSuccess extends AppCompatActivity {

    boolean startFromCart=Pay.startFromCart; ////Kiểm tra lấy list vé từ đâu? thanh toán hay info....
    public static ArrayList<TicketCartModel> listTicChoosed=new ArrayList<>();
    DatabaseReference mDatabase ;
    DatabaseReference myDatabase1 ;
    DatabaseReference myDatabase2 ;

    public static long idPay=0;
    String idCus="C001";
    String nameCus="Nguyễn Thành Nam";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);



        if (startFromCart){
            listTicChoosed=TicketCart.listTicChooseToPay;

        }else {
            listTicChoosed=Info_Cus_Get_Ticket.listProInCart;
        }
        //Update trong table TICKET
        for (TicketCartModel t:listTicChoosed) {
            TicketDAO.updateSeat(t.getIdTicket(),TicketDAO.BOOKED_TICKET);
        }
        Button button = (Button)findViewById(R.id.btn_seeTicket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccess.this,SeeTicketJustBooked.class);
                startActivity(intent);
            }
        });
        //Delete in table TicketCart
        if (startFromCart) {
            for (TicketCartModel t:listTicChoosed) {
                TicketCartDAO.updateCart(t.getIdTicket(),TicketCartDAO.REMOVE);
            }

        }
            //INSERT in table Payment and TicketForm
            readSumPayment(new SeeTicketJustBooked.MyCallback() {
                @Override
                public void onCallback(String value) {
                    if (value.equals("finish")){
                        saveToPayment(new SeeTicketJustBooked.MyCallback() {
                            @Override
                            public void onCallback(String value) {
                               saveToTicketForm(new SeeTicketJustBooked.MyCallback() {
                                   @Override
                                   public void onCallback(String value) {

                                   }
                               });
                            }
                        });
                    }
                }
            });

    }
    public void saveToPayment(SeeTicketJustBooked.MyCallback myCallback){
        mDatabase = FirebaseDatabase.getInstance().getReference("payment");
        idPay++;
        for (TicketCartModel tm:listTicChoosed) {

            Payment p = new Payment("pm"+idPay,nameCus,new Date());
            mDatabase.child("pm"+idPay).setValue(p);
            mDatabase.child("pm"+idPay).child("date").setValue(TicketDAO.dateToString(p.getDate()));
        }
        myCallback.onCallback("finish");



    }
    public void saveToTicketForm(SeeTicketJustBooked.MyCallback myCallback){
        myDatabase1 = FirebaseDatabase.getInstance().getReference("ticketForm");
        for (int i=0;i<listTicChoosed.size();i++) {
            TicketCartModel tm =listTicChoosed.get(i);

            TicketFormDB t = new TicketFormDB(tm.getIdTicket(),tm.getNameOwner(),tm.getIdCard(),"pm"+idPay);

            myDatabase1.child(tm.getIdTicket()).setValue(t);
        }
        myCallback.onCallback("finish");

    }
    public void readSumPayment(SeeTicketJustBooked.MyCallback myCallback){
        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               idPay=0;
                if (dataSnapshot.exists()) {
                    idPay=dataSnapshot.getChildrenCount();
                    myCallback.onCallback("finish");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myDatabase2 = FirebaseDatabase.getInstance().getReference("payment");
        myDatabase2.addListenerForSingleValueEvent(valueEventListener);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}