package br.com.siecola.aws_ddb_sqs_demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

public class ProductKey {
    public ProductKey() {}

    private String pk;
    private String sk;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }
}