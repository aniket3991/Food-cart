package foodcart.entity;

public class Food {

    private int foodId;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public Food(){

    }

    public Food(int foodId, String name, double price, int quantity, String description) {
        this(name, price, quantity, description);
        this.foodId = foodId;
    }

    public Food(String name, double price, int quantity, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food Details " +
                "\nName: " + name +
                "\nPrice: " + price +
                "\nQuantity: " + quantity +
                "\nDescription: " + description + '\n';
    }
}
