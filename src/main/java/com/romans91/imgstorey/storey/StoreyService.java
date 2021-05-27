package com.romans91.imgstorey.storey;

import com.romans91.imgstorey.config.AwsS3Properties;
import com.romans91.imgstorey.filestore.S3FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class StoreyService {
    private final StoreyDataAccessService storeyDataAccessService;
    private final S3FileStore s3FileStore;

    @Autowired
    public StoreyService(StoreyDataAccessService storeyDataAccessService, S3FileStore s3FileStore) {
        this.storeyDataAccessService = storeyDataAccessService;
        this.s3FileStore = s3FileStore;
    }

    public Storey getStorey(String storeyId) {
        Storey storey = storeyDataAccessService.getStorey(storeyId);

        return storey;
    }

    public Storey addNewStorey() {
        return storeyDataAccessService.createNewStorey();
    }

    public ArrayList<StoreyElement> getStoreyElements(String storeyId) {
        ArrayList<StoreyElement> storeyElements = storeyDataAccessService.getStoreyElements(storeyId);

        return storeyElements;
    }

    public String appendElementToStorey(String storeyId, StoreyElementType entryType, String contents) {
        return storeyDataAccessService.appendElementToStorey(storeyId, entryType, contents);
    }

    public String storeImageInS3(MultipartFile file, String parentStoreyId) {
        if (file.isEmpty()) {
            throw new IllegalStateException(String.format("Cannot upload an empty file \"%s\".", file.getName()));
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException(String.format("Uploaded file \"%s\" must be an image.", file.getName()));
        }

        String fileExtension = file.getContentType().split("/")[1];

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String pathInS3Bucket = String.format("%s/%s/%s",
                AwsS3Properties.IMAGES_BUCKET.getName(), AwsS3Properties.IMAGES_FOLDER.getName(), parentStoreyId);

        String filenameInS3Bucket = String.format("%s.%s", System.currentTimeMillis(), fileExtension);

        try {
            s3FileStore.save(pathInS3Bucket, filenameInS3Bucket, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return filenameInS3Bucket;
    }
}
