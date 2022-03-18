package com.mobdev.rickandmortyapp.repository;

import com.mobdev.rickandmortyapp.dto.LocationResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationResponseDTO,Long> {

}
