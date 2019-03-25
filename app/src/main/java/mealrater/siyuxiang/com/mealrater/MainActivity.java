package mealrater.siyuxiang.com.mealrater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSaveButton();
        iniRatingButton();
    }

    public void onResume() {
        super.onResume();
        initScreen();
    }

    private void initSaveButton() {
        Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editRestaurant = (EditText) findViewById(R.id.editRestaurant);
                EditText editDish = (EditText) findViewById(R.id.editDish);
                RadioButton rb1 = (RadioButton) findViewById(R.id.dishRating_1);
                RadioButton rb2 = (RadioButton) findViewById(R.id.dishRating_2);
                RadioButton rb3 = (RadioButton) findViewById(R.id.dishRating_3);
                RadioButton rb4 = (RadioButton) findViewById(R.id.dishRating_4);
                RadioButton rb5 = (RadioButton) findViewById(R.id.dishRating_5);

                Rating r = new Rating();
                r.setRestaurantName(editRestaurant.getText().toString());
                r.setDishName(editDish.getText().toString());

                if (rb1.isChecked()) {
                    r.setRatingNumber(1);
                }
                else if (rb2.isChecked()) {
                    r.setRatingNumber(2);
                }
                else if (rb3.isChecked()) {
                    r.setRatingNumber(3);
                }
                else if (rb4.isChecked()) {
                    r.setRatingNumber(4);
                }
                else if (rb5.isChecked()) {
                    r.setRatingNumber(5);
                }

                boolean wasSuccessful = false;
                RatingDataSource ds = new RatingDataSource(MainActivity.this);
                ds.open();
                int newId = ds.getLastRatingId();
                r.setRatingID(newId);
                wasSuccessful = ds.insertRating(r);
                ds.close();

                if (wasSuccessful) {
                    initScreen();
                }
            }
        });
    }

    private void initScreen() {
        EditText editRestaurant = (EditText) findViewById(R.id.editRestaurant);
        EditText editDish = (EditText) findViewById(R.id.editDish);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioDishRating);

        editRestaurant.setText("");
        editDish.setText("");
        rg.clearCheck();
    }

    private void iniRatingButton() {
        Button ratingButton = (Button) findViewById(R.id.buttonRating);
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
