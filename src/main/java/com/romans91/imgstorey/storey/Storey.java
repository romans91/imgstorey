package com.romans91.imgstorey.storey;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Storey {

    @Id
    private final String id;
    private final UUID ownerId;

    protected Storey() {
        this.id = "";
        this.ownerId = UUID.randomUUID();
    }

    public Storey(String id, UUID ownerId) {
        this.id = id;
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}
