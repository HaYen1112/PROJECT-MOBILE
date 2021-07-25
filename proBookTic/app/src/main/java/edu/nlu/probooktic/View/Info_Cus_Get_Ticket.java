package edu.nlu.probooktic.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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


import java.util.ArrayList;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class Info_Cus_Get_Ticket extends AppCompatActivity implements  View.OnClickListener{
    ViewGroup layout;
    int seatGaping_LT = 10;
    int seatGaping_TB = 0;
    public static ArrayList<Ticket> listTicChoosed;
    static final int STATUS_NO=0;
    static final int STATUS_YES=1;
    static final int REQUEST_NO=2000;
    int id_BtnKBYT=0;
    long milis=0;
    CountDownTimer cTimer = null;
    Button btnCont;
    String idCus="C001";//hỗ trợ lưu vé vào giỏ vé;
    public static ArrayList<TicketCartModel> listProInCart = new ArrayList<>();//lưu để dùng khi thanh thoán ko thành công
    boolean isDestroy= true;
    Trip trip = ChooseSeat.trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__cus__get__ticket);

        //da man hinh/////////////////////////
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        long width = size.x;
        long height = size.y;



        seatGaping_LT = (int)width/108;


        //////////////////////////////


        layout = findViewById(R.id.layout_cartTic);

        //get the bundle to get list seat choosed
        Intent intent=getIntent();
        Bundle b = intent.getExtras();
        //getting the arraylist from the key
        if(b!=null){
            listTicChoosed = (ArrayList<Ticket>) b.getSerializable(ChooseSeat.LIST_SEAT_CHOOSED);
            milis = (long)b.getLong("MILIS");
        }
        startTimer(milis-1000);


        LinearLayout layoutGetInfo = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutGetInfo.setOrientation(LinearLayout.VERTICAL);
        layoutGetInfo.setLayoutParams(params);
        layoutGetInfo.setPadding(4 * seatGaping_LT, 4 * seatGaping_LT, 4 * seatGaping_LT, 4* seatGaping_LT);
        layout.addView(layoutGetInfo);

        LinearLayout layout =null;
        LinearLayout layout1=null;
        LinearLayout layout2=null;
        LinearLayout layout3=null;
        int count=1;
        for (int i = 0; i < listTicChoosed.size(); i++) {

            layout = new LinearLayout(this);
            //layout.setLayoutParams(params);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(R.drawable.info_cus_card);
            layoutGetInfo.addView(layout);

            layout1 = new LinearLayout(this);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(params);
            layout.addView(layout1);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(25*width/54), (int)(10*height/111));
            layoutParams.setMargins(seatGaping_LT, seatGaping_LT, seatGaping_TB, seatGaping_TB);
            //ID Seat TextView
            TextView view = new TextView(this);

            view.setLayoutParams(layoutParams);
            view.setPadding(0, 0, 0, 0 * seatGaping_LT);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.BLUE);
            view.setText("Mã vé: " + listTicChoosed.get(i).getIdTic());
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            layout1.addView(view);
            //Number Seat TextView

            TextView viewNS = new TextView(this);
            viewNS.setLayoutParams(layoutParams);
            viewNS.setPadding(0, 0, 0, 0 * seatGaping_LT);
            viewNS.setGravity(Gravity.CENTER);
            viewNS.setTextColor(Color.BLUE);
            viewNS.setText("Số ghế: " + listTicChoosed.get(i).getNumOfSeat());
            viewNS.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            layout1.addView(viewNS);
            //
            layout2 = new LinearLayout(this);
            layout2.setOrientation(LinearLayout.HORIZONTAL);
            layout2.setLayoutParams(params);
            layout.addView(layout2);

            //Họ và tên
            TextView viewName = new TextView(this);
            LinearLayout.LayoutParams layoutParamsTV = new LinearLayout.LayoutParams((int)(10*width/27), (int)(5*height/74));
            layoutParamsTV.setMargins(0, 0, 0, (int)(height/74));
            viewName.setLayoutParams(layoutParamsTV);
            viewName.setPadding(100, 0, 0, 0 * seatGaping_LT);
            //viewName.setGravity(Gravity.CENTER);
            viewName.setTextColor(Color.BLACK);
            viewName.setText("Họ và tên: ");
            viewName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            layout2.addView(viewName);


            EditText edTextName = new EditText(this);
            LinearLayout.LayoutParams layoutParamsETV = new LinearLayout.LayoutParams((int)(25*width/54), (int)(5*height/74));
            layoutParamsETV.setMargins(0, 0, 0, (int)(height/74));
            edTextName.setLayoutParams(layoutParamsETV);
            edTextName.setPadding(0, 0, 0, 0 * seatGaping_LT);
            //edTextName.setGravity(Gravity.CENTER);
            edTextName.setId(count*65);
            count++;
            edTextName.setBackgroundResource(R.drawable.shadow);
            edTextName.setTextColor(Color.BLACK);
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
            viewIDP.setPadding((int)(5*width/54), 0, 0, 0 * seatGaping_LT);
            //viewIDP.setGravity(Gravity.CENTER);
            viewIDP.setTextColor(Color.BLACK);
            viewIDP.setText("CMND/CCCD: ");
            viewIDP.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            layout3.addView(viewIDP);


            EditText edTextIDP = new EditText(this);
            edTextIDP.setLayoutParams(layoutParamsETV);
            edTextIDP.setPadding(0, 0, 0, 0 * seatGaping_LT);
            //edTextIDP.setGravity(Gravity.CENTER);
            edTextIDP.setId(count*65);
            count++;
            edTextIDP.setBackgroundResource(R.drawable.shadow);
            edTextIDP.setTextColor(Color.BLACK);
            edTextIDP.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
            layout3.addView(edTextIDP);
            //button
            Button btn = new Button(this);
            LinearLayout.LayoutParams layoutParamsBtn = new LinearLayout.LayoutParams((int)(25*width/54), (int)(5*height/111));
            layoutParamsBtn.setMargins((int)(25*width/108), (int)(1*height/222), (int)(width/108), (int)(5*height/111));
            //btn.setPadding(0,0,0,10);
            btn.setTag(STATUS_NO);
            btn.setLayoutParams(layoutParamsBtn);
            btn.setGravity(Gravity.CENTER_HORIZONTAL);
            btn.setText("KHAI BÁO Y TẾ");
            btn.setId(count*65);
            count++;
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundResource(R.drawable.background_button);
            btn.setOnClickListener(this);
            layout.addView(btn);

           // add vào danh sách giỏ
            TicketCartModel tm = new TicketCartModel(idCus,listTicChoosed.get(i).getIdTic(),viewNS.getText().toString(),edTextName.getText().toString(),edTextIDP.getText().toString(),null,trip.toString(),listTicChoosed.get(i).getPrice());
            listProInCart.add(tm);
            //hỗ trợ khi thanh toán ko thành công
            //cập nhật giá trị khi ng dùng nhập thông tin vào edit Text
            edTextName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                tm.setNameOwner(edTextName.getText().toString());
                }
            });
            edTextIDP.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    tm.setIdCard(edTextIDP.getText().toString());
                }
            });
        }

         btnCont = findViewById(R.id.btn_continueToPayIC);

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCont.getText().toString().equalsIgnoreCase("ĐÃ HẾT GIỜ!")){
                    finish();
                }
                else if(checkInfo()){
                    isDestroy=false;
                    Intent i = new Intent(Info_Cus_Get_Ticket.this, Pay.class);
                    startActivity(i);
                }
            }
        });

    }

    public boolean checkInfo(){
        for (int i=1;i<=listTicChoosed.size()*3;i++){
            if (i%3==0){
                Button btn = (Button)findViewById(i*65);
                 if ((int)btn.getTag()==0) {
                     //Create dialog
                     AlertDialog.Builder builder = new AlertDialog.Builder(Info_Cus_Get_Ticket.this);
                     builder.setTitle("Thông báo!");
                     builder.setMessage("Bạn phải KBYT cho tất cả các vé");
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
                     return false;}

            }else {
                EditText edt = (EditText) findViewById(i * 65);
                if (TextUtils.isEmpty(edt.getText().toString())) {
                    //Create dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(Info_Cus_Get_Ticket.this);
                    builder.setTitle("Thông báo!");
                    builder.setMessage("Bạn phải điền thông tin đầy đủ cho tất cả các vé");
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
                    return false;}
                }

            }


        return true;
    }
    @Override
    public void onClick(View view) {
        id_BtnKBYT=view.getId();
        Intent intent = new Intent(Info_Cus_Get_Ticket.this, Kbyt.class);
        startActivityForResult(intent,REQUEST_NO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_NO){
            if (resultCode==Kbyt.RESULT_NO){
                Button b = (Button)findViewById(id_BtnKBYT);
                b.setTag(STATUS_YES);
            }
        }
    }
    //start timer function
    void startTimer(long mili) {
        cTimer = new CountDownTimer(mili, 1000) {
            Button b = (Button)findViewById(R.id.btn_continueToPayIC);
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds     = seconds % 60;
                b.setText(String.format("Tiếp tục thanh toán (%d:%02d)", minutes, seconds));
            }
            public void onFinish() {

                b.setText("Đã hết giờ!");
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if(cTimer!=null) {
            cTimer.cancel();
            cTimer=null;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        if (!listTicChoosed.isEmpty()&&isDestroy==true){
            for (Ticket t: listTicChoosed) {
                TicketDAO.updateSeat(t.getIdTic(),TicketDAO.AVAILABLE_TICKET);

            }
        }
    }
}