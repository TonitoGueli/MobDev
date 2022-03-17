package com.mobdev.rickandmortyapp.repository;

import com.mobdev.rickandmortyapp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

}
