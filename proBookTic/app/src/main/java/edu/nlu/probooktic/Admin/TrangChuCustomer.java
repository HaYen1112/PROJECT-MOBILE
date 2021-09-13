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
                        Toast.makeText(TrangChuCustomer.this, "Thông tin cá nhân", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent1 = new Intent(TrangChuCustomer.this, TrangChuCustomer.class);
                        intent1.putExtra("name", (String) name + "");
                        startActivity(intent1);
                        Toast.makeText(TrangChuCustomer.this, "Bạn chọn Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intent2 = new Intent(TrangChuCustomer.this, TicketCart.class);
                        intent2.putExtra("name", (String) name + "");
                        startActivity(intent2);
                        Toast.makeText(TrangChuCustomer.this, "Bạn chọn Giỏ vé", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent intent3 = new Intent(TrangChuCustomer.this, PromotionDetail.class);
                        intent3.putExtra("name", (String) name + "");
                        startActivity(intent3);
                        Toast.makeText(TrangChuCustomer.this, "Bạn chọn Khuyến mãi dành cho bạn", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Intent intent4 = new Intent(TrangChuCustomer.this, Menu.class);
                        intent4.putExtra("name", (String) name + "");
                        startActivity(intent4);
                        Toast.makeText(TrangChuCustomer.this, "Bạn chọn Đăng xuất", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void actionViewTrip() {
        listTrip = new ArrayList<>();
        listTrip.add(new TripItem("Kiên Giang - Cần Thơ", "9:00 AM", "120.000 Đồng", "65TK - 09867"));
        listTrip.add(new TripItem("Tiền Giang - Vĩnh Long", "5:00 AM", "100.000 Đồng", "63B1 - 05566"));
        listTrip.add(new TripItem("Đồng Tháp - Cà Mau", "1:00 PM", "150.000 Đồng", "55S2 - 02452"));
        listTrip.add(new TripItem("Bến Tre - Long An", "5:00 PM", "100.000 Đồng", "71B1 - 05541"));
        listTrip.add(new TripItem("Trà Vinh - An Giang", "2:00 AM", "150.000 Đồng", "78D1 - 04657"));
        listTrip.add(new TripItem("Hậu Giang - TP HCM", "3:00 AM", "200.000 Đồng", "45S3 - 01234"));
        listTrip.add(new TripItem("Cần Thơ - TP HCM", "8:00 AM", "220.000 Đồng", "51B1 - 07845"));
        listTrip.add(new TripItem("Bến Tre - Cần Thơ", "9:00 PM", "120.000 Đồng", "71C2 - 07541"));
        listTrip.add(new TripItem("Vĩnh Long - Đồng Tháp", "1:00 PM", "100.000 Đồng", "55C3 - 04561"));
        listTrip.add(new TripItem("Kiên Giang - Vũng Tàu", "10:00 AM", "320.000 Đồng", "45TH - 06024"));
        listTrip.add(new TripItem("Long An - Trà Vinh", "5:00 PM", "90.000 Đồng", "61B3 - 04142"));
        tripsAdapter = new TripHomeAdapter(this, R.layout.trip_item, listTrip);
        listView1.setAdapter(tripsAdapter);
    }

    private void actionMenu() {
        list = new ArrayList<>();
        Intent intent = getIntent();
        name = (String) intent.getSerializableExtra("name");
        list.add(new ItemMenu( name + "", R.drawable.ic_person));
        list.add(new ItemMenu("Trang chủ", R.drawable.ic_home));
        list.add(new ItemMenu("Giỏ vé", R.drawable.ic_shopping));
        list.add(new ItemMenu("Khuyến mãi dành cho bạn", R.drawable.ic_km));
        list.add(new ItemMenu("Đăng xuất", R.drawable.ic_exit));
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