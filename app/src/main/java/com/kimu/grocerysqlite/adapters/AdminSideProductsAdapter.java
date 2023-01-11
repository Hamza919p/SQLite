package com.kimu.grocerysqlite.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.kimu.grocerysqlite.R;
import com.kimu.grocerysqlite.interfaces.ItemClickListener;
import com.kimu.grocerysqlite.models.Products;
import com.kimu.grocerysqlite.utils.ratingBar.CustomRatingBar;

import java.util.ArrayList;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class AdminSideProductsAdapter extends RecyclerView.Adapter<AdminSideProductsAdapter.AdminSideProductsViewHolder> {

    private ItemClickListener itemClickListener;
    public void callBackListener(ItemClickListener mCallback) {
        itemClickListener = mCallback;
    }

    private ArrayList<Products> products;
    public AdminSideProductsAdapter(ArrayList<Products> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public AdminSideProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_side_products_list, parent, false);
        return new AdminSideProductsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdminSideProductsViewHolder holder, int position) {
       holder.productName.setText("Product Name: " + products.get(position).getProductName());
       holder.quantity.setText("Quantity: " + products.get(position).getProductQuantity());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    protected class AdminSideProductsViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity;
        LinearLayout root;
        CustomRatingBar ratingBar;

        public AdminSideProductsViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            productName = itemView.findViewById(R.id.tv_product_name);
            quantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
