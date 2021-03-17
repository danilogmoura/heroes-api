package com.dgm.dio.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

    @Value("{amazon.dynamodb.endpoint}")
    private String amazonDynamodbEndpoint;

    @Value("{aws_region}")
    private String amazonAWSRegion;

    @Value("{aws_access_key_id}")
    private String amazonAWSAccessKey;

    @Value("{aws_secret_access_key}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials((AWSCredentialsProvider) amazonAWSCredentials())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamodbEndpoint, amazonAWSRegion))
                .build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }
}
