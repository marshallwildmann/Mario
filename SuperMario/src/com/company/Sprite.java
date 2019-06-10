package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public enum Sprite {
    LUIGI,
    MARIO,
    YOSHI,
    TURTLE,
    GOOMBA,
    PIRANHA;

    static int jumpFactor = 10;

    public int getNumOfStandingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 1;
                break;
            case MARIO:
                numOfImages = 1;
                break;
            case YOSHI:
                numOfImages = 1;
                break;
            case TURTLE:
                numOfImages = 1;
                break;
            case GOOMBA:
                numOfImages = 1;
                break;
            case PIRANHA:
                numOfImages = 1;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public int getNumOfWalkingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 0;
                break;
            case MARIO:
                numOfImages = 5;
                break;
            case YOSHI:
                numOfImages = 0;
                break;
            case TURTLE:
                numOfImages = 0;
                break;
            case GOOMBA:
                numOfImages = 2;
                break;
            case PIRANHA:
                numOfImages = 0;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public int getNumOfJumpingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 0;
                break;
            case MARIO:
                numOfImages = 1;
                break;
            case YOSHI:
                numOfImages = 0;
                break;
            case TURTLE:
                numOfImages = 0;
                break;
            case GOOMBA:
                numOfImages = 0;
                break;
            case PIRANHA:
                numOfImages = 0;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public int getNumOfDuckingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 0;
                break;
            case MARIO:
                numOfImages = 1;
                break;
            case YOSHI:
                numOfImages = 0;
                break;
            case TURTLE:
                numOfImages = 0;
                break;
            case GOOMBA:
                numOfImages = 0;
                break;
            case PIRANHA:
                numOfImages = 0;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public int getNumOfAttackingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 0;
                break;
            case MARIO:
                numOfImages = 0;
                break;
            case YOSHI:
                numOfImages = 0;
                break;
            case TURTLE:
                numOfImages = 0;
                break;
            case GOOMBA:
                numOfImages = 0;
                break;
            case PIRANHA:
                numOfImages = 0;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public int getNumOfDyingImages(){
        int numOfImages;
        switch (this){
            case LUIGI:
                numOfImages = 1;
                break;
            case MARIO:
                numOfImages = 1;
                break;
            case YOSHI:
                numOfImages = 0;
                break;
            case TURTLE:
                numOfImages = 0;
                break;
            case GOOMBA:
                numOfImages = 1;
                break;
            case PIRANHA:
                numOfImages = 0;
                break;
            default: numOfImages = 0;
        }
        return numOfImages;
    }
    public String asString(){
        switch (this){
            case LUIGI: return "Luigi";
            case MARIO: return "Mario";
            case YOSHI: return "Yoshi";
            case TURTLE: return "Turtle";
            case GOOMBA: return "Goomba";
            case PIRANHA: return "Piranha";
            default: return "";
        }
    }

    public Boolean isFriendly(){
        switch (this){
            case LUIGI: return true;
            case MARIO: return true;
            case YOSHI: return true;
            case TURTLE: return false;
            case GOOMBA: return false;
            case PIRANHA: return false;
            default: return false;
        }
    }

    public int getJumpHeight(){
        switch (this){
            case LUIGI: return 50;
            case MARIO: return 150;
            case YOSHI: return 50;
            case TURTLE: return 25;
            case GOOMBA: return 0;
            case PIRANHA: return 0;
            default: return 0;
        }
    }

    public int getAirTime(){ //in frames
        switch (this){
            case LUIGI: return 30;
            case MARIO: return 18; //1.5 seconds
            case YOSHI: return 30;
            case TURTLE: return 30;
            case GOOMBA: return 0;
            case PIRANHA: return 0;
            default: return 0;
        }
    }





    public int getJumpIncrament(){
        switch (this){
            case LUIGI: return this.getJumpHeight()/jumpFactor;
            case MARIO: return this.getJumpHeight()/jumpFactor;
            case YOSHI: return this.getJumpHeight()/jumpFactor;
            case TURTLE: return this.getJumpHeight()/jumpFactor;
            case GOOMBA: return 0;
            case PIRANHA: return 0;
            default: return 0;
        }

    }


}