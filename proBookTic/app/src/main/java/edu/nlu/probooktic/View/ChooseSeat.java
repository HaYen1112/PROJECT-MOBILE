package edu.nlu.probooktic.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import edu.nlu.probooktic.Model.Ticket;
import edu.nlu.probooktic.Model.TicketCartModel;
import edu.nlu.probooktic.Model.TicketDAO;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.R;

import static android.content.ContentValues.TAG;

public class ChooseSeat extends AppCompatActivity implements View.OnClickListener {
    ViewGroup layout;

    ArrayList<Ticket> listTic = new ArrayList<>();


    String seats = "";
    String[] an = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    List<String> anphabet = new ArrayList<String>(Arrays.asList(an));
    long milisCur = 0;

    CountDownTimer cTimer = null;
    List<TextView> seatViewList = new ArrayList<>();
    int seatSize = 90;
    int seatGaping_LT = 36;
    int seatGaping_TB = 40;

    int STATUS_AVAILABLE = 1;
    int STATUS_BOOKED = 2;
    int STATUS_RESERVED = 3;
    String selectedIds = "";
    static final String LIST_SEAT_CHOOSED = "listSeatChoosed";
    ArrayList<Integer> seatchoosed = null;//{1,8,9,....}
    public static Trip trip = null;//new Trip("TR0001", "Tiền Giang", "TP.HCM", new Date(), "9:00", "13:00", "63F-5236");
    public static ArrayList<String> listSeatChoosed = new ArrayList<>();//{A1,A2,A3,....}
    TextView person;
    TextView seat;
    boolean isDestroy = true;//Kiểm tra xem act có bị hủy khi chua chuyển sang acti khác ko

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seat);

        //da man hinh/////////////////////////
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        long width = size.x;
        Log.e(TAG,width+"");

         seatSize = (int)width/12;
         seatGaping_LT = (int)width/30;
         seatGaping_TB = (int)width/27;

        //////////////////////////////
        //get the bundle to get list seat choosed
        Intent intent=getIntent();
        trip=(Trip)intent.getSerializableExtra("trip");
        //////////////////////////////

        Query query = FirebaseDatabase.getInstance().getReference("ticket")
                .orderByChild("idTrip")
                .equalTo(trip.getIdtrip());
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTic.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ticket tic = snapshot.getValue(Ticket.class);
                        listTic.add(tic);

                    }
                    createSeatMap();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void createSeatMap() {
        layout = findViewById(R.id.layoutSeat);

        seats = Ticket.listToString(listTic);
        seats = "/" + seats;
        //Log.e(TAG, "12" + listTic.size());
        LinearLayout layoutSeat = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(4 * seatGaping_LT, 4 * seatGaping_LT, 4 * seatGaping_LT, 4 * seatGaping_LT);
        layout.addView(layoutSeat);

        LinearLayout layout = null;
        int countID = 0;
        int count = 0;
        int countA = -1;

        //Layout TRIP

        person = (TextView) findViewById(R.id.txtPas);
        if (seatchoosed != null)
            person.setText(seatchoosed.size() + " Người");
        else
            person.setText("");
        TextView bSo = (TextView) findViewById(R.id.txtIdCar);
        bSo.setText(trip.getBienSo());
        TextView bsoXe = (TextView) findViewById(R.id.txtIdXE);
        bsoXe.setText(trip.getBienSo());

        seat = (TextView) findViewById(R.id.txtChooseSeat);
        if (listSeatChoosed.size() != 0)
            seat.setText(stringFromArrList(listSeatChoosed));
        else
            seat.setText("");
        // Layout SEAT
        for (int index = 0; index < seats.length(); index++) {
            if (seats.charAt(index) == '/') {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layoutSeat.addView(layout);
                count = 0;
                countA++;
            } else if (seats.charAt(index) == 'U') {
                countID++;
                count++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping_LT, seatGaping_LT, seatGaping_TB, seatGaping_TB);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 0 * seatGaping_LT);
                view.setId(countID);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.seat_orange);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_BOOKED);
                view.setText(anphabet.get(countA) + count);
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'A') {
                count++;
                countID++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping_LT, seatGaping_LT, seatGaping_TB, seatGaping_TB);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 0 * seatGaping_LT);
                view.setId(countID);
                view.setGravity(Gravity.CENTER);

                view.setBackgroundResource(R.drawable.seat_gray);
                view.setText(anphabet.get(countA) + count);
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_AVAILABLE);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == 'R') {
                count++;
                countID++;
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping_LT, seatGaping_LT, seatGaping_TB, seatGaping_TB);
                view.setLayoutParams(layoutParams);
                view.setPadding(0, 0, 0, 0 * seatGaping_LT);
                view.setId(countID);
                view.setGravity(Gravity.CENTER);
                view.setBackgroundResource(R.drawable.yellow_seat);
                view.setText(anphabet.get(countA) + count);
                view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
                view.setTextColor(Color.BLACK);
                view.setTag(STATUS_RESERVED);
                layout.addView(view);
                seatViewList.add(view);
                view.setOnClickListener(this);
            } else if (seats.charAt(index) == '_') {
                TextView view = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(0, 0, seatGaping_TB, seatGaping_TB);
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(Color.TRANSPARENT);
                view.setText("");
                layout.addView(view);
            }
        }
        Button b = (Button) findViewById(R.id.btnProcess);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b.getText().toString().equalsIgnoreCase("ĐÃ HẾT GIỜ!")) {
                    finish();
                } else if (listSeatChoosed.isEmpty()) {

                } else {
                    isDestroy = false;//không thực hiện onDestroyed()khi acti bị hủy

                    Intent intent = new Intent(ChooseSeat.this, Info_Cus_Get_Ticket.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(LIST_SEAT_CHOOSED, (Serializable) getListSeatChoosed());
                    bundle.putLong("MILIS", milisCur);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public ArrayList<Ticket> getListSeatChoosed() {
        ArrayList<Ticket> re = new ArrayList<>();
        char[] c = selectedIds.toCharArray();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < c.length - 1; i++) {
            str.append(c[i]);
        }
        StringTokenizer tokenizer = new StringTokenizer(str.toString(), ",");
        while (tokenizer.hasMoreTokens()) {
            re.add(listTic.get(Integer.parseInt(tokenizer.nextToken()) - 1));
        }
        return re;
    }

    @Override
    public void onClick(View view) {
        String ts = ((TextView) view).getText().toString();
        if (cTimer == null) startTimer();
        //Lấy vị trí trong danh sách vé
        int indexInListTic = Integer.parseInt(view.getId() + "") - 1;
        //lấy mã vé
        String idTic = listTic.get(indexInListTic).getIdTic();

        if ((int) view.getTag() == STATUS_AVAILABLE) {
            if (selectedIds.contains(view.getId() + ",")) {
                selectedIds = selectedIds.replace(+view.getId() + ",", "");
                view.setBackgroundResource(R.drawable.seat_gray);
                listSeatChoosed.remove(ts);

                TicketDAO.updateSeat(idTic, TicketDAO.AVAILABLE_TICKET);
            } else {
                listSeatChoosed.add(ts);
                selectedIds = selectedIds + view.getId() + ",";
                view.setBackgroundResource(R.drawable.seat_green);
                TicketDAO.updateSeat(idTic, TicketDAO.RESERVED_TICKET);
            }
        } else if ((int) view.getTag() == STATUS_BOOKED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Booked", Toast.LENGTH_LONG).show();
        } else if ((int) view.getTag() == STATUS_RESERVED) {
            Toast.makeText(this, "Seat " + view.getId() + " is Reserved", Toast.LENGTH_SHORT).show();
        }
        //Layout TRIP
        seatchoosed = getListSeatChoosedByIdView();

        if (seatchoosed != null)
            person.setText(seatchoosed.size() + " Người");
        else
            person.setText("");


        if (listSeatChoosed.size() != 0)
            seat.setText(stringFromArrList(listSeatChoosed));
        else
            seat.setText("");
        if (TextUtils.isEmpty(selectedIds)) {
            cancelTimer();
            Button b = (Button) findViewById(R.id.btnProcess);
            b.setText("Tiếp tục");
        }
    }

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(300000, 1000) {
            Button b = (Button) findViewById(R.id.btnProcess);

            public void onTick(long millisUntilFinished) {
                milisCur = millisUntilFinished;
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                b.setText(String.format("Tiếp tục (%d:%02d)", minutes, seconds));
            }

            public void onFinish() {
                ArrayList<Integer> re = getListSeatChoosedByIdView();
                for (Integer in : re) {
                    TextView t = (TextView) findViewById(in);
                    t.setBackgroundResource(R.drawable.seat_gray);

                }
                b.setText("Đã hết giờ!");
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if (cTimer != null) {
            cTimer.cancel();
            cTimer = null;
        }
    }

    public ArrayList<Integer> getListSeatChoosedByIdView() {
        ArrayList<Integer> re = new ArrayList<>();
        char[] c = selectedIds.toCharArray();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < c.length - 1; i++) {
            str.append(c[i]);
        }
        StringTokenizer tokenizer = new StringTokenizer(str.toString(), ",");
        while (tokenizer.hasMoreTokens()) {
            re.add(Integer.parseInt(tokenizer.nextToken()));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        if (!listSeatChoosed.isEmpty() && isDestroy == true) {
            for (Ticket t : listTic) {
                if (listSeatChoosed.contains(t.getNumOfSeat())) {
                    TicketDAO.updateSeat(t.getIdTic(), TicketDAO.AVAILABLE_TICKET);
                }
            }
        }
    }
}
