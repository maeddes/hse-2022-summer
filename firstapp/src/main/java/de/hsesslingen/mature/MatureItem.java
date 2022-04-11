package de.hsesslingen.mature;

public class MatureItem {

    private String item;
    private int amount;  

    public MatureItem(String item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public MatureItem() {
    }

    public MatureItem(String item) {
        this.item = item;
        this.amount = 1;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MatureItem [amount=" + amount + ", item=" + item + "]";
    }

}
