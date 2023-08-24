package com.example.car_guide_ray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.car_guide_ray.databinding.ActivityMainBinding;

public class RepairActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.setBackground(null);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_item1) {
                replaceFragment(new OuterFragment());
                //startActivity(new Intent(getApplicationContext(), InnerActivity.class));
                return true;
            }
            else if (itemId == R.id.navigation_item2) {
                replaceFragment(new InnerFragment());
                //startActivity(new Intent(getApplicationContext(), OuterActivity.class));
                return true;
            } else if (itemId == R.id.navigation_item3) {
                replaceFragment(new AccountbookFragment());
                //startActivity(new Intent(getApplicationContext(), AccountbookActivity.class));
                return true;
            }
            else if (itemId == R.id.navigation_item4) {
                replaceFragment(new RepairFragment());
                //startActivity(new Intent(getApplicationContext(), RepairActivity.class));
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}