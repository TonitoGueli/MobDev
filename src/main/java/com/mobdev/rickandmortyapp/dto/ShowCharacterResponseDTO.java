package com.mobdev.rickandmortyapp.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: Anthonny Gueli
 * This class getter's and setter's methods are implemented by Lombok through the @Data annotation.
 * If you wish to see each of the methods created by Lombok, just follow the next steps:
 * right click on Character > Refactor > DLombok > "All Lombok Annotations.
 */
@Entity
@Table(name = "characters")
@Getter
@Builder
public class ShowCharacterResponseDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long characters_pkey;

    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_status")
    private String status;

    @Column(name = "character_species")
    private String species;

    @Column(name = "character_type")
    private String type;

    @Column(name = "character_episode_count")
    private int episode_count;

    @Column(name = "character_origin")
    private String origin;

}
