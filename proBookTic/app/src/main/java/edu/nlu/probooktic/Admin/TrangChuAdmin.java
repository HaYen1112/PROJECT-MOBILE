package edu.nlu.probooktic.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import edu.nlu.probooktic.Model.Acount;
import edu.nlu.probooktic.Model.TripItem;
import edu.nlu.probooktic.R;
import edu.nlu.probooktic.View.Admin_TripManagement;
import edu.nlu.probooktic.View.ChooseSeat;
import edu.nlu.probooktic.View.ItemMenu;
import edu.nlu.probooktic.View.Menu;
import edu.nlu.probooktic.View.MenuAdapter;
import edu.nlu.probooktic.View.PromotionDetail;
import edu.nlu.probooktic.View.TicketCart;
import edu.nlu.probooktic.View.TripHomeAdapter;

public class TrangChuAdmin extends AppCompatActivity {
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
    DatabaseReference dataName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_admin);
        Acount acount = new Acount();
        acount.inSertToDB();
        anhxa();
        actionToolBar();
        actionMenu();

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(TrangChuAdmin.this, TrangChuAdmin.class);
                        intent.putExtra("name", (String) name + "");
                        startActivity(intent);
                        Toast.makeText(TrangChuAdmin.this, "Thông tin cá nhân", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent1 = new Intent(TrangChuAdmin.this, Admin_TripManagement.class);
                        intent1.putExtra("name", (String) name + "");
                        startActivity(intent1);
                        Toast.makeText(TrangChuAdmin.this, "Bạn chọn Quản lý thông tin chuyến đi", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intent2 = new Intent(TrangChuAdmin.this, TicketCart.class);
                        intent2.putExtra("name", (String) name + "");
                        startActivity(intent2);
                        Toast.makeText(TrangChuAdmin.this, "Bạn chọn Quản lý nhân viên", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent intent3 = new Intent(TrangChuAdmin.this, PromotionDetail.class);
                        intent3.putExtra("name", (String) name + "");
                        startActivity(intent3);
                        Toast.makeText(TrangChuAdmin.this, "Bạn chọn Quản lý khuyến mãi", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Intent intent4 = new Intent(TrangChuAdmin.this, Menu.class);
                        intent4.putExtra("name", (String) name + "");
                        startActivity(intent4);
                        Toast.makeText(TrangChuAdmin.this, "Bạn chọn Đăng xuất", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void actionMenu() {
        list = new ArrayList<>();
        Intent intent = getIntent();
        name = (String) intent.getSerializableExtra("name");
        list.add(new ItemMenu( name + "", R.drawable.ic_person));
        list.add(new ItemMenu("Quản lý thông tin chuyến đi", R.drawable.ic_car));
        list.add(new ItemMenu("Quản lý nhân viên", R.drawable.ic_persons));
        list.add(new ItemMenu("Quản lý khuyến mãi", R.drawable.ic_km));
        list.add(new ItemMenu("Đăng xuất", R.drawable.ic_exit));
        adapter = new MenuAdapter(this, R.layout.item_row, list);
        listView2.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_quanly);
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
