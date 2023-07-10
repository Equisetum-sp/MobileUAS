package com.example.uas2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order implements Parcelable {
    public static final int ORDERSTATUS_CANCELLED = 0;
    public static final int ORDERSTATUS_WAITINGCONFIRM = 1;
    public static final int ORDERSTATUS_SENT = 2;
    public static final int ORDERSTATUS_FINISHED = 3;

    Item item;
    int id, qty, status;
    String date;

    public Order(int id, Item item, int qty) {
        this.id = id;
        this.item = item;
        this.qty = qty;
        this.status = 1;
        this.date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    public Order(int id, Item item, int qty, Date date) {
        this.id = id;
        this.item = item;
        this.qty = qty;
        this.status = 1;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    //region getter
    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getQty() {
        return qty;
    }

    public String getStatus() {
        switch(this.status){
            case 0:
                return "Dibatalkan";
            case 1:
                return "Menunggu konfirmasi";
            case 2:
                return "Sedang dikirim";
            case 3:
                return "Selesai";
            default:
                return "-";
        }
    }

    public String getDate() {
        return date;
    }

    //endregion

    //region setter

    public void setStatus(int status) {
        this.status = status;
    }

    //endregion


    //region parcelable method
    protected Order(Parcel in) {
        id = in.readInt();
        item = (Item) in.readParcelable(Item.class.getClassLoader());
        qty = in.readInt();
        status = in.readInt();
        date = in.readString();
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeParcelable(item, flags);
        parcel.writeInt(qty);
        parcel.writeInt(status);
        parcel.writeString(date);
    }
    //endregion
}
