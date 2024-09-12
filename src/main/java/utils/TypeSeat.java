package utils;

public enum TypeSeat {
    GENERAL ("general",10),
    PREMIUM ("premium",20);

    private final String type;
    private final double price;
    TypeSeat(String type, double price) {
        this.type = type;
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
