package com.example.uas2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder>{
    ArrayList<Order> itemList;

    Context activityContext;

    public HistoryAdapter(ArrayList<Order> list, Context activityContext) {
        itemList = list;
        this.activityContext = activityContext;
    }
    class Holder extends RecyclerView.ViewHolder{
        public TextView itemName, date, orderId, variant, size, price, total, status;
        public Holder(View itemView){
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.history_itemname);
            date = (TextView) itemView.findViewById(R.id.history_date);
            orderId = (TextView) itemView.findViewById(R.id.history_orderId);
            variant = (TextView) itemView.findViewById(R.id.history_variant);
            size = (TextView) itemView.findViewById(R.id.history_size);
            price = (TextView) itemView.findViewById(R.id.history_price);
            total = (TextView) itemView.findViewById(R.id.history_total);
            status = (TextView) itemView.findViewById(R.id.history_status);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_history, parent, false);

        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Holder holder, int position) {
        Order curr = itemList.get(position);
        holder.itemName.setText(curr.getItem().getName());
        holder.date.setText(curr.getDate());
        holder.orderId.setText(Integer.toString(curr.getId()));
        holder.variant.setText("Variant: " + curr.getItem().getVariant());
        holder.size.setText("Size: " + curr.getItem().getSize());
        holder.price.setText(Integer.toString(curr.getQty()) + " x " + Integer.toString(curr.getItem().getPrice()));
        holder.total.setText("Total: " + Integer.toString(curr.getQty()*curr.getItem().getPrice()));
        holder.status.setText(curr.getStatus());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
