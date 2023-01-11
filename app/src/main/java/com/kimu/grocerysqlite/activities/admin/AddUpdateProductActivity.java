package com.kimu.grocerysqlite.activities.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kimu.grocerysqlite.DbHandler;
import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.databinding.ActivityAddUpdateProductBinding;
import com.kimu.grocerysqlite.interfaces.DialogListener;
import com.kimu.grocerysqlite.models.Products;
import com.kimu.grocerysqlite.utils.CustomAlertDialog;

public class AddUpdateProductActivity extends AppCompatActivity {
    private ActivityAddUpdateProductBinding binding;
    private DbHandler dbHandler;
    private Products products;
    private String productId;
    private CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        onClickListeners();
    }

    private void init() {
        products = (Products) getIntent().getSerializableExtra("products");
        dbHandler = new DbHandler(this);
        customAlertDialog = new CustomAlertDialog();
        if (products != null) { // -1 means to add new data. if -1 then it means update data
            binding.btnSave.setText(getString(R.string.update));
            productId = products.getProductId();
            binding.etProductName.setText(products.getProductName());
            binding.etProductQuantity.setText(products.getProductQuantity());
            binding.btnDelete.setVisibility(View.VISIBLE);
        } else {
            productId = "-1";
        }
    }

    private void onClickListeners() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    dbHandler.addOrUpdateProducts(AddUpdateProductActivity.this, productId,
                            binding.etProductName.getText().toString(),
                            binding.etProductQuantity.getText().toString());
                }else
                    Toast.makeText(AddUpdateProductActivity.this, "Please add required fields", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAlertDialog.show(AddUpdateProductActivity.this, getString(R.string.warning), getString(R.string.are_you_sure_you_want_to_delete));
                customAlertDialog.callback(new DialogListener() {
                    @Override
                    public void listen(Boolean yes) {
                        if(yes)
                            dbHandler.deleteTrip(AddUpdateProductActivity.this, productId);
                    }
                });
            }
        });
    }

    private boolean validate() {
        if (binding.etProductName.getText().toString().trim().isEmpty())
            return false;
        else if (binding.etProductQuantity.getText().toString().trim().isEmpty())
            return false;

        return true;
    }
}