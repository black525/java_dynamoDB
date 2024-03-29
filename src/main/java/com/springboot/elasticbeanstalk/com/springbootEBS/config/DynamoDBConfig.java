package com.springboot.elasticbeanstalk.com.springbootEBS.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {


    public static final String SERVICE_END_POINT = "dynamodb.us-east-1.amazonaws.com";
    public static final String REGION = "us-east-1";
    public static final String ACCESS_KEY = "AKIAVNIPDPZMN25AGJNB";
    public static final String SECRET_KEY = "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";

    @Bean
   public DynamoDBMapper mapper(){
       return new DynamoDBMapper(amazonDynamoDBClientConfig());
   }

   private AmazonDynamoDB amazonDynamoDBClientConfig(){
       return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_END_POINT,REGION)).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY))).build();
   }
}