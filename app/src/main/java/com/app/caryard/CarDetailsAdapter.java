package com.app.caryard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CarDetailsAdapter extends FragmentStatePagerAdapter {
    private Car car;

    CarDetailsAdapter(FragmentManager fm, Car carDetails) {
        super(fm);
        this.car = carDetails;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        CarDetailsFragment carDetailsFragment = new CarDetailsFragment();
        bundle.putParcelable("car", car);
        carDetailsFragment.setArguments(bundle);
        return carDetailsFragment;

    }

    @Override
    public int getCount() {
        return 1;
    }
}

//public class CarDetailsAdapter extends PagerAdapter {
//
//    private List<Car> cars;
//    private LayoutInflater layoutInflater;
//    private Context context;
//    private EditText carModelEditText;
//    private EditText carYearEditText;
//    private EditText carColorEditText;
//    private EditText carUsageEditText;
//    private EditText carPriceEditText;
//    private Button saveButton;
//
//    public CarDetailsAdapter(Context context, List < Car > cars) {
//
//        this.context = context;
//        this.cars = cars;
//        Log.d("TAG", "CarDetailsAdapter: "+ cars.get(0).getUsage());
//        layoutInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount () {
//        return cars.size();
//    }
//
//    @Override
//    public Object instantiateItem (ViewGroup container, int position){
//        View view = layoutInflater.inflate(R.layout.car_details_fragment, container, false);
//        carModelEditText = view.findViewById(R.id.car_model_edit_text);
//        carYearEditText = view.findViewById(R.id.car_year_edit_text);
//        carColorEditText = view.findViewById(R.id.car_color_edit_text);
//        carUsageEditText = view.findViewById(R.id.car_usage_edit_text);
//        carPriceEditText = view.findViewById(R.id.car_price_edit_text);
//        saveButton = view.findViewById(R.id.save_button);
//
//        carModelEditText.setText(cars.get(0).getModel());
//        carYearEditText.setText(cars.get(0).getYear());
//        carColorEditText.setText(cars.get(0).getColor());
//        carUsageEditText.setText(cars.get(0).getUsage());
//        carPriceEditText.setText(cars.get(0).getPrice());
//        container.addView(view);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String model = carModelEditText.getText().toString().trim();
//                String year = carYearEditText.getText().toString().trim();
//                String color = carColorEditText.getText().toString().trim();
//                String usage = carUsageEditText.getText().toString().trim();
//                String price = carPriceEditText.getText().toString().trim();
//                if (model.isEmpty() || year.isEmpty() || color.isEmpty() || usage.isEmpty() || price.isEmpty()) {
//                    Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_SHORT).show();
//                } else
//                if (cars.get(0).getId() == ""){
//                    List<Car> _cars = new ArrayList<>();
//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("CarsDB", MODE_PRIVATE);
//                    Gson gson = new Gson();
//                    String json = sharedPreferences.getString("cars", null);
//                    Type type = new TypeToken<ArrayList<Car>>() {}.getType();
//                    _cars = gson.fromJson(json, type);
//                    _cars.add(new Car(UUID.randomUUID().toString(),model,year,color,usage,price));
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    json = gson.toJson(_cars);
//                    editor.putString("cars", json);
//                    editor.apply();
//                    Snackbar.make(view, "Car added", Snackbar.LENGTH_SHORT).show();
//                    v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
//                }else{
//                    List<Car> _cars = new ArrayList<>();
//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("CarsDB", MODE_PRIVATE);
//                    Gson gson = new Gson();
//                    String json = sharedPreferences.getString("cars", null);
//                    Type type = new TypeToken<ArrayList<Car>>() {}.getType();
//                    _cars = gson.fromJson(json, type);
//
//                    for (Car car: _cars){
//                        Log.d("id", car.getId() + " - "+ cars.get(0).getId());
//                        if(car.getId().equals(cars.get(0).getId())){
//                            car.setModel(model);
//                            car.setYear(year);
//                            car.setColor(color);
//                            car.setUsage(usage);
//                            car.setPrice(price);
//                            break;
//                        }
//                    }
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    json = gson.toJson(_cars);
//                    editor.putString("cars", json);
//                    editor.apply();
//                    Snackbar.make(view, "Car modified", Snackbar.LENGTH_SHORT).show();
//                    v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
//                }
//
//            }
//        });
//
//        return view;
//    }
//
//    @Override
//    public boolean isViewFromObject (@NonNull View view, @NonNull Object object){
//        return view.equals(object);
//    }
//
//    @Override
//    public void destroyItem (@NonNull ViewGroup container,int position, @NonNull Object object){
//        container.removeView((View) object);
//    }
//}
