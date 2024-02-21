package com.alex.carbider.CarBider.entities.user.cars;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private int startingBid;
    private int buyOutPrice;

    public CarEntity(long id, String title, String desc, int startingBid, int buyOutPrice) {
        this.id = id;
        this.title = title;
        this.description = desc;
        this.startingBid = startingBid;
        this.buyOutPrice = buyOutPrice;
    }

    public CarEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(int startingBid) {
        this.startingBid = startingBid;
    }

    public int getBuyOutPrice() {
        return buyOutPrice;
    }

    public void setBuyOutPrice(int buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }
}
