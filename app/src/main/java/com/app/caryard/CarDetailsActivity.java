package com.app.caryard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class CarDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_detail_activity);

        ViewPager viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.car_detail_toolbar);
        setSupportActionBar(toolbar);

        List<Car> cars = new ArrayList<>();
        Intent intent = getIntent();
        String carDetails = intent.getStringExtra("carDetails");
        if (carDetails == null){
            Car car = new Car("","","","","","");
            cars.add(car);
            getSupportActionBar().setTitle("New Car");
        }else{
            String[] car = carDetails.split("#");
            cars.add(new Car(car[0], car[1], car[2], car[3], car[4], car[5]));
            getSupportActionBar().setTitle("CarYard");
        }
        CarDetailsAdapter viewPagerAdapter = new CarDetailsAdapter(getSupportFragmentManager(),cars.get(0));
        viewPager.setAdapter(viewPagerAdapter);
    }
}
