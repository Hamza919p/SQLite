package com.kimu.grocerysqlite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.interfaces.ItemClickListener;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class AdminSideUsersAdapter extends RecyclerView.Adapter<AdminSideUsersAdapter.AdminSideUsersViewHolder>{

    private ItemClickListener itemClickListener;
    public void callBackListener(ItemClickListener mCallback) {
        itemClickListener = mCallback;
    }

    @NonNull
    @Override
    public AdminSideUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_side_users, parent, false);
        return new AdminSideUsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSideUsersViewHolder holder, int position) {
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    protected class AdminSideUsersViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userItems;
        LinearLayout root;

        public AdminSideUsersViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            userName = itemView.findViewById(R.id.tv_user_name);
            userItems = itemView.findViewById(R.id.tv_user_items);
        }
    }
}
