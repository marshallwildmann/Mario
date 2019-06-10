package com.company;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public enum CharacterState {
    STANDINGLEFT,
    STANDINGRIGHT,
    WALKINGLEFT,
    WALKINGRIGHT,
    JUMPINGLEFT,
    JUMPINGRIGHT,
    DUCKINGLEFT,
    DUCKINGRIGHT,
    ATTACKINGLEFT,
    ATTACKINGRIGHT,
    DYING;

    public String toString(){
        switch (this){

            case STANDINGLEFT: return "StandingLeft";
            case STANDINGRIGHT: return "StandingRight";
            case WALKINGLEFT: return "WalkingLeft";
            case WALKINGRIGHT: return "WalkingRight";
            case JUMPINGLEFT: return "JumpingLeft";
            case JUMPINGRIGHT: return "JumpingRight";
            case DUCKINGLEFT: return "DuckingLeft";
            case DUCKINGRIGHT: return "DuckingRight";
            case ATTACKINGLEFT: return "AttackingLeft";
            case ATTACKINGRIGHT: return "AttackingRight";
            case DYING: return "Dying";
            default: return "";
        }
    }
    public static CharacterState toState(String stateAsString){
        switch (stateAsString){

            case "StandingLeft": return STANDINGLEFT;
            case "StandingRight": return STANDINGRIGHT;
            case "WalkingLeft": return WALKINGLEFT ;
            case "WalkingRight": return WALKINGRIGHT ;
            case "JumpingLeft": return JUMPINGLEFT;
            case "JumpingRight": return JUMPINGRIGHT;
            case "DuckingLeft": return DUCKINGLEFT;
            case "DuckingRight": return DUCKINGRIGHT;
            case "AttackingLeft": return ATTACKINGLEFT;
            case "AttackingRight": return ATTACKINGRIGHT;
            case "Dying": return DYING;
            default: return STANDINGRIGHT;
        }
    }

    public int getNumOfImages(Sprite sprite){
        int numOfImages;
        switch (this) {

            case STANDINGLEFT: case STANDINGRIGHT:
                numOfImages = sprite.getNumOfStandingImages();
                break;
            case WALKINGLEFT: case WALKINGRIGHT:
                numOfImages = sprite.getNumOfWalkingImages();
                break;
            case DUCKINGLEFT: case DUCKINGRIGHT:
                numOfImages = sprite.getNumOfDuckingImages();
                break;
            case JUMPINGLEFT: case JUMPINGRIGHT:
                numOfImages = sprite.getNumOfJumpingImages();
                break;
            case ATTACKINGLEFT: case ATTACKINGRIGHT:
                numOfImages = sprite.getNumOfAttackingImages();
                break;
            case DYING:
                numOfImages = sprite.getNumOfDyingImages();
            default: numOfImages = sprite.getNumOfStandingImages();
        }
        return numOfImages;
    }



}