package com.example.uas2;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class Item implements Parcelable {
    public static final int CLASS_CLOTH = 1;
    public static final int CLASS_SHOES = 2;

    String name;
    String variant;
    int size, price;

    protected Item(){

    }
    public Item(String name, String variant, int size, int price) {
        this.name = name;
        this.variant = variant;
        this.size = size;
        this.price = price;
    }

    public static Item getChildInstance(Parcel in){
        switch(in.readInt()){
            case CLASS_CLOTH:
                return new Cloth(in);
            case CLASS_SHOES:
                return new Shoes(in);
            default:
                return null;
        }
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return Item.getChildInstance(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getVariant() {
        return variant;
    }

    public int getPrice() {
        return price;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public abstract String getSize();
    public abstract void setSize(int size);
}
