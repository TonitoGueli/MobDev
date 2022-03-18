package com.mobdev.rickandmortyapp.repository;

import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Interface to implement JPARepository methods.
* */
@Repository
public interface ShowCharacterRepository extends JpaRepository<ShowCharacterResponseDTO,Long> {

}
