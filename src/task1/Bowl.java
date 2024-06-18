package task1;

public class Bowl {
    private int feedAmount = 0;

    public void pourFoodInBowl(int feedAmount) {
        this.feedAmount = feedAmount;
    }

    public boolean eatFromBowl(int amountFoodEaten) {
        if ((feedAmount - amountFoodEaten) < 0) {
            return false;
        } else {
            feedAmount -= amountFoodEaten;
            return true;
        }
    }
}
