package com.romans91.imgstorey.datastore;

import com.romans91.imgstorey.storey.*;
import com.romans91.imgstorey.storey.StoreyElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
public class StoreyDataStore {

    private final StoreyRepository storeyRepository;
    private final StoreyElementRepository storeyElementRepository;

    @Autowired
    public StoreyDataStore(StoreyRepository storeyRepository, StoreyElementRepository storeyElementRepository) {
        this.storeyRepository = storeyRepository;
        this.storeyElementRepository = storeyElementRepository;
    }

    public Storey getStorey(String storeyId) {
        Storey storey = storeyRepository.findById(storeyId).get();

        if (storey == null)
            throw new NoSuchElementException(String.format("No storey with an id of \"%s\" was found in the database.",
                    storeyId));

        return storey;
    }

    public ArrayList<StoreyElement> getElementsOfStorey(String storeyId) {
        StoreyElement storeyElement = new StoreyElement(storeyId, null, null);
        Example<StoreyElement> example = Example.of(storeyElement);

        return new ArrayList<>(storeyElementRepository.findAll(example));
    }

    public Storey createNewStorey() {
        Storey storey = new Storey(generateRandomUniqueStoreyId(), UUID.randomUUID());
        storeyRepository.save(storey);

        return storey;
    }

    public String appendElementToStorey(String storeyId, StoreyElementType entryType, String contents) {
        Storey storey = storeyRepository.findById(storeyId).get();

        if (storey == null)
            throw new NoSuchElementException(String.format("No storey with an id of \"%s\" was found in the database.",
                    storeyId));

        StoreyElement savedStoreyElement = storeyElementRepository.save(new StoreyElement(storeyId, entryType, contents));

        return savedStoreyElement.getContents();
    }

    public String generateRandomUniqueStoreyId() {
        boolean uniqueIdHasBeenGenerated = false;
        String id = "";

        while (!uniqueIdHasBeenGenerated) {
            id = generateRandomStoreyId(8);
            if (!storeyRepository.existsById(id))
                uniqueIdHasBeenGenerated = true;
        }

        return id;
    }

    private String generateRandomStoreyId(int length) {
        String id = "";
        String storeyIdAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        // TODO use util instead of alphabet
        for (int i = 0; i < length; i++) {
            id += storeyIdAlphabet.toCharArray()[(int)(Math.random() * storeyIdAlphabet.length())];
        }

        return id;
    }
}
