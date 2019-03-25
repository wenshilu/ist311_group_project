package mealrater.siyuxiang.com.mealrater;

public class Rating {

    private int ratingID;
    private String restaurantName;
    private String dishName;
    private int ratingNumber;

    public Rating () {
        ratingNumber = 0;
        ratingID = -1;
    }

    public int getRatingID () {
        return ratingID;
    }

    public void setRatingID (int i) {
        ratingID = i;
    }

    public String getRestaurantName () {
        return restaurantName;
    }

    public void setRestaurantName (String s) {
        restaurantName = s;
    }

    public String getDishName () {
        return dishName;
    }

    public void setDishName(String s) {
        dishName = s;
    }

    public int getRatingNumber () {
        return ratingNumber;
    }

    public void setRatingNumber (int i) {
        ratingNumber = i;
    }
}
