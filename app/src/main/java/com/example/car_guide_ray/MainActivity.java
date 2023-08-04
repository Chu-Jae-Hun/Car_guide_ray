package com.example.car_guide_ray;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.car_guide_ray.databinding.ActivityMainBinding;
import com.example.car_guide_ray.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setBackground(null);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_item1) {
                replaceFragment(new InnerFragment());
                startActivity(new Intent(getApplicationContext(), InnerActivity.class));
                return true;

            }
            else if (itemId == R.id.navigation_item2) {
                replaceFragment(new OuterFragment());
                startActivity(new Intent(getApplicationContext(), OuterActivity.class));
                return true;
            } else if (itemId == R.id.navigation_item3) {
                replaceFragment(new AccountbookFragment());
                startActivity(new Intent(getApplicationContext(), AccountbookActivity.class));
                return true;
            }
            else if (itemId == R.id.navigation_item4) {
                replaceFragment(new RepairFragment());
                startActivity(new Intent(getApplicationContext(), RepairActivity.class));
                return true;
            }
            return false;
        });
    }

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