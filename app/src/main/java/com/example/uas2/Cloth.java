package com.example.uas2;

import android.os.Parcel;

import androidx.annotation.NonNull;

public class Cloth extends Item{
    public static final int CLOTHSIZE_S = 1;
    public static final int CLOTHSIZE_M = 2;
    public static final int CLOTHSIZE_L = 3;
    public static final int CLOTHSIZE_XL = 4;
    public static final int CLOTHSIZE_XXL = 5;


    public Cloth(String name, String variant, int size, int price) {
        super(name, variant, size, price);
    }

    @Override
    public String getSize() {
        switch(this.size){
            case 1:
                return "S";
            case 2:
                return "M";
            case 3:
                return "L";
            case 4:
                return "XL";
            case 5:
                return "XXL";
            default:
                return "-";
        }
    }

    @Override
    public void setSize(int size) {

    }

    //region parcelable methods
    protected Cloth(Parcel in){
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
        parcel.writeInt(CLASS_CLOTH);

        parcel.writeString(name);
        parcel.writeString(variant);
        parcel.writeInt(size);
        parcel.writeInt(price);
    }
    //endregion
}
