package com.romans91.imgstorey.storey;

import javax.persistence.*;

@Entity
public class StoreyElement {

    @Id
    @SequenceGenerator(
            name = "storey_element_id_sequence",
            sequenceName = "storey_element_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "storey_element_id_sequence"
    )
    private Long id;
    private final String storeyId;

    private StoreyElementType type;
    private String contents;

    protected StoreyElement() {
        this.storeyId = "";
    }

    public StoreyElement(String storeyId, StoreyElementType type, String contents) {
        this.storeyId = storeyId;
        this.type = type;
        this.contents = contents;
    }

    public StoreyElementType getType() {
        return type;
    }

    public void setContents(String storeyElementContents) {
        this.contents = storeyElementContents;
    }

    public String getContents() {
        return contents;
    }
}

