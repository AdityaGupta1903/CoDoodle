package com.example.CoDoddleBE.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Sketches")
public class Sketches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String SketchData;

}
