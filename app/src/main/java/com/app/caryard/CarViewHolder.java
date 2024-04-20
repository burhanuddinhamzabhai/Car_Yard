package com.app.caryard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class CarViewHolder extends RecyclerView.ViewHolder {
private TextView carModel;
private TextView carYear;
private TextView carColor;
private TextView carUsage;
private TextView carPrice;

public CarViewHolder(View itemView) {
        super(itemView);
        carModel = itemView.findViewById(R.id.car_model_text);
        carYear = itemView.findViewById(R.id.car_year_text);
        carColor = itemView.findViewById(R.id.car_color_text);
        carUsage = itemView.findViewById(R.id.car_usage_text);
        carPrice = itemView.findViewById(R.id.car_price_text);
        }

public void bind(Car car) {
        carModel.setText(car.getModel());
        carYear.setText(car.getYear());
        carColor.setText(car.getColor());
        carUsage.setText(car.getUsage());
        carPrice.setText(car.getPrice());
        }
        }