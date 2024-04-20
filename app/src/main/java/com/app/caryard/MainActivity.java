package com.app.caryard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CarAdapter.OnCarClickListener {

    private RecyclerView carListRecyclerView;
    private ViewPager2 carDetailsViewPager;
    private CarAdapter carAdapter;
    private List<Car> cars; // List of cars data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carListRecyclerView = findViewById(R.id.car_list_recycler_view);
        Car car = new Car();
        SharedPreferences sharedPreferences = getSharedPreferences("CarsDB",MODE_PRIVATE);
        // creating a variable for gson.
        Gson gson = new Gson();
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("cars", null);
        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<Car>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        cars = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (cars == null) {
            // if the array list is empty
            car.initializeCars();
            cars = car._cars;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            json = gson.toJson(cars);
            // below line is to save data in shared
            // prefs in the form of string.
            editor.putString("cars", json);

            // below line is to apply changes
            // and save data in shared prefs.
            editor.apply();
        }

        carAdapter = new CarAdapter(cars,carClickListener);
        carListRecyclerView.setAdapter(carAdapter);
        carListRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Add click listener for new car button (implementation depends on your layout)
        Button newCarButton = findViewById(R.id.new_car_button);
        newCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add new car instance (with empty fields)\

                Intent intent = new Intent(MainActivity.this,CarDetailsActivity.class);
                intent.putExtra("data", (String) null);
                startActivity(intent);
            }
        });



    }


    // Create an instance of OnCarClickListener in your activity or fragment
    private CarAdapter.OnCarClickListener carClickListener = new CarAdapter.OnCarClickListener() {
        @Override
        public void onCarClick(Car car) {
            // Handle the click event here
            Intent intent = new Intent(MainActivity.this,CarDetailsActivity.class);
            String carDetails = car.getId() + "#" + car.getModel() + "#" + car.getYear() + "#" + car.getColor() + "#" + car.getUsage() + "#" + car.getPrice();
            intent.putExtra("carDetails", carDetails);
            startActivity(intent);
        }
    };
    @Override
    public void onCarClick(Car car) {
        // Update ViewPager with the clicked car data

    }

}