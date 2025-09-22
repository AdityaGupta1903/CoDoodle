package com.example.CoDoddleBE.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "User_Details_Fk",referencedColumnName = "id")
//    ArrayList<Sketches> sketches = new ArrayList<>();

}
