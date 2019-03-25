package mealrater.siyuxiang.com.mealrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RatingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myratings.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String CREATE_TABLE_RATING =
            "create table rating (_id integer primary key autoincrement, "
            + "restaurant text not null, dish text not null, "
            + "rating int);";

    public RatingDBHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RATING);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
