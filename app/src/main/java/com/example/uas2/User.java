package com.example.uas2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User implements Parcelable {
    String name, email, pw;
    ArrayList<Order> orders;

    public User(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;

        this.orders = new ArrayList<>();
    }

    public boolean checkPw(String pw){
        return this.pw.equals(pw);
    }

    public void addOrder(Order newOrder){
        this.orders.add(newOrder);
    }

    //region getter
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    //endregion

    //region setter
    public void setRooms(ArrayList<Order> rooms) {
        this.orders = rooms;
    }
    public void setRoom(int index, Order order) {
        this.orders.set(index, order);
    }
    //endregion


    //region parcelable method
    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        pw = in.readString();
        orders = in.createTypedArrayList(Order.CREATOR);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(pw);
        parcel.writeList(orders);
    }
    //endregion
}
