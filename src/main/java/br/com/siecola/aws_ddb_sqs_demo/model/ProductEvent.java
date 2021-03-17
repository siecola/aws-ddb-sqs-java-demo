package br.com.siecola.aws_ddb_sqs_demo.model;

public class ProductEvent {

    private final String username;
    private final String name;
    private final String code;
    private final String model;
    private final float price;
    private final long timestamp;

    public ProductEvent (Product product, long timestamp) {
        this.username = product.getUsername();
        this.name = product.getName();
        this.code = product.getCode();
        this.model = product.getModel();
        this.price = product.getPrice();
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public float getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }
}