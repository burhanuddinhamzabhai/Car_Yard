//package com.app.caryard;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBHandler extends SQLiteOpenHelper {
//
//    private static final int DATABASE_VERSION = 1;
//    private static final String DATABASE_NAME = "cars.db";
//    private static  final String TABLE_CARS = "cars";
//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_MODEL = "model";
//    private static final String COLUMN_YEAR = "year";
//    private static final String COLUMN_COLOR = "color";
//    private static final String COLUMN_USAGE = "usage";
//    private static final String COLUMN_PRICE = "price";
//
//
//    String CREATE_CARS_TABLE = "CREATE TABLE " + TABLE_CARS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
//            + COLUMN_MODEL + " TEXT, " + COLUMN_YEAR + " TEXT, " + COLUMN_COLOR + " TEXT, " + COLUMN_USAGE + " TEXT, " +
//            COLUMN_PRICE + " TEXT )" ;
//
//    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    public void addCar(Car car){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_MODEL,car.getModel());
//        contentValues.put(COLUMN_YEAR,car.getYear());
//        contentValues.put(COLUMN_COLOR,car.getColor());
//        contentValues.put(COLUMN_USAGE,car.getUsage());
//        contentValues.put(COLUMN_PRICE,car.getPrice());
//        database.insert(TABLE_CARS,null,contentValues);
//        Log.d("database", "addCar: added");
//        database.close();
//    }
//
//    public Car getCar(int id){
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor cursor = database.query(TABLE_CARS,new String[]{COLUMN_ID,COLUMN_MODEL,COLUMN_YEAR,COLUMN_COLOR,COLUMN_USAGE,COLUMN_PRICE},
//                COLUMN_ID + "=?", new String[]{String.valueOf(id)},null,null,null);
//        if (cursor != null){
//            cursor.moveToFirst();
//        }
//        Car car = new Car(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
//        return car;
//    }
//
//    public List<Car> getCars(){
//        List<Car> cars = new ArrayList<>();
//        String SELECT_QUERY = "SELECT * FROM " + TABLE_CARS;
//        SQLiteDatabase database = getReadableDatabase();
//        Cursor cursor = database.rawQuery(SELECT_QUERY,null);
//            if (cursor.moveToNext()){
//                do {
//                    Car car = new Car();
//                   // car.setId(Integer.parseInt(cursor.getString(0)));
//                    car.setModel(cursor.getString(1));
//                    car.setYear(cursor.getString(2));
//                    car.setColor(cursor.getString(3));
//                    car.setUsage(cursor.getString(4));
//                    car.setPrice(cursor.getString(5));
//                    cars.add(car);
//                }
//                while (cursor.moveToNext());
//            }
//        return cars;
//    }
//
//    public int updateCar(Car car){
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_MODEL,car.getModel());
//        contentValues.put(COLUMN_YEAR,car.getYear());
//        contentValues.put(COLUMN_COLOR,car.getColor());
//        contentValues.put(COLUMN_USAGE,car.getUsage());
//        contentValues.put(COLUMN_PRICE,car.getPrice());
//
//        return database.update(TABLE_CARS,contentValues,COLUMN_ID + "=?",
//                new String[]{ String.valueOf(car.getId())});
//    }
//
//    public boolean deleteCar(int id){
//        SQLiteDatabase database = this.getWritableDatabase();
//        database.delete(TABLE_CARS,COLUMN_ID + "=?", new String[]{String.valueOf(id)});
//        database.close();
//        return true;
//    }
//}
