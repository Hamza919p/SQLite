package com.kimu.grocerysqlite.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.databinding.ActivityLandingScreenBinding;
import com.kimu.grocerysqlite.utils.Launcher;

public class AdminLandingScreenActivity extends AppCompatActivity {
    private ActivityLandingScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClickListeners();
    }

    private void onClickListeners() {
        binding.btnManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Launcher.startManageUsersActivity(AdminLandingScreenActivity.this);
            }
        });

        binding.btnViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Launcher.startViewProductsActivity(AdminLandingScreenActivity.this);

            }
        });

        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Launcher.startProfileActivity(AdminLandingScreenActivity.this);
            }
        });
    }
}