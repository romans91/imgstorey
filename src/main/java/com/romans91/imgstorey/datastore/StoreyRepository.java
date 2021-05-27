package com.romans91.imgstorey.datastore;

import com.romans91.imgstorey.storey.Storey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreyRepository extends JpaRepository<Storey, String> {
}
