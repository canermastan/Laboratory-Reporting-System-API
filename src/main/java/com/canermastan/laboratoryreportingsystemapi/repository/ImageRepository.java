package com.canermastan.laboratoryreportingsystemapi.repository;

import com.canermastan.laboratoryreportingsystemapi.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageData, Long> {
    ImageData findById(String id);
}
