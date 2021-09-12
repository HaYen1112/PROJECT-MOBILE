package edu.nlu.probooktic.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.nlu.probooktic.Model.Date;
import edu.nlu.probooktic.Model.Trip;
import edu.nlu.probooktic.Model.TripItem;
import edu.nlu.probooktic.R;

public class Menu extends AppCompatActivity {
   Toolbar toolbar;
    NavigationView navigationView;
    ListView listView1, listView2;
    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> list;
    ArrayList<TripItem> listTrip;
    MenuAdapter adapter;
    LinearLayout linearLayout;
    TextView textView;
    TripHomeAdapter tripsAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home);
        anhxa();
        actionToolBar();
        actionMenu();
        actionViewTrip();
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Menu.this, ChooseSeat.class);
                startActivity(intent);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 0:
                      Intent intent1 = new Intent(Menu.this, Menu.class);
                      startActivity(intent1);
                      Toast.makeText(Menu.this, "Bạn chọn Trang chủ", Toast.LENGTH_SHORT).show();
                      break;
                  case 1:
                      Intent intent2 = new Intent(Menu.this, TicketCart.class);
                      startActivity(intent2);
                      Toast.makeText(Menu.this, "Bạn chọn Giỏ vé", Toast.LENGTH_SHORT).show();
                      break;
                  case 2:
                      Intent intent3 = new Intent(Menu.this, Menu.class);
                      startActivity(intent3);
                      Toast.makeText(Menu.this, "Bạn Chọn Khuyến mãi", Toast.LENGTH_SHORT).show();
                      break;
                  case 3:
                      Intent intent4 = new Intent(Menu.this, LoginActivity.class);
                      startActivity(intent4);
                      Toast.makeText(Menu.this, "Bạn Chọn Đăng nhập", Toast.LENGTH_SHORT).show();
                      break;
              }
               // Toast.makeText(Menu.this, ""+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actionViewTrip() {
        listTrip = new ArrayList<>();
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "14/09/2021 - 9:00 AM", "120.000 Đồng", "65TK - 09867"));
        tripsAdapter = new TripHomeAdapter(this, R.layout.trip_item, listTrip);
        listView1.setAdapter(tripsAdapter);
    }

    private void actionMenu() {
        list = new ArrayList<>();
        list.add(new ItemMenu("Trang chủ", R.drawable.ic_home));
        list.add(new ItemMenu("Giỏ vé", R.drawable.ic_shopping));
        list.add(new ItemMenu("Khuyến mãi", R.drawable.ic_km));
        list.add(new ItemMenu("Đăng nhập", R.drawable.ic_person));
        adapter = new MenuAdapter(this, R.layout.item_row, list);
        listView2.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_action_menu);
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
