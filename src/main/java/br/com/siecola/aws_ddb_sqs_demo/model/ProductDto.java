package br.com.siecola.aws_ddb_sqs_demo.model;

public class ProductDto {

    private String username;
    private String name;
    private String code;
    private String model;
    private float price;

    public ProductDto(){}

    public ProductDto(Product product) {
        this.username = product.getUsername();
        this.name = product.getName();
        this.code = product.getCode();
        this.model = product.getModel();
        this.price = product.getPrice();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}