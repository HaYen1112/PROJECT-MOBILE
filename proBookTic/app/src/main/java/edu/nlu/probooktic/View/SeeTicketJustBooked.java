package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketForm;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class SeeTicketJustBooked extends AppCompatActivity {
    ViewGroup layout;
    public  ArrayList<TicketForm> listTicBooked=new ArrayList<>();
    public  ArrayList<String> listIdTic=new ArrayList<>();
    String idPayment="pm"+PaymentSuccess.idPay;
    String nameOwner="";
    String numCar =null;
    Trip trip = null;
    String idTic;
    String dataTicForm="";
    String dataTic="";
    FirebaseDatabase mDatabase ;
    DatabaseReference myRef ;
    Query query;
    long widthScreen=0;
    long heightScreen=0;

    TextView beginHour =null;
    TextView endHour =null;
    TextView locateBegin =null;
    TextView locateEnd =null;
    boolean startFromCart=Pay.startFromCart;//false=chooset, true=gio hang ////Kiểm tra lấy list vé từ đâu? thanh toán hay info....
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_ticket_just_booked);

        //da man hinh/////////////////////////
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        widthScreen = size.x;
        heightScreen = size.y;



        //seatGaping_LT = (int)width/108;


        //////////////////////////////

        if(startFromCart){
            trip= TicketCart.trip;

        }else {
            trip = ChooseSeat.trip;

        }
        numCar =ChooseSeat.trip.getBienSo();
        //TRIP
        getWidget();
        beginHour.setText(trip.getTgKH().trim());
        endHour.setText(trip.getTgKT().trim());
        locateBegin.setText(trip.getDiemKH().trim());
        locateEnd.setText(trip.getDiemKT().trim());

        //TICKET
         mDatabase = FirebaseDatabase.getInstance();
         myRef = mDatabase.getReference("ticket");

         readData1(new MyCallback() {
             @Override
             public void onCallback(String value) {
                 dataTicForm=value;
                 Log.e(TAG,dataTicForm);
                 readData2(new MyCallback() {
                     @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                     @Override
                     public void onCallback(String value) {
                         dataTic=value;
                         Log.e(TAG,dataTic);
                         creatListTic(dataTic,dataTicForm);
                         showJustTicBooked();
                     }
                 });
             }
         });

         Button button = (Button)findViewById(R.id.btn_backHome);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 
             }
         });

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showJustTicBooked(){
        layout =findViewById(R.id.layout_seeJustTic);
        LinearLayout layoutSJTic = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSJTic.setOrientation(LinearLayout.VERTICAL);
        layoutSJTic.setLayoutParams(params);
        layout.addView(layoutSJTic);


        LinearLayout layout_main =null;
        LinearLayout layout_InfoTic=null;
        LinearLayout layout_NumCar=null;
        LinearLayout layout_NumSeat=null;
        LinearLayout layout_Price=null;
        LinearLayout layout_IdTic=null;
        int countID=0;
        for (int i =0;i<listTicBooked.size();i++) {

            //Layout main
            LinearLayout.LayoutParams params_main = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_main.setMargins(getWidth(3*10),0,getWidth(3*10),getHeight(50));
            layout_main= new LinearLayout(this);
            layout_main.setLayoutParams(params_main);
            //layout_main.setGravity(Gravity.CENTER_HORIZONTAL);
            layout_main.setBackgroundResource(R.drawable.subtraction_1);
            layout_main.setOrientation(LinearLayout.VERTICAL);
            layoutSJTic.addView(layout_main);

            //Ten Chu ve
            TextView tVLbName = new TextView(this);
            LinearLayout.LayoutParams params_1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_1.setMargins(getWidth(3*25),getHeight(3*20),0,0);

            tVLbName.setLayoutParams(params_1);
            tVLbName.setText("Hành khách");
            tVLbName.setTextColor(Color.BLACK);
            tVLbName.setTextSize(15);
            tVLbName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_main.addView(tVLbName);

            //Ten Chu ve
            TextView tName = new TextView(this);
            LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_2.setMargins(getWidth(3*25),0,0,0);
            tName.setLayoutParams(params_2);
            tName.setText(listTicBooked.get(i).getNameOwner());
            tName.setTextColor(Color.parseColor("#244CBA"));
            tName.setTextSize(22);
            tName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_main.addView(tName);

            //Layout Information Ticket

            LinearLayout.LayoutParams params_InfoTic = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_InfoTic.setMargins(getWidth(3*25),getHeight(50),getWidth(3*25),getHeight(80));
            layout_InfoTic= new LinearLayout(this);
            layout_InfoTic.setLayoutParams(params_InfoTic);
            layout_InfoTic.setOrientation(LinearLayout.HORIZONTAL);
            layout_main.addView(layout_InfoTic);
            //Layout num Car
            LinearLayout.LayoutParams params_numCar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params_numCar.setMargins(0,0,getWidth(3*10),0);
            layout_NumCar= new LinearLayout(this);
            layout_NumCar.setLayoutParams(params_numCar);
            layout_NumCar.setOrientation(LinearLayout.VERTICAL);
            layout_InfoTic.addView(layout_NumCar);
            //So Xe
            TextView tVLbNumCar = new TextView(this);
            LinearLayout.LayoutParams params_LNC = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_LNC.setMargins(0,0,0,0);

            tVLbNumCar.setLayoutParams(params_LNC);
            tVLbNumCar.setText("Số xe");
            tVLbNumCar.setTextColor(Color.BLACK);
            tVLbNumCar.setTextSize(15);
            tVLbNumCar.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_NumCar.addView(tVLbNumCar);

            //So XE
            TextView tNumCar = new TextView(this);
            LinearLayout.LayoutParams params_TNC = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_TNC.setMargins(0,getHeight(5*3),0,0);
            tNumCar.setLayoutParams(params_TNC);
            tNumCar.setText(listTicBooked.get(i).getNumCar());
            tNumCar.setTextColor(Color.parseColor("#244CBA"));
            tNumCar.setTextSize(22);
            tNumCar.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_NumCar.addView(tNumCar);

            //Layout num Seat
            LinearLayout.LayoutParams params_numSeat = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params_numSeat.setMargins(0,0,getWidth(3*30),0);
            layout_NumSeat= new LinearLayout(this);
            layout_NumSeat.setLayoutParams(params_numSeat);
            layout_NumSeat.setOrientation(LinearLayout.VERTICAL);
            layout_InfoTic.addView(layout_NumSeat);
            //So Ghế
            TextView tVLbNumSeat = new TextView(this);
            LinearLayout.LayoutParams params_LNS = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_LNS.setMargins(getWidth(10*3),0,0,0);

            tVLbNumSeat.setLayoutParams(params_LNS);
            tVLbNumSeat.setText("Số ghế");
            tVLbNumSeat.setTextColor(Color.BLACK);
            tVLbNumSeat.setTextSize(15);
            tVLbNumSeat.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_NumSeat.addView(tVLbNumSeat);

            //So Ghe
            TextView tNumSeat = new TextView(this);
            LinearLayout.LayoutParams params_TNS = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_TNS.setMargins(getWidth(10*3),getHeight(5*3),0,0);
            tNumSeat.setLayoutParams(params_TNS);
            tNumSeat.setText(listTicBooked.get(i).getNumSeat());
            tNumSeat.setTextColor(Color.parseColor("#244CBA"));
            tNumSeat.setTextSize(22);
            tNumSeat.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_NumSeat.addView(tNumSeat);

            //Layout price
            LinearLayout.LayoutParams params_price = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params_price.setMargins(0,0,0,0);
            layout_Price= new LinearLayout(this);
            layout_Price.setLayoutParams(params_price);
            layout_Price.setOrientation(LinearLayout.VERTICAL);
            layout_InfoTic.addView(layout_Price);
            //So Ghế
            TextView tVLbPrice = new TextView(this);
            LinearLayout.LayoutParams params_LP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_LP.setMargins(getWidth(10*3),0,0,0);

            tVLbPrice.setLayoutParams(params_LP);
            tVLbPrice.setText("Giá");
            tVLbPrice.setTextColor(Color.BLACK);
            tVLbPrice.setTextSize(15);
            tVLbPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_Price.addView(tVLbPrice);

            //So Ghe
            TextView tPrice = new TextView(this);
            LinearLayout.LayoutParams params_TP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_TP.setMargins(getWidth(10*3),getHeight(10*3),0,0);
            tPrice.setLayoutParams(params_TP);
            tPrice.setText(formatCurrency(listTicBooked.get(i).getPrice())+"đ");
            tPrice.setTextColor(Color.parseColor("#244CBA"));
            tPrice.setTextSize(22);
            tPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_Price.addView(tPrice);
            //
            //Layout ID Ticket

            LinearLayout.LayoutParams params_IDTic = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_IDTic.setMargins(getWidth(3*25),0,getWidth(3*25),0);

            layout_IdTic= new LinearLayout(this);
            layout_IdTic.setLayoutParams(params_IDTic);
            layout_IdTic.setOrientation(LinearLayout.HORIZONTAL);
            layout_IdTic.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            layout_IdTic.setGravity(Gravity.CENTER_HORIZONTAL);
            layout_main.addView(layout_IdTic);
            //MA VE
            TextView tVLbIdTic= new TextView(this);
            LinearLayout.LayoutParams params_LIT = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_LIT.setMargins(getWidth(25*3),getHeight(20*3),getWidth(3*10),0);

            tVLbIdTic.setLayoutParams(params_LIT);
            tVLbIdTic.setText("Mã vé");
            tVLbIdTic.setTextColor(Color.BLACK);
            tVLbIdTic.setTextSize(15);
            tVLbIdTic.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_IdTic.addView(tVLbIdTic);

            //MA VE
            TextView tIdTic = new TextView(this);
            LinearLayout.LayoutParams params_TIT = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_TIT.setMargins(0,getHeight(20*3),0,0);
            tIdTic.setLayoutParams(params_TIT);
            tIdTic.setText(listTicBooked.get(i).getIdTicket());
            tIdTic.setTextColor(Color.parseColor("#244CBA"));
            tIdTic.setTextSize(22);
            tIdTic.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            layout_IdTic.addView(tIdTic);
            //

        }
    }
    //
    public void creatListTic(String dataTic,String dataTicForm){

        String data="";

        int count=0;
        StringTokenizer tokenizer0 = new StringTokenizer(dataTic,"/");
        StringTokenizer tokenizer1 = new StringTokenizer(dataTicForm,"/");
        count=tokenizer0.countTokens();
        for (int i=0;i<count;i++){
            data=tokenizer1.nextToken()+"\t"+numCar+"\t"+tokenizer0.nextToken();

            TicketForm t = creatTicFormObject(data);
            listTicBooked.add(t);

        }
    }
    public TicketForm creatTicFormObject(String data){

        StringTokenizer tokenizer = new StringTokenizer(data,"\t");
        String idTic = tokenizer.nextToken();

        String name = tokenizer.nextToken();

        String numCar = tokenizer.nextToken();

        String numSeat = tokenizer.nextToken();

        String price = tokenizer.nextToken();

        TicketForm n = new TicketForm(idTic,name,numCar,numSeat,Long.parseLong(price));
        return n;
    }
    //
    public interface MyCallback {
        void onCallback(String value);
    }
    public void readData1(MyCallback myCallback) {

        ValueEventListener valueEventListener =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTicBooked.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        idTic = snapshot.getKey().toString();
                        nameOwner=snapshot.child("nameOwner").getValue().toString();
                        dataTicForm+=idTic+"\t"+nameOwner+"/";
                        listIdTic.add(idTic);
                        Log.e(TAG,idTic);
                    }
                    myCallback.onCallback(dataTicForm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query = FirebaseDatabase.getInstance().getReference("ticketForm")
                .orderByChild("idPayment")
                .equalTo(idPayment);
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    public void readData2(MyCallback myCallback) {
        ValueEventListener valueEventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                for (DataSnapshot snap : dsnapshot.getChildren()) {
                    if (listIdTic.contains(snap.getKey().toString())) {
                       dataTic+=snap.child("numOfSeat").getValue().toString()+"\t"+snap.child("price").getValue().toString()+"/";
                    }
                }
                myCallback.onCallback(dataTic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };


        myRef.addListenerForSingleValueEvent(valueEventListener);
    }
    //
    public String formatCurrency(long s) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        return vn.format(s);
    }
    public void getWidget(){

        beginHour=(TextView) findViewById(R.id.txt_HourBeginAtSeeTicket);
        endHour=(TextView) findViewById(R.id.txt_HourEndAtSeeTicket);
        locateBegin=(TextView) findViewById(R.id.txt_LocalBeginAtSeeTicket);
        locateEnd=(TextView) findViewById(R.id.txt_LocalEndAtSeeTicket);
    }
    //ho tro chuyên da man hinh chuyển từ màn hình 1080x2200 sang các mh khác
    public int getWidth(int w){
        return (int)(w*widthScreen/1080);
    }

    public int getHeight(int h){
        return (int)(h*heightScreen/2220);
    }
}




