package edu.nlu.probooktic.View;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import edu.nlu.probooktic.R;

public class Menu extends AppCompatActivity {
   private Toolbar toolbar;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<ItemMenu> list;
    MenuAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home);
        anhxa();
        actionToolBar();
        actionMenu();
    }

    private void actionMenu() {
        list = new ArrayList<>();
        list.add(new ItemMenu("Đặt vé xe", R.drawable.calendar_icon));
        list.add(new ItemMenu("Ký gửi hàng hóa", R.drawable.calendar_icon));
        list.add(new ItemMenu("Giỏ vé", R.drawable.calendar_icon));
        list.add(new ItemMenu("Khuyến mãi", R.drawable.calendar_icon));
        adapter = new MenuAdapter(this, R.layout.item_row, list);
        listView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbars);
        drawerLayout = findViewById(R.id.menu_home);
        navigationView = findViewById(R.id.navigation_menu);
        listView = findViewById(R.id.listViewMenu);

    }

//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//            getMenuInflater().inflate(R.menu.menu_ticket, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.giove:
//                Toast.makeText(this, "Bạn chọn giỏ vé", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.booking:
//                Toast.makeText(this, "Bạn chọn đặt vé xe", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.search:
//                Toast.makeText(this, "Bạn chọn tìm kiếm", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.kghh:
//                Toast.makeText(this, "Bạn chọn ký gửi hàng hóa", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.km:
//                Toast.makeText(this, "Bạn chọn khuyến mãi", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
