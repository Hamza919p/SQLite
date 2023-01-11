package com.kimu.grocerysqlite.utils;

import android.app.Activity;
import android.content.Intent;

import com.kimu.grocerysqlite.activities.ProfileActivity;
import com.kimu.grocerysqlite.activities.admin.AddUpdateProductActivity;
import com.kimu.grocerysqlite.activities.admin.AdminLandingScreenActivity;
import com.kimu.grocerysqlite.activities.admin.ManageUsersActivity;
import com.kimu.grocerysqlite.activities.admin.ViewProductsActivity;
import com.kimu.grocerysqlite.models.Products;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class Launcher {
    public static void startAdminLandingScreenActivity(Activity activity) {
        activity.startActivity(new Intent(activity, AdminLandingScreenActivity.class));
    }

    public static void startManageUsersActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ManageUsersActivity.class));
    }

    public static void startViewProductsActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ViewProductsActivity.class));
    }

    public static void startProfileActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ProfileActivity.class));
    }

    public static void startAddUpdateProductsActivity(Activity activity, Products products) {
        activity.startActivity(new Intent(activity, AddUpdateProductActivity.class).putExtra("products", products));
    }

}
