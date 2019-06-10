package com.company;
public class Player extends Character {

    private int numberOfLives;
    public enum collectables{ //items that are collected physically by running into it
        ONEUP,
        MEGAMUSHROOM,
        FIREBALL,
        SNOWBALL,
        INVICIBILESTAR,

    }



    public Player(Sprite player){
        super(player);
        this.numberOfLives = 3;
    }


    public void getCollectable(collectables item){
        switch (item){
            case ONEUP: break;
            case MEGAMUSHROOM: break;
            case FIREBALL: break;
            case SNOWBALL: break;
            case INVICIBILESTAR: break;
        }
    }
}