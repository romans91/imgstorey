package com.romans91.imgstorey.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonS3 s3() {
        AwsKeys awsKeys = new AwsKeys("rootkey.csv");
        Regions awsBucketRegion = Regions.AP_SOUTHEAST_2;

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                awsKeys.getAwsAccessKeyId(),
                awsKeys.getAwsSecretKey());

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsBucketRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
