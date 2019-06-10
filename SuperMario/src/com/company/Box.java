package com.company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Box { //x and y coords on top left corner
    private int xPos;
    private int yPos;
    private int boxSize;
    public static int maxBoxHeight;
    public static int minBoxHeight;

    private BoxState state;

    public Box( int x, int y){
        //is greater than maxBoxHeight
        state = BoxState.FREE;
        maxBoxHeight = Screen.grassHeight - Screen.getCurrentPlayer().getCurrentImageDimensions()[0] - Screen.getCurrentPlayer().jumpHeight;
        if(Screen.getDimensions()[0] < Screen.getDimensions()[1]){
            this.boxSize = Screen.getDimensions()[0] / 16;
        }else{
            this.boxSize = Screen.getDimensions()[1] / 16;
        }
        minBoxHeight = Screen.grassHeight - Screen.getCurrentPlayer().getCurrentImageDimensions()[0] ;


        Random r = new Random();
        int low = maxBoxHeight;
        int high = minBoxHeight + 50;

        int result = r.nextInt(high-low) + low;
        this.xPos = x;
        this.yPos = y;





    }




    public BoxState getState(){
        return this.state;
    }
    public void setState(BoxState state){
        this.state = state;
    }
    public int[] getCoordinates(){
        int[] coords = new int[]{this.xPos, this.yPos};

        return coords;
    }
    public void setCoordinates(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }
    public int getSize(){
        return this.boxSize;
    }
    public void setSize(int size){
        this.boxSize = size;
    }

    public void draw(Graphics g){
        g.setColor(new Color(139,69,19));
        g.fillRect(xPos, yPos,boxSize, boxSize);
//        g.fillRect(xPos + boxSize,  (yPos),boxSize, boxSize);
//        System.out.println("drew box at yPos = " + yPos);

    }
    public void move(){

        if(Screen.getCurrentPlayer().getWalkSpeed() == 0){
            this.xPos -= Screen.getCurrentPlayer().maxSpeed/2;
        }else{
            this.xPos -= Screen.getCurrentPlayer().getWalkSpeed();

        }
    }

    public void updateState(){
        for (Character character : Screen.getAllCharacters()) {
            if(this.equals(character.boxAbove())){
                this.state = BoxState.ABOVE;
            }else if(this.equals(character.boxBelow())){
                this.state = BoxState.BELOW;
            }else if(this.equals(character.boxToTheLeft())){
                this.state = BoxState.TOTHELEFT;
            }else if(this.equals(character.boxToTheRight())){
                this.state = BoxState.TOTHERIGHT;

            }else{
                this.state = BoxState.FREE;
            }

        }
    }

//    public Box topHalf(){
//        //create new box with same coords but half the size
//        Box topBox = new Box(false);
//        topBox.setCoordinates(this.xPos, this.yPos);
//        topBox.s
//    }
//    public Box bottomHalf(){
//
//    }
//    public Box leftHalf(){
//
//    }
//    public Box rightHalf(){
//
//    }


}
