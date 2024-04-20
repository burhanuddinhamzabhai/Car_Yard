package com.app.caryard;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<Car> cars;
    private OnCarClickListener listener;


    public CarAdapter(){
        super();
        Car car = new Car();
        cars = car._cars;
    }

    public CarAdapter(List<Car> cars, OnCarClickListener carClickListener) {
        this.cars = cars;
        listener = carClickListener;
    }
    public interface OnCarClickListener {
        void onCarClick(Car car);
    }
    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item_layout, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Car car = cars.get(position);
        holder.bind(car);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCarClick(car);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void addCar(Car car) {
        cars.add(car);
        notifyItemInserted(cars.size() - 1); // Notify adapter of new item
    }






}