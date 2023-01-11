package com.kimu.grocerysqlite.utils;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.interfaces.DialogListener;

/**
 * Custom class for Alert Dialog
 * */
public class CustomAlertDialog {
    private DialogListener dialogListener;
    public void callback(DialogListener mCallback) {
        this.dialogListener = mCallback;
    }

    public void show(Activity activity, String title, String description) {
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.listen(true);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.listen(false);
                        dialog.dismiss();
                    }
                }).show();
    }
}
