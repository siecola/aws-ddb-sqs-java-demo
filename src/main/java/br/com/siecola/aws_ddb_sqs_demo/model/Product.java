package br.com.siecola.aws_ddb_sqs_demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "products")
public class Product {
    public Product() {}

    public Product(ProductDto productDto) {
        this.productKey = new ProductKey();
        this.productKey.setPk(productDto.getUsername());
        this.productKey.setSk(productDto.getCode());

        this.username = productDto.getUsername();
        this.name = productDto.getName();
        this.code = productDto.getCode();
        this.model = productDto.getModel();
        this.price = productDto.getPrice();
    }

    @Id
    private ProductKey productKey;

    @DynamoDBAttribute(attributeName = "username")
    private String username;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "code")
    private String code;

    @DynamoDBAttribute(attributeName = "model")
    private String model;

    @DynamoDBAttribute(attributeName = "price")
    private float price;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return this.productKey != null ? this.productKey.getPk() : null;
    }

    public void setPk(String pk) {
        if (this.productKey == null) {
            this.productKey = new ProductKey();
        }

        this.productKey.setPk(pk);
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return this.productKey != null ? this.productKey.getSk() : null;
    }

    public void setSk(String sk) {
        if (this.productKey == null) {
            this.productKey = new ProductKey();
        }

        this.productKey.setSk(sk);
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
