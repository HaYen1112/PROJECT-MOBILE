package edu.nlu.probooktic.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import edu.nlu.probooktic.Model.Acount;
import edu.nlu.probooktic.Model.TripItem;
import edu.nlu.probooktic.R;
import edu.nlu.probooktic.View.ChooseSeat;
import edu.nlu.probooktic.View.ItemMenu;
import edu.nlu.probooktic.View.Menu;
import edu.nlu.probooktic.View.MenuAdapter;
import edu.nlu.probooktic.View.PromotionDetail;
import edu.nlu.probooktic.View.TicketCart;
import edu.nlu.probooktic.View.TripHomeAdapter;

public class TrangChuCustomer extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView1, listView2;
    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> list;
    ArrayList<TripItem> listTrip;
    MenuAdapter adapter;
    TripHomeAdapter tripsAdapter;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_customer);
            Acount acount = new Acount();
            acount.inSertToDB();
            anhxa();
            actionToolBar();
            actionMenu();
            actionViewTrip();

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrangChuCustomer.this, ChooseSeat.class);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(TrangChuCustomer.this, TrangChuCustomer.class);
                        intent.putExtra("name", (String) name + "");
                        startActivity(intent);
                        Toast.makeText(TrangChuCustomer.this, "Th??ng tin c?? nh??n", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent1 = new Intent(TrangChuCustomer.this, TrangChuCustomer.class);
                        intent1.putExtra("name", (String) name + "");
                        startActivity(intent1);
                        Toast.makeText(TrangChuCustomer.this, "B???n ch???n Trang ch???", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intent2 = new Intent(TrangChuCustomer.this, TicketCart.class);
                        intent2.putExtra("name", (String) name + "");
                        startActivity(intent2);
                        Toast.makeText(TrangChuCustomer.this, "B???n ch???n Gi??? v??", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent intent3 = new Intent(TrangChuCustomer.this, PromotionDetail.class);
                        intent3.putExtra("name", (String) name + "");
                        startActivity(intent3);
                        Toast.makeText(TrangChuCustomer.this, "B???n ch???n Khuy???n m??i d??nh cho b???n", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Intent intent4 = new Intent(TrangChuCustomer.this, Menu.class);
                        intent4.putExtra("name", (String) name + "");
                        startActivity(intent4);
                        Toast.makeText(TrangChuCustomer.this, "B???n ch???n ????ng xu???t", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void actionViewTrip() {
        listTrip = new ArrayList<>();
        listTrip.add(new TripItem("Ki??n Giang - C???n Th??", "9:00 AM", "120.000 ?????ng", "65TK - 09867"));
        listTrip.add(new TripItem("Ti???n Giang - V??nh Long", "5:00 AM", "100.000 ?????ng", "63B1 - 05566"));
        listTrip.add(new TripItem("?????ng Th??p - C?? Mau", "1:00 PM", "150.000 ?????ng", "55S2 - 02452"));
        listTrip.add(new TripItem("B???n Tre - Long An", "5:00 PM", "100.000 ?????ng", "71B1 - 05541"));
        listTrip.add(new TripItem("Tr?? Vinh - An Giang", "2:00 AM", "150.000 ?????ng", "78D1 - 04657"));
        listTrip.add(new TripItem("H???u Giang - TP HCM", "3:00 AM", "200.000 ?????ng", "45S3 - 01234"));
        listTrip.add(new TripItem("C???n Th?? - TP HCM", "8:00 AM", "220.000 ?????ng", "51B1 - 07845"));
        listTrip.add(new TripItem("B???n Tre - C???n Th??", "9:00 PM", "120.000 ?????ng", "71C2 - 07541"));
        listTrip.add(new TripItem("V??nh Long - ?????ng Th??p", "1:00 PM", "100.000 ?????ng", "55C3 - 04561"));
        listTrip.add(new TripItem("Ki??n Giang - V??ng T??u", "10:00 AM", "320.000 ?????ng", "45TH - 06024"));
        listTrip.add(new TripItem("Long An - Tr?? Vinh", "5:00 PM", "90.000 ?????ng", "61B3 - 04142"));
        tripsAdapter = new TripHomeAdapter(this, R.layout.trip_item, listTrip);
        listView1.setAdapter(tripsAdapter);
    }

    private void actionMenu() {
        list = new ArrayList<>();
        Intent intent = getIntent();
        name = (String) intent.getSerializableExtra("name");
        list.add(new ItemMenu( name + "", R.drawable.ic_person));
        list.add(new ItemMenu("Trang ch???", R.drawable.ic_home));
        list.add(new ItemMenu("Gi??? v??", R.drawable.ic_shopping));
        list.add(new ItemMenu("Khuy???n m??i d??nh cho b???n", R.drawable.ic_km));
        list.add(new ItemMenu("????ng xu???t", R.drawable.ic_exit));
        adapter = new MenuAdapter(this, R.layout.item_row, list);
        listView2.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_person);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbars);
        drawerLayout = findViewById(R.id.menu_home);
        navigationView = findViewById(R.id.navigation_menu);
        listView2 = findViewById(R.id.listViewMenu);
        listView1 = findViewById(R.id.listTrips);

    }
}