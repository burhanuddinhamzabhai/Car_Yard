package com.app.caryard;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarDetailsFragment extends Fragment {
    private EditText carModelEditText;
    private EditText carYearEditText;
    private EditText carColorEditText;
    private EditText carUsageEditText;
    private EditText carPriceEditText;
    private Button saveButton;
    private Car currentCar; // Reference to the currently displayed car

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_details_fragment, container, false);

        carModelEditText = view.findViewById(R.id.car_model_edit_text);
        carYearEditText = view.findViewById(R.id.car_year_edit_text);
        carColorEditText = view.findViewById(R.id.car_color_edit_text);
        carUsageEditText = view.findViewById(R.id.car_usage_edit_text);
        carPriceEditText = view.findViewById(R.id.car_price_edit_text);
        saveButton = view.findViewById(R.id.save_button);
        Bundle bundle = getArguments();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = carModelEditText.getText().toString().trim();
                String year = carYearEditText.getText().toString().trim();
                String color = carColorEditText.getText().toString().trim();
                String usage = carUsageEditText.getText().toString().trim();
                String price = carPriceEditText.getText().toString().trim();
                if (model.isEmpty() || year.isEmpty() || color.isEmpty() || usage.isEmpty() || price.isEmpty()) {
                    Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_SHORT).show();
                } else if (bundle == null){
                    List<Car> _cars = new ArrayList<>();
                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("CarsDB", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("cars", null);
                    Type type = new TypeToken<ArrayList<Car>>() {}.getType();
                    _cars = gson.fromJson(json, type);
                    _cars.add(new Car(UUID.randomUUID().toString(),model,year,color,usage,price));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    json = gson.toJson(_cars);
                    editor.putString("cars", json);
                    editor.apply();
                    Snackbar.make(view, "Car added", Snackbar.LENGTH_SHORT).show();
                    v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
                }else{
                    List<Car> _cars = new ArrayList<>();
                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("CarsDB", MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("cars", null);
                    Type type = new TypeToken<ArrayList<Car>>() {}.getType();
                    _cars = gson.fromJson(json, type);
                    currentCar = (Car) bundle.getSerializable("car");

                    for (Car car: _cars){
                        if(car.getId().equals(currentCar.getId())){
                            car.setModel(model);
                            car.setYear(year);
                            car.setColor(color);
                            car.setUsage(usage);
                            car.setPrice(price);
                            break;
                        }
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    json = gson.toJson(_cars);
                    editor.putString("cars", json);
                    editor.apply();
                    Snackbar.make(view, "Car modified", Snackbar.LENGTH_SHORT).show();
                    v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
                }
            }
        });

        // Load car details based on arguments or activity/fragment communication
        if (bundle != null && bundle.containsKey("car")) {
            currentCar = (Car) bundle.getSerializable("car");
            carModelEditText.setText(currentCar.getModel());
            carYearEditText.setText(currentCar.getYear());
            carColorEditText.setText(currentCar.getColor());
            carUsageEditText.setText(currentCar.getUsage());
            carPriceEditText.setText(currentCar.getPrice());
        } else {
            // Handle new car case (clear fields)
            carModelEditText.setText("");
            carYearEditText.setText("");
            carColorEditText.setText("");
            carUsageEditText.setText("");
            carPriceEditText.setText("");
        }

        return view;
    }
}