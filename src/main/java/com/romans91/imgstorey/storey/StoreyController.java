package com.romans91.imgstorey.storey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/storey")
@CrossOrigin("*")
public class StoreyController {

    private final StoreyService storeyService;

    @Autowired
    public StoreyController(StoreyService storeyService) {
        this.storeyService = storeyService;
    }

    @GetMapping("create")
    public Storey createStorey() {
        Storey newStorey = storeyService.addNewStorey();

        return newStorey;
    }

    @GetMapping("{storeyId}")
    public Storey getStorey(@PathVariable("storeyId") String storeyId) {
        return storeyService.getStorey(storeyId);
    }

    @GetMapping("{storeyId}/elements")
    public List<StoreyElement> getStoreyElements(@PathVariable("storeyId") String storeyId) {
        return storeyService.getStoreyElements(storeyId);
    }

    @PostMapping(
            path = "{storeyId}/uploadImage",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> appendImageToStorey(@PathVariable("storeyId") String storeyId,
                                                      @RequestParam("file") MultipartFile file) {

        String imageFilenameInS3 = storeyService.appendElementToStorey(
                storeyId, StoreyElementType.IMAGE_URI, storeyService.storeImageInS3(file, storeyId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "StoreyController");

        return ResponseEntity.accepted().headers(headers).body(imageFilenameInS3);
    }

    @PostMapping(
            path = "{storeyId}/uploadText",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> appendTextToStorey(@PathVariable("storeyId") String storeyId,
                                                     @RequestParam("text") String text) {
        String newText = storeyService.appendElementToStorey(storeyId, StoreyElementType.TEXT_BLOCK, text);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "StoreyController");

        return ResponseEntity.accepted().headers(headers).body(newText);
    }
}
