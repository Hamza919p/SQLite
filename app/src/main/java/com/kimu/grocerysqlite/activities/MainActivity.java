package com.kimu.grocerysqlite.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kimu.grocerysqlite.DbHandler;
import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.databinding.ActivityMainBinding;
import com.kimu.grocerysqlite.models.Admins;
import com.kimu.grocerysqlite.models.Customers;
import com.kimu.grocerysqlite.utils.Launcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        onClickListeners();
    }

    private void init() {
        dbHandler = new DbHandler(this);
    }

    private boolean validateSignInFields() {
        if (binding.etSignInEmail.getText().toString().trim().isEmpty())
            return false;
        else if (binding.etSignInPass.getText().toString().trim().isEmpty())
            return false;
        return true;
    }

    private boolean validateSignUpFields() {
        if (binding.etSignUpEmail.getText().toString().trim().isEmpty())
            return false;
        else if (binding.etSignUpName.getText().toString().trim().isEmpty())
            return false;
        else if (binding.etSignUpPass.getText().toString().trim().isEmpty())
            return false;
        return true;
    }

    private void onClickListeners() {

        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvSignIn.setTextColor(getColor(R.color.black));
                binding.tvSignUp.setTextColor(getColor(R.color.grey));
                binding.llSignIn.setVisibility(View.VISIBLE);
                binding.llSignUp.setVisibility(View.GONE);
            }
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvSignIn.setTextColor(getColor(R.color.grey));
                binding.tvSignUp.setTextColor(getColor(R.color.black));
                binding.llSignIn.setVisibility(View.GONE);
                binding.llSignUp.setVisibility(View.VISIBLE);
            }
        });


        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateSignInFields()) {
                    int selectedId = binding.signInRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        Toast.makeText(MainActivity.this, "Please select user category", Toast.LENGTH_SHORT).show();
                    } else {
                        signIn(selectedId);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateSignUpFields()) {
                    int selectedId = binding.signUpRadioGroup.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        Toast.makeText(MainActivity.this, "Please select user category", Toast.LENGTH_SHORT).show();
                    } else {
                        RadioButton selectedRadioBtn = findViewById(selectedId);
                        if (selectedRadioBtn.getText().toString().equals(getString(R.string.admin))) {
                            dbHandler.addOrUpdateAdmins(MainActivity.this, "-1", binding.etSignUpName.getText().toString(),
                                    binding.etSignUpEmail.getText().toString(),
                                    binding.etSignUpPass.getText().toString());
                        } else {
                            dbHandler.addOrUpdateCustomers(MainActivity.this, "-1", binding.etSignUpName.getText().toString(),
                                    binding.etSignUpEmail.getText().toString(),
                                    binding.etSignUpPass.getText().toString());
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(int selectedId) {
        RadioButton selectedRadioBtn = findViewById(selectedId);
        if (selectedRadioBtn.getText().toString().equals(getString(R.string.admin))) {
            ArrayList<Admins> admins = dbHandler.readAdmins();
            boolean isAdminRegistered = false;
            boolean isPassMatched = false;
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).getEmail().equals(binding.etSignInEmail.getText().toString())) {
                    isAdminRegistered = true;
                    if(admins.get(i).getPass().equals(binding.etSignInPass.getText().toString())) {
                        isPassMatched = true;
                    }
                    break;
                }
            }

            if (isAdminRegistered) {
                if(isPassMatched) {
                    Launcher.startAdminLandingScreenActivity(MainActivity.this);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(MainActivity.this, "Please register yourself first", Toast.LENGTH_SHORT).show();
        }else {
            ArrayList<Customers> customers = dbHandler.readCustomers();
            boolean isCustomerRegistered = false;
            boolean isPassMatched = false;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getEmail().equals(binding.etSignInEmail.getText().toString())) {
                    isCustomerRegistered = true;
                    if(customers.get(i).getPass().equals(binding.etSignInPass.getText().toString())) {
                        isPassMatched = true;
                    }
                    break;
                }
            }

            if (isCustomerRegistered) {
                if(isPassMatched) {
                    Toast.makeText(MainActivity.this, "Valid Password", Toast.LENGTH_SHORT).show();

//                    Launcher.startAdminLandingScreenActivity(MainActivity.this);
//                    finish();
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            } else
                Toast.makeText(MainActivity.this, "Please register yourself first", Toast.LENGTH_SHORT).show();

        }

    }
}