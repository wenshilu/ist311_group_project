package mealrater.siyuxiang.com.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.SQLException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RatingDataSource {

    private SQLiteDatabase database;
    private RatingDBHelper dbHelper;


    public RatingDataSource (Context context) {
        dbHelper = new RatingDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertRating (Rating r) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("restaurant", r.getRestaurantName());
            initialValues.put("dish", r.getDishName());
            initialValues.put("rating", r.getRatingNumber());

            didSucceed = database.insert("rating", null, initialValues) > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    public ArrayList<String> getRestaurant() {
        ArrayList<String> restaurants = new ArrayList<String>();
        try {
            String query = "Select DISTINCT(restaurant) from rating";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                restaurants.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            restaurants = new ArrayList<String>();
        }
        return restaurants;
    }

    public String getRating (String selectedRestaurant) {
        String averageRating = "0";
        Cursor cursor = null;

        try {
            String query = "SELECT AVG(rating) FROM rating WHERE restaurant ='" +
                    selectedRestaurant + "'";
            cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                averageRating = String.valueOf(cursor.getDouble(0));
            }
            cursor.close();
        }
        catch (Exception e) {
            cursor.close();
        }
        return averageRating;
    }

    public int getLastRatingId() {
        int lastId = -1;
        try {
            String query = "Select MAX(_id) from rating";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }


}
