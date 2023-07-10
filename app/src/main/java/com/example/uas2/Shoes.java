package com.example.uas2;

import android.os.Parcel;

import androidx.annotation.NonNull;

public class Shoes extends Item{
    public Shoes(String name, String variant, int size, int price) {
        super(name, variant, size, price);
    }

    @Override
    public String getSize() {
        return null;
    }

    @Override
    public void setSize(int size) {

    }

    //region parcelable methods
    protected Shoes(Parcel in){
        name = in.readString();
        variant = in.readString();
        size = in.readInt();
        price = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        //identifier
        parcel.writeInt(CLASS_SHOES);

        parcel.writeString(name);
        parcel.writeString(variant);
        parcel.writeInt(size);
        parcel.writeInt(price);
    }
    //endregion
}
