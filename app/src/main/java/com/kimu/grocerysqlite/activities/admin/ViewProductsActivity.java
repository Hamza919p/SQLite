package com.kimu.grocerysqlite.activities.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.kimu.grocerysqlite.DbHandler;
import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.adapters.AdminSideProductsAdapter;
import com.kimu.grocerysqlite.databinding.ActivityViewProductsBinding;
import com.kimu.grocerysqlite.interfaces.ItemClickListener;
import com.kimu.grocerysqlite.utils.Launcher;

public class ViewProductsActivity extends AppCompatActivity {
    private ActivityViewProductsBinding binding;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        initializeAdapter();
        onClickListeners();
    }

    private void init() {
        dbHandler = new DbHandler(this);
    }

    @Override
    protected void onResume() {
        initializeAdapter();
        super.onResume();
    }

    private void onClickListeners() {
        binding.btnAddProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Launcher.startAddUpdateProductsActivity(ViewProductsActivity.this, null);
            }
        });
    }

    private void initializeAdapter() {
        AdminSideProductsAdapter adapter = new AdminSideProductsAdapter(dbHandler.readProducts());
        binding.rvProducts.setAdapter(adapter);
        adapter.callBackListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Launcher.startAddUpdateProductsActivity(ViewProductsActivity.this,
                        dbHandler.readProducts().get(position));
            }
        });
    }
}