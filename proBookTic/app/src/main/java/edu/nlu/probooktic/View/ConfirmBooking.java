package edu.nlu.probooktic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

public class ConfirmBooking extends AppCompatActivity {
    TextView card = null;
    TextView expire = null;
    TextView cvv = null;
    TextView nameOnCard = null;
    TextView num_Tic = null;
    TextView num_Seat = null;
    TextView total =null;
    TextView beginHour =null;
    TextView endHour =null;
    TextView locateBegin =null;
    TextView locateEnd =null;

    ArrayList<TicketCartModel> listTic = null;//Kiểm tra lấy từ đâu? thanh toán hay info....
    boolean startFromCart=Pay.startFromCart;//false=chooset, true=gio hang ////Kiểm tra lấy list vé từ đâu? thanh toán hay info....
    Trip trip = null;
    ArrayList<String> listSeat = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);

        getWidget();
        if(startFromCart){
            trip= TicketCart.trip;
            listSeat=TicketCartModel.getListSeat(TicketCart.listTicChooseToPay);
            listTic = TicketCart.listTicChooseToPay;
        }else {
            trip = ChooseSeat.trip;
            listSeat = ChooseSeat.listSeatChoosed;
            listTic = Info_Cus_Get_Ticket.listProInCart;
        }


        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!= null){
            card.setText(formatNumCard(bundle.getString("CARD")));
            expire.setText(bundle.getString("EXPIRE"));
            cvv.setText(bundle.getString("CVV"));
            nameOnCard.setText(bundle.getString("NAME_ON_CARD").toUpperCase());
        }
        num_Tic.setText("Tổng số vé: "+listSeat.size());
        num_Seat.setText("Vị trí ghế: "+stringFromArrList(listSeat));
        total.setText(formatCurrency(getTotal())+" VND");
        beginHour.setText(trip.getTgKH().trim());
        endHour.setText(trip.getTgKT().trim());
        locateBegin.setText(trip.getDiemKH().trim());
        locateEnd.setText(trip.getDiemKT().trim());

        Button button = (Button)findViewById(R.id.btn_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cvv.getText().toString().equalsIgnoreCase("visa")) {
                    Intent intent1 = new Intent(ConfirmBooking.this, PaymentSuccess.class);
                    startActivity(intent1);
                }else {
                    Intent intent2 = new Intent(ConfirmBooking.this, FailedPayment.class);
                    startActivity(intent2);
                }
            }
        });
    }
    public void getWidget(){
        card = (TextView) findViewById(R.id.txt_numberCard);
        expire = (TextView) findViewById(R.id.txt_Expire);
        cvv = (TextView) findViewById(R.id.txt_nameCard);
        nameOnCard = (TextView) findViewById(R.id.txt_NameOnCard);
        num_Seat = (TextView) findViewById(R.id.txt_num_Seat);
        num_Tic = (TextView) findViewById(R.id.txt_num_Tic);
        total=(TextView) findViewById(R.id.txtPriceTicket);
        beginHour=(TextView) findViewById(R.id.txt_HourBegin);
        endHour=(TextView) findViewById(R.id.txt_HourEnd);
        locateBegin=(TextView) findViewById(R.id.txt_LocalBegin);
        locateEnd=(TextView) findViewById(R.id.txt_LocalEnd);
    }

    public String formatNumCard(String s) {
       char[] c = s.toCharArray();
       StringBuilder stringBuilder = new StringBuilder();
       for (int i=0;i<c.length;i++){
           int y=i+1;
           if (y%4==0)
               stringBuilder.append(c[i]+" ");
           else
               stringBuilder.append(c[i]);
        }
       return stringBuilder.toString();
    }
    public long getTotal(){
        long re=0;
        for (TicketCartModel t: listTic) {
            re+=t.getPrice();
        }
        return re;
    }
    public String stringFromArrList(ArrayList<String> a) {
        String re = "";
        for (int i = 0; i < a.size(); i++) {
            if (i == a.size() - 1)
                re += a.get(i);
            else
                re += a.get(i) + ", ";
        }
        return re;
    }
    public String formatCurrency(long s) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        return vn.format(s);
    }

}