package com.romans91.imgstorey.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class AwsKeys {
    private String awsAccessKeyId;
    private String awsSecretKey;

    protected AwsKeys(String awsCredentialsFilename) {
        try (BufferedReader br = new BufferedReader(new FileReader(awsCredentialsFilename))) {
            for(String line; (line = br.readLine()) != null; ) {
                String[] keyValuePair = line.split("=");
                if (keyValuePair.length == 2) {
                    switch(keyValuePair[0]) {
                        case "AWSAccessKeyId":
                            awsAccessKeyId = keyValuePair[1];
                            break;
                        case "AWSSecretKey":
                            awsSecretKey = keyValuePair[1];
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(String.format("AWS Credentials file not found in \"%s\".", awsCredentialsFilename));
        }

        if (awsAccessKeyId == null || awsSecretKey == null) {
            throw new IllegalStateException(String.format("The file found in \"%s\" may not contain valid AWS credentials.", awsCredentialsFilename));
        }
    }

    protected String getAwsSecretKey() {
        return awsSecretKey;
    }

    protected String getAwsAccessKeyId() {
        return awsAccessKeyId;
    }
}
