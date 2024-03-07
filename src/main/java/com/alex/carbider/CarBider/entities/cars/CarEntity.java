package com.alex.carbider.CarBider.entities.cars;

import com.alex.carbider.CarBider.entities.images.ImageEntity;
import com.alex.carbider.CarBider.entities.user.UserEntity;
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
    private int currentBid;
    private int buyOutPrice;
    private boolean bought;


    private String email;

    @ManyToOne()
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "users_id_winnings")
    private UserEntity user_winnings;

    public CarEntity(long id, boolean bought, int currentBid, String email, String title, String desc, int startingBid, int buyOutPrice, UserEntity user, UserEntity user_winnings) {
        this.id = id;
        this.title = title;
        this.description = desc;
        this.startingBid = startingBid;
        this.buyOutPrice = buyOutPrice;
        this.user = user;
        this.user_winnings = user_winnings;
        this.email = email;
        this.currentBid = currentBid;
        this.bought = bought;
    }

    public CarEntity() {
    }


    public UserEntity getUser_winnings() {
        return user_winnings;
    }

    public void setUser_winnings(UserEntity user_winnings) {
        this.user_winnings = user_winnings;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
