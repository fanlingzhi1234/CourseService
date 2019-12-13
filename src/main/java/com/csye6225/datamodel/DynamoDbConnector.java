package com.csye6225.datamodel;

/**
 * @author Rexus
 * @date 2019-10-18
 */


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {

    static AmazonDynamoDB dynamoDB;

    public void init() {
        if (dynamoDB == null) {
//            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
//            credentialsProvider.getCredentials();

            dynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                    .withRegion("us-west-2")
                    .build();
            System.out.println("I created the client");
        }
    }

    public AmazonDynamoDB getClient() {
        init();
        return dynamoDB;
    }
}
