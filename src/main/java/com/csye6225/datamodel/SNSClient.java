package com.csye6225.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;

public class SNSClient {
    private static AmazonSNS snsClient;

    public static AmazonSNS getClient() {
        if (snsClient == null) {
//            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
//            credentialsProvider.getCredentials();

            snsClient = AmazonSNSClient.builder()
                    .withRegion(Regions.US_WEST_2)
                    .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                    .build();

            System.out.println("I created the client");
        }
        return snsClient;
    }
}
