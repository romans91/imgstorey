package com.romans91.imgstorey.datastore;

import com.romans91.imgstorey.storey.StoreyElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreyElementRepository extends JpaRepository<StoreyElement, UUID> {
}
