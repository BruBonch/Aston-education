package task1;

public class Bowl {
    private int feedAmount = 0;

    public int getFeedAmount() {
        return feedAmount;
    }

    public void pourFoodInBowl() {
        feedAmount = 50;
    }

    public void eatFromBowl(int amountFoodEaten) {
        feedAmount -= amountFoodEaten;
    }
}
