package com.romans91.imgstorey.config;

public enum AwsS3Properties {
    IMAGES_BUCKET("imgstorey-images"),
    IMAGES_FOLDER("storeys");

    private final String awsS3PropertyName;

    AwsS3Properties(String awsS3PropertyName) {
        this.awsS3PropertyName = awsS3PropertyName;
    }

    public String getName() {
        return awsS3PropertyName;
    }
}
