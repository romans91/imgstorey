package com.romans91.imgstorey.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class S3FileStore {

    private final AmazonS3 s3;

    @Autowired
    public S3FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();

        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metadata::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, inputStream, metadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException(
                    String.format("Failed to store file in the S3 at \"%s\\%s\".", path, fileName), e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            if (s3.doesObjectExist(path, key)) {
                S3Object object = s3.getObject(path, key);

                return IOUtils.toByteArray(object.getObjectContent());
            } else {
                throw new IllegalStateException(
                        String.format("No file exists the S3 at \"%s\\%s\".", path, key));
            }
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException(
                    String.format("Failed to download file from the S3 at \"%s\\%s\".", path, key), e);
        }
    }
}
