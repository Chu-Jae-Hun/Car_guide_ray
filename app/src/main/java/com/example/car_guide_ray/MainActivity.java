package com.example.car_guide_ray;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.car_guide_ray.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.navigation_item1) {
                        // navigation_item1 처리 로직
                        Toast.makeText(MainActivity.this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                        selectedFragment = new Fragment();
                    } else if (item.getItemId() == R.id.navigation_item2) {
                        // navigation_item2 처리 로직
                        Toast.makeText(MainActivity.this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                        selectedFragment = new Fragment();
                    } else if (item.getItemId() == R.id.navigation_item3) {
                        // navigation_item3 처리 로직
                        Toast.makeText(MainActivity.this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                        selectedFragment = new Fragment();
                    } else if (item.getItemId() == R.id.navigation_item4) {
                        // navigation_item4 처리 로직
                        Toast.makeText(MainActivity.this, "Item 4 selected", Toast.LENGTH_SHORT).show();
                        selectedFragment = new Fragment();
                    } else if (item.getItemId() == R.id.navigation_item5) {
                        // navigation_item5 처리 로직
                        Toast.makeText(MainActivity.this, "Item 5 selected", Toast.LENGTH_SHORT).show();
                        selectedFragment = new Fragment();
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, selectedFragment)
                                .commit();
                    }

                    return true;

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelectedListener);

        // 초기 화면 설정
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();
    }
}
