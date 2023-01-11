package com.kimu.grocerysqlite.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.adapters.AdminSideUsersAdapter;
import com.kimu.grocerysqlite.databinding.ActivityManageUsersBinding;
import com.kimu.grocerysqlite.interfaces.ItemClickListener;

public class ManageUsersActivity extends AppCompatActivity {
    private ActivityManageUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeAdapter();
    }

    private void initializeAdapter() {
        AdminSideUsersAdapter adapter = new AdminSideUsersAdapter();
        binding.rvUsers.setAdapter(adapter);
        adapter.callBackListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
    }
}