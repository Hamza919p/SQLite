package com.kimu.grocerysqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.kimu.grocerysqlite.activities.MainActivity;
import com.kimu.grocerysqlite.models.Admins;
import com.kimu.grocerysqlite.models.Customers;
import com.kimu.grocerysqlite.models.Products;
import com.kimu.grocerysqlite.utils.Constants;
import com.kimu.grocerysqlite.utils.Launcher;

import java.util.ArrayList;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String productsTable = "CREATE TABLE " + Constants.TABLE_PRODUCTS + " ("
                + Constants.PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.PRODUCT_NAME + " TEXT,"
                + Constants.PRODUCT_QUANTITY + " TEXT)";

        String adminTable = "CREATE TABLE " + Constants.TABLE_ADMINS + " ("
                + Constants.ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.ADMIN_NAME + " TEXT,"
                + Constants.ADMIN_EMAIL + " TEXT,"
                + Constants.ADMIN_PASS + " TEXT)";

        String customersTable = "CREATE TABLE " + Constants.TABLE_CUSTOMERS + " ("
                + Constants.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constants.CUSTOMER_NAME + " TEXT,"
                + Constants.CUSTOMER_EMAIL + " TEXT,"
                + Constants.CUSTOMER_PASS + " TEXT)";

        db.execSQL(productsTable);
        db.execSQL(adminTable);
        db.execSQL(customersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ADMINS);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_CUSTOMERS);
        onCreate(db);
    }

    //Add or update trip according to condition
    public void addOrUpdateProducts(Activity activity, String productId, String productName, String productQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Constants.PRODUCT_NAME, productName);
            values.put(Constants.PRODUCT_QUANTITY, productQuantity);

            //means adding new data to SqLite
            if (productId.equals("-1")) { //-1 means to add new data
                db.insert(Constants.TABLE_PRODUCTS, null, values);
                Toast.makeText(activity, "Data Inserted", Toast.LENGTH_SHORT).show();
                activity.finish();
            } else {
                db.update(Constants.TABLE_PRODUCTS, values, Constants.PRODUCT_ID + "=?", new String[]{productId});
                Toast.makeText(activity, "Data Updated", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Add or update trip according to condition
    public void addOrUpdateAdmins(Activity activity, String adminId, String adminName, String adminEmail, String adminPass) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Constants.ADMIN_ID, adminId);
            values.put(Constants.ADMIN_EMAIL, adminEmail);
            values.put(Constants.ADMIN_NAME, adminName);
            values.put(Constants.ADMIN_PASS, adminPass);

            //means adding new data to SqLite
            if (adminId.equals("-1")) { //-1 means to add new data
                ArrayList<Admins> savedAdmins = readAdmins();
                boolean isAdminAlreadyRegistered = false;
                for(int i=0; i< savedAdmins.size(); i++) {
                    if(savedAdmins.get(i).getEmail().equals(adminEmail)) {
                        Toast.makeText(activity, "Sorry Admin already registered", Toast.LENGTH_SHORT).show();
                        isAdminAlreadyRegistered = true;
                        break;
                    }
                }

                if(!isAdminAlreadyRegistered) {
                    db.insert(Constants.TABLE_ADMINS, null, values);
                    Toast.makeText(activity, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                    activity.finish();
                    Launcher.startAdminLandingScreenActivity(activity);
                }

            } else {
                db.update(Constants.TABLE_ADMINS, values, Constants.ADMIN_ID + "=?", new String[]{adminId});
                Toast.makeText(activity, "Data Updated", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addOrUpdateCustomers(Activity activity, String customerId, String customerName, String customerEmail, String customerPass) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(Constants.CUSTOMER_ID, customerId);
            values.put(Constants.CUSTOMER_EMAIL, customerEmail);
            values.put(Constants.CUSTOMER_NAME, customerName);
            values.put(Constants.CUSTOMER_PASS, customerPass);

            //means adding new data to SqLite
            if (customerId.equals("-1")) { //-1 means to add new data
                ArrayList<Customers> savedCustomers = readCustomers();
                boolean isCustomerAlreadyRegistered = false;
                for(int i=0; i< savedCustomers.size(); i++) {
                    if(savedCustomers.get(i).getEmail().equals(customerEmail)) {
                        Toast.makeText(activity, "Sorry Customer already registered", Toast.LENGTH_SHORT).show();
                        isCustomerAlreadyRegistered = true;
                        break;
                    }
                }

                if(!isCustomerAlreadyRegistered) {
                    db.insert(Constants.TABLE_CUSTOMERS, null, values);
                    Toast.makeText(activity, "SignUp Successfully", Toast.LENGTH_SHORT).show();
//                    activity.finish();
//                    Launcher.startAdminLandingScreenActivity(activity);
                }

            } else {
                db.update(Constants.TABLE_CUSTOMERS, values, Constants.CUSTOMER_ID + "=?", new String[]{customerId});
                Toast.makeText(activity, "Data Updated", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    //Delete a specific trip
    public void deleteTrip(Activity activity, String id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Constants.TABLE_PRODUCTS, Constants.PRODUCT_ID + "=?", new String[]{id});
            db.close();
            Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show();
            activity.finish();
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<Products> readProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor products = db.rawQuery("SELECT * FROM " + Constants.TABLE_PRODUCTS, null);
        ArrayList<Products> productList = new ArrayList<>();
        if (products.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                productList.add(new Products(
                        products.getString(0),
                        products.getString(1),
                        products.getString(2)
                ));
            } while (products.moveToNext());
        }

        products.close();
        return productList;
    }

    public ArrayList<Admins> readAdmins() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor admins = db.rawQuery("SELECT * FROM " + Constants.TABLE_ADMINS, null);
        ArrayList<Admins> adminsList = new ArrayList<>();
        if (admins.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                adminsList.add(new Admins(
                        admins.getString(0),
                        admins.getString(1),
                        admins.getString(2),
                        admins.getString(3)
                ));
            } while (admins.moveToNext());
        }

        admins.close();
        return adminsList;
    }

    public ArrayList<Customers> readCustomers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor customers = db.rawQuery("SELECT * FROM " + Constants.TABLE_CUSTOMERS, null);
        ArrayList<Customers> customersList = new ArrayList<>();
        if (customers.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                customersList.add(new Customers(
                        customers.getString(0),
                        customers.getString(1),
                        customers.getString(2),
                        customers.getString(3)
                ));
            } while (customers.moveToNext());
        }

        customers.close();
        return customersList;
    }


}
