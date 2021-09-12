package edu.nlu.probooktic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartDAO;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.R;

public class FailedPayment extends AppCompatActivity {
    boolean startFromCart=Pay.startFromCart; ////Kiểm tra lấy list vé từ đâu? thanh toán hay info....
    public static ArrayList<TicketCartModel> listTicChoosed = new ArrayList<TicketCartModel>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_payment);

        if (!startFromCart){
            listTicChoosed=Info_Cus_Get_Ticket.listProInCart;
            saveTicketInCart(listTicChoosed);
        }
        Button button =(Button)findViewById(R.id.btn_seeTicketCart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailedPayment.this, TicketCart.class);
                startActivity(intent);
            }
        });
    }
    public void saveTicketInCart(ArrayList<TicketCartModel> arr){
        //lưu vào giỏ
               for (TicketCartModel tm: arr) {
            tm.setExpirePayment(TicketDAO.dateExpire());
        }
        TicketCartDAO.writeToDB(arr);
        //Lưu vào vé để cập nhật trạng thái của vé về thời gian giữ chỗ
        ArrayList<Ticket> arr1 = Info_Cus_Get_Ticket.listTicChoosed;
        for (Ticket t: arr1) {
            TicketDAO.updateSeat(t.getIdTic(),TicketDAO.RESERVED_TICKET_TIME);
        }
    }
}