package com.app.caryard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Car implements Serializable, Parcelable {
    private String id;
    private String model;
    private String year;
    private String color;
    private String usage;
    private String price;

    public List<Car> _cars = new ArrayList<>();


    public Car(String id, String model, String year, String color, String usage, String price) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.color = color;
        this.usage = usage;
        this.price = price;
    }

    public Car(String model, String year, String color, String usage, String price) { // Constructor without id for new car
        this.model = model;
        this.year = year;
        this.color = color;
        this.usage = usage;
        this.price = price;
    }

    public Car(){
        super();
    }

    protected Car(Parcel in) {
        id = in.readString();
        model = in.readString();
        year = in.readString();
        color = in.readString();
        usage = in.readString();
        price = in.readString();
        _cars = in.createTypedArrayList(Car.CREATOR);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public void initializeCars(){
        _cars.add(new Car(UUID.randomUUID().toString(),"BMW 320","2015","Grey", "5 months Rego", "$32,600"));
        _cars.add(new Car(UUID.randomUUID().toString(),"Toyota Camry","2010","White", "10 months Rego", "$7,200"));
        _cars.add(new Car(UUID.randomUUID().toString(),"Mazda 3","2012","Red", "2 months Rego", "$4,800"));
        _cars.add(new Car(UUID.randomUUID().toString(),"Nissan Pulsar","2012","Silver", "2 months Rego", "$3,200"));
        _cars.add(new Car(UUID.randomUUID().toString(),"Holden Captiva","2018","Black", "1 months Rego", "$18,300"));
        _cars.add(new Car(UUID.randomUUID().toString(),"Ford Lazer","2004","White", "12 months Rego", "$1100"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(model);
        parcel.writeString(year);
        parcel.writeString(color);
        parcel.writeString(usage);
        parcel.writeString(price);
        parcel.writeTypedList(_cars);
    }
}
