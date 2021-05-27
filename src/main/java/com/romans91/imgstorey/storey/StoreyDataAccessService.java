package com.romans91.imgstorey.storey;

import com.romans91.imgstorey.datastore.StoreyDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class StoreyDataAccessService {
    private final StoreyDataStore storeyDataStore;

    @Autowired
    public StoreyDataAccessService(StoreyDataStore storeyDataStore) {
        this.storeyDataStore = storeyDataStore;
    }

    public Storey getStorey(String storeyId) {
        return storeyDataStore.getStorey(storeyId);
    }

    public Storey createNewStorey() {
        return storeyDataStore.createNewStorey();
    }

    public ArrayList<StoreyElement> getStoreyElements(String storeyId) {
        return storeyDataStore.getElementsOfStorey(storeyId);
    }

    public String appendElementToStorey(String storeyId, StoreyElementType entryType, String contents) {
        return storeyDataStore.appendElementToStorey(storeyId, entryType, contents);
    }
}
