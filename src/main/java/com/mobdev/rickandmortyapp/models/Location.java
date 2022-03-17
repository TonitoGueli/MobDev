package com.mobdev.rickandmortyapp.models;

import lombok.Data;

import javax.persistence.*;

/**
 * This class getter's and setter's methods are implemented by Lombok through the @Data annotation.
 * If you wish to see each of the methods created by Lombok, just follow the next steps:
 * right click on Location > Refactor > DLombok > "All Lombok Annotations.
 */
@Entity
@Table(name = "Locations")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long location_pkey;

    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String name;

    @Column(name = "location_url")
    private String url;

    @Column(name = "location_dimension")
    private String dimension;

    @Column(name = "location_residents")
    @Lob
    private byte[] residents;

}
