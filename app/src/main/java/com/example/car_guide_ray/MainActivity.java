package com.example.car_guide_ray;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.car_guide_ray.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //ActivityMainBinding binding;
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit(); // 초기화 단계 Homefragment 만들건지 수정 필요.
            navigationView.setCheckedItem(R.id.navigation_home); //마찬가지 수정 필요
        }

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_item1) { //내장
                replaceFragment(new InnerFragment());
                //startActivity(new Intent(getApplicationContext(), InnerActivity.class));
                return true;

            }
            else if (itemId == R.id.navigation_item2) { //외장
                replaceFragment(new OuterFragment());
                //startActivity(new Intent(getApplicationContext(), OuterActivity.class));
                return true;
            } else if (itemId == R.id.navigation_item3) { //차계부
                replaceFragment(new AccountbookFragment());
                //startActivity(new Intent(getApplicationContext(), AccountbookActivity.class));
                return true;
            }
            else if (itemId == R.id.navigation_item4) { //정비소
                replaceFragment(new RepairFragment());
                //startActivity(new Intent(getApplicationContext(), RepairActivity.class));
                return true;
            }
            return false;
        });

        //home버튼 눌렀을 때 (home화면에 대한 회의 필요..)
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment());
            }
        });
    }

    //outside onCreate
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

        // 초기 화면 설정

       /* bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_item1) {
                startActivity(new Intent(getApplicationContext(), InnerActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.navigation_item2) {
                startActivity(new Intent(getApplicationContext(), OuterActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.navigation_item3) {
                startActivity(new Intent(getApplicationContext(), AccountbookAcitivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.navigation_item4) {
                startActivity(new Intent(getApplicationContext(), AddscheduleActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.navigation_item5) {
                startActivity(new Intent(getApplicationContext(), RepairActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
    }*/


}