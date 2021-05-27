package com.romans91.imgstorey.image;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/i")
@CrossOrigin("*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{storeyId}/{imageId}")
    public byte[] downloadImage(@PathVariable("storeyId") String storeyId,
                                @PathVariable("imageId") String imageId) {
        byte[] imageBytes = imageService.fetchImageFromS3(String.format("%s/%s", storeyId, imageId));

        return imageBytes;
    }
}
