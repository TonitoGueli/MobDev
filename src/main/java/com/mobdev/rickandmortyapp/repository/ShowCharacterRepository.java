package com.mobdev.rickandmortyapp.repository;

import com.mobdev.rickandmortyapp.dto.ShowCharacterResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Anthonny Gueli
 * Interface to JPARepository methods.
 */
@Repository
public interface ShowCharacterRepository extends JpaRepository<ShowCharacterResponseDTO, Long> {

}
