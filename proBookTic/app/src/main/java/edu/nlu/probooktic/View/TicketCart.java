package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartDAO;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class TicketCart extends AppCompatActivity implements View.OnClickListener {

    ViewGroup layout;
    int seatGaping_LT = 10;
    int seatGaping_LL = 10;

    ArrayList<TicketCartModel> listTicChoosed = new ArrayList<>();
    static final int STATUS_NO = 0;
    static final int STATUS_YES = 1;
    static final int REQUEST_NO = 2000;
    int id_BtnKBYT = 0;
    String idCus = "C001";
    public static Trip trip;
    long widthScreen=0;
    long heightScreen=0;
    String name;
    public static ArrayList<TicketCartModel> listTicChooseToPay = new ArrayList<>();

    Button btnCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_cart);


        //da man hinh/////////////////////////
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        long width = size.x;
        long height = size.y;



        widthScreen = size.x;
        heightScreen = size.y;

        seatGaping_LT = getWidth(10);
        seatGaping_LL = getHeight(10);


        //////////////////////////////


        layout = findViewById(R.id.layout_cartTic);


        Query query = FirebaseDatabase.getInstance().getReference("ticketCart")
                .orderByChild("idCus")
                .equalTo(idCus);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTicChoosed.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TicketCartModel tic = snapshot.getValue(TicketCartModel.class);
                        listTicChoosed.add(tic);

                    }
                    for (TicketCartModel tm : listTicChoosed) {

                    }
                    try {
                        showListTicInCart();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void showListTicInCart() throws ParseException {
        LinearLayout layoutGetInfo = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutGetInfo.setOrientation(LinearLayout.VERTICAL);
        layoutGetInfo.setLayoutParams(params);
        layoutGetInfo.setPadding(4 * seatGaping_LT, 4 * seatGaping_LL, 4 * seatGaping_LT, 4 * seatGaping_LL);
        layout.addView(layoutGetInfo);

        LinearLayout layout = null;
        LinearLayout layout1 = null;
        LinearLayout layout2 = null;
        LinearLayout layout3 = null;
        LinearLayout layout4 = null;
        int count = 1;
        for (int i = 0; i < listTicChoosed.size(); i++) {
            int index = i;
            Log.e(TAG, listTicChoosed.get(i).getInfo_trip());
            layout = new LinearLayout(this);
            //layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(R.drawable.info_cus_card);
            layoutGetInfo.addView(layout);

            layout1 = new LinearLayout(this);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(params);
            layout.addView(layout1);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getWidth(500), getHeight(150));
            //layoutParams.setMargins(seatGaping_LT, seatGaping_LT, seatGaping_TB, seatGaping_TB);
            //ID Seat TextView
            TextView view = new TextView(this);

            view.setLayoutParams(layoutParams);
            view.setPadding(0, 0, 0, 0 * seatGaping_LT);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.BLUE);
            view.setText("Mã vé: " + listTicChoosed.get(i).getIdTicket());
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout1.addView(view);
            //Number Seat TextView

            TextView viewNS = new TextView(this);
            viewNS.setLayoutParams(layoutParams);
            viewNS.setPadding(0, 0, 0, 0 * seatGaping_LT);
            viewNS.setGravity(Gravity.CENTER);
            viewNS.setTextColor(Color.BLUE);
            viewNS.setText("Số ghế: " + listTicChoosed.get(i).getNumSeat());
            viewNS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout1.addView(viewNS);
            //ID Info Trip
            TextView viewTrip = new TextView(this);
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams1.setMargins(getWidth(80), 0, 0, 0);
            viewTrip.setLayoutParams(layoutParams1);

            //viewTrip.setGravity(Gravity.CENTER);
            viewTrip.setTextColor(Color.BLUE);
            viewTrip.setText(listTicChoosed.get(i).getInfo_trip() + "\t\t\t\t\tGiá:" + listTicChoosed.get(i).getPrice());
            viewTrip.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout.addView(viewTrip);
            //
            layout2 = new LinearLayout(this);
            layout2.setOrientation(LinearLayout.HORIZONTAL);
            layout2.setLayoutParams(params);
            layout.addView(layout2);
//

            //Họ và tên
            TextView viewName = new TextView(this);
            LinearLayout.LayoutParams layoutParamsTV = new LinearLayout.LayoutParams(getWidth(400), getHeight(100));
            layoutParamsTV.setMargins(0, 0, 0, getHeight(20));
            viewName.setLayoutParams(layoutParamsTV);
            viewName.setPadding(getWidth(100), 0, 0, 0 * seatGaping_LT);
            //viewName.setGravity(Gravity.CENTER);
            viewName.setTextColor(Color.BLACK);
            viewName.setText("Họ và tên: ");
            viewName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout2.addView(viewName);


            EditText edTextName = new EditText(this);
            LinearLayout.LayoutParams layoutParamsETV = new LinearLayout.LayoutParams(getWidth(500), getHeight(150));
            layoutParamsETV.setMargins(0, 0, 0, getHeight(20));
            edTextName.setLayoutParams(layoutParamsETV);
            edTextName.setPadding(0, 0, 0, 0 * seatGaping_LT);
            //edTextName.setGravity(Gravity.CENTER);
            edTextName.setId(count * 63);
            count++;
            edTextName.setBackgroundResource(R.drawable.shadow);
            edTextName.setTextColor(Color.BLACK);
            edTextName.setText(listTicChoosed.get(i).getNameOwner());
            edTextName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout2.addView(edTextName);
            //
            layout3 = new LinearLayout(this);
            layout3.setOrientation(LinearLayout.HORIZONTAL);
            layout3.setLayoutParams(params);
            layout.addView(layout3);
            //CMND
            TextView viewIDP = new TextView(this);

            viewIDP.setLayoutParams(layoutParamsTV);
            viewIDP.setPadding(getWidth(100), 0, 0, 0 * seatGaping_LT);
            //viewIDP.setGravity(Gravity.CENTER);
            viewIDP.setTextColor(Color.BLACK);
            viewIDP.setText("CMND/CCCD: ");
            viewIDP.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout3.addView(viewIDP);


            EditText edTextIDP = new EditText(this);
            edTextIDP.setLayoutParams(layoutParamsETV);
            // edTextIDP.setPadding(0, 0, 0, 0 * seatGaping_LT);
            //edTextIDP.setGravity(Gravity.CENTER);
            edTextIDP.setId(count * 63);
            count++;
            edTextIDP.setBackgroundResource(R.drawable.shadow);
            edTextIDP.setText(listTicChoosed.get(i).getIdCard());
            edTextIDP.setTextColor(Color.BLACK);
            edTextIDP.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout3.addView(edTextIDP);
            //button
            TextView btn = new TextView(this);
            LinearLayout.LayoutParams layoutParamsBtn = new LinearLayout.LayoutParams(getWidth(500), getHeight(100));
            layoutParamsBtn.setMargins(getWidth(250), getHeight(10), getWidth(10), getHeight(20));
            //btn.setPadding(0,0,0,10);
            btn.setTag(STATUS_NO);
            btn.setLayoutParams(layoutParamsBtn);
            btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn.setPaintFlags(btn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            btn.setText("KHAI BÁO Y TẾ");
            btn.setId(count * 63);
            count++;
            btn.setTextColor(Color.BLUE);
            //btn.setBackgroundResource(R.drawable.background_button);
            btn.setOnClickListener(this);
            layout.addView(btn);
//
            //Boder hoti
            TextView t = new TextView(this);
            t.setBackgroundResource(R.drawable.border);
            t.setGravity(Gravity.CENTER_HORIZONTAL);
            LinearLayout.LayoutParams layoutParamsLine = new LinearLayout.LayoutParams(getWidth(900), getHeight(5));
            layoutParamsLine.setMargins(getWidth(50), 0, 0, 0);
            t.setLayoutParams(layoutParamsLine);
            layout.addView(t);
            //
            layout4 = new LinearLayout(this);
            layout4.setOrientation(LinearLayout.HORIZONTAL);
            layout4.setLayoutParams(params);

            layout.addView(layout4);

            //TGian
            TextView viewTime = new TextView(this);
            LinearLayout.LayoutParams layoutParamsTV1 = new LinearLayout.LayoutParams(getWidth(600), getHeight(200));
            viewTime.setLayoutParams(layoutParamsTV1);
            viewTime.setPadding(getWidth(100), 0, 0, 0 * seatGaping_LT);

            viewTime.setGravity(Gravity.CENTER_VERTICAL);
            viewTime.setTextColor(Color.BLACK);
            if (checkTime(listTicChoosed.get(i).getExpirePayment()))
                viewTime.setText("Đã hết hạn giữ vé! ");
            else
                viewTime.setText("Vui lòng thanh toán trước thời hạn: " + listTicChoosed.get(i).getExpirePayment());
            viewTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            layout4.addView(viewTime);

            //
            Button btnPay = new Button(this);
            LinearLayout.LayoutParams layoutParamsBtnPay = new LinearLayout.LayoutParams(getWidth(300), getHeight(100));
            layoutParamsBtnPay.setMargins(0, 0, getWidth(10), getHeight(150));
            //btn.setPadding(0,0,0,10);
            btnPay.setTag(STATUS_NO);
            btnPay.setLayoutParams(layoutParamsBtnPay);
            btnPay.setGravity(Gravity.CENTER);

            btnPay.setTextColor(Color.WHITE);
            btnPay.setBackgroundResource(R.drawable.background_button);
            boolean flag_IsExpire = false;
            if (checkTime(listTicChoosed.get(i).getExpirePayment())) {
                btnPay.setText("Xóa vé");
                flag_IsExpire = true;
            } else {
                btnPay.setText("Thanh toán");
                flag_IsExpire = false;
            }

            boolean finalFlag_IsExpire = flag_IsExpire;
            btnPay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(checkInfo(btn,edTextName,edTextIDP)) {
                        if (finalFlag_IsExpire) {
                            TicketCartDAO.updateCart(listTicChoosed.get(index).getIdTicket(), TicketCartDAO.REMOVE);

                            recreate();
                        } else {
                            try {
                                trip = Trip.toTrip(listTicChoosed.get(index).getInfo_trip());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            listTicChooseToPay.clear();
                            listTicChooseToPay.add(listTicChoosed.get(index));
                            listTicChooseToPay.get(0).setNameOwner(edTextName.getText().toString());
                            listTicChooseToPay.get(0).setIdCard(edTextIDP.getText().toString());

                            Intent intent = new Intent(TicketCart.this, Pay.class);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("FLAG", true);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                }
            });
            layout4.addView(btnPay);
            //cập nhật giá trị khi ng dùng nhập thông tin vào edit Text
//            edTextName.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    listTicChoosed.get(index).setNameOwner(edTextName.getText().toString());
//                }
//            });
//            edTextIDP.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    listTicChoosed.get(index).setIdCard(edTextIDP.getText().toString());
//                }
//            });
        }


    }

    public boolean checkInfo(TextView btn, EditText name, EditText idC) {

        if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(idC.getText().toString())) {
            //Create dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(TicketCart.this);
            builder.setTitle("Thông báo!");
            builder.setMessage("Bạn phải điền thông tin đầy đủ để đặt vé");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
                   /* Toast toast= Toast.makeText(this,
                            "Bạn phải điền thông tin đầy đủ cho tất cả các vé", Toast.LENGTH_SHORT);
                   // toast.setMargin(10,500);
                    toast.show();*/
            return false;
        }
        if ((int) btn.getTag() == 0) {
            //Create dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(TicketCart.this);
            builder.setTitle("Thông báo!");
            builder.setMessage("Bạn phải KBYT để đặt vé");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
                     /*Toast toast= Toast.makeText(getApplicationContext(),
                             "Bạn phải KBYT cho tất cả các vé", Toast.LENGTH_SHORT);
                     //toast.setMargin(10,500);
                     toast.show();*/
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        id_BtnKBYT = view.getId();
        Intent intent = new Intent(TicketCart.this, Kbyt.class);
        startActivityForResult(intent, REQUEST_NO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NO) {
            if (resultCode == Kbyt.RESULT_NO) {
                TextView b = (TextView) findViewById(id_BtnKBYT);
                b.setTag(STATUS_YES);
            }
        }
    }

    public String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(date);
        return s;
    }

    public String dateExpire() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String s = format.format(new Date(d.getTime() + 1800000));
        return s;
    }

    public boolean checkTime(String time) throws ParseException {
        Date d1 = TicketDAO.stringToDate(time);
        Date d2 = new Date();
        if (d2.getTime() - d1.getTime() > 0)
            return true;
        return false;
    }
    //ho tro chuyên da man hinh chuyển từ màn hình 1080x2200 sang các mh khác
    public int getWidth(int w){
        return (int)(w*widthScreen/1080);
    }

    public int getHeight(int h){
        return (int)(h*heightScreen/2220);
    }
}