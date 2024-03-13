package com.alex.carbider.CarBider.entities.user.cars;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String desc;
    private int startingBid;
    private int buyOutPrice;
}
