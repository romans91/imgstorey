package com.romans91.imgstorey.image;

import com.romans91.imgstorey.config.AwsS3Properties;
import com.romans91.imgstorey.filestore.S3FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final S3FileStore s3FileStore;

    @Autowired
    public ImageService(S3FileStore s3FileStore) {
        this.s3FileStore = s3FileStore;
    }

    public byte[] fetchImageFromS3(String imageId) {
        String path = AwsS3Properties.IMAGES_BUCKET.getName() + "/" + AwsS3Properties.IMAGES_FOLDER.getName();

        try {
            byte[] imageBytes = s3FileStore.download(path, imageId);

            return imageBytes;
        } catch (IllegalStateException e) {
            return new byte[0];
        }
    }
}
