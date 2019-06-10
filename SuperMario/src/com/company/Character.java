package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class Character {
    private int xPos;
    private int yPos;
    private int walkSpeed;
    int jumpSpeed;
    int fallSpeedDucking;
    int fallSpeed;
    private int maxJumpPos;
    int jumpHeight;
    int maxSpeed;
    int acceleration;
    int initialSpeed;
    private int[] startCoords;
    private int indexOfCurrentImage;
    private Sprite sprite;
    private String charName;
    private Boolean isFriendly;
    private CharacterState state;
    private CharacterState state2;
    Boolean isJumping;
    Boolean isFalling;
    Boolean isAttacking;
    String direction; //first letter is capital
    private int collisionBuffer = 25;

    public Character(Sprite sprite){ //x and y pos on top left corner

        isAttacking = false;
        direction = "Right";
        isFalling = true;
        this.sprite = sprite;
        this.initialSpeed = 8;
        this.maxSpeed = this.initialSpeed * 2;
        this.acceleration = this.maxSpeed / 7;
        jumpSpeed = this.initialSpeed;
        fallSpeed = this.initialSpeed;

        walkSpeed = this.initialSpeed;
        //Screenheight - grounds height - ground height - characterHeight

        this.jumpHeight = sprite.getJumpHeight();

        this.charName = sprite.asString();
        this.isFriendly = sprite.isFriendly();
        this.state = CharacterState.STANDINGRIGHT;
        this.indexOfCurrentImage = 0;

        //x and y coords always here
        this.xPos = 10; //default spawn point
        this.yPos = Screen.getDimensions()[0] - Screen.getGroundHeight()  - this.getCurrentImageDimensions()[0]; //default spawn point
        this.startCoords = new int[]{this.xPos, this.yPos};
        if(this.yPos - this.jumpHeight < 0){
            System.out.println("mario can jump off screen");
        }
        this.maxJumpPos = this.yPos - this.jumpHeight;

        Screen.addCharacter(this);

    }


    public String toString() {
        return charName;
    }

    public int [] getCoordinates(){
        return new int[]{this.xPos, this.yPos};
    }
    public int[] getBottomRight(){
        int[] bottomRight = new int[]{this.xPos + this.getCurrentImageDimensions()[1], this.yPos + this.getCurrentImageDimensions()[0]};
        return bottomRight;
    }


    public void setCoordinates(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }
    public Sprite getSprite(){
        return this.sprite;
    }
    public void jump(){
        //include animation here
        if(this.boxAbove() == null){ //if isn;t box above
            this.moveTo(xPos, yPos - this.jumpSpeed );

        }

        if(this.jumpSpeed < this.maxSpeed) //12 is max
            jumpSpeed += this.acceleration;

    }
    public int[] getCurrentImageDimensions(){
        BufferedImage bimg = toBufferedImage(this.getCurrentImage());
        int[] dimensions = new int[]{bimg.getHeight() + 35, bimg.getWidth()};
        return dimensions;
    }

    public int getMaxJumpPos() {
        return this.maxJumpPos;
    }
    public int[] getStartCoords(){
        return this .startCoords;
    }

    public void fall(){
//        int heightFromGround = (Screen.getGroundHeight() - Screen.getCurrentPlayer().getCoordinates()[1]);
        //&& Math.abs(heightFromGround - Screen.getCurrentPlayer().fallSpeed) < 10
//        if( (yPos + fallSpeed) > this.startCoords[1]) {
//            System.out.println("did this");
//            this.moveTo(xPos, Screen.getGroundHeight());
//        }else{

        this.moveTo(xPos, yPos + this.fallSpeed);

        if (this.yPos >= this.startCoords[1]){
            this.walkSpeed = initialSpeed;
            this.fallSpeed = initialSpeed;
            this.jumpSpeed = initialSpeed;
            this.isFalling = false;
        }
        if(boxBelow() != null){
            this.moveTo(this.xPos, boxBelow().getCoordinates()[1] - this.getCurrentImageDimensions()[0] + collisionBuffer);
            this.isFalling = false;
        }



        if(this.fallSpeed < this.maxSpeed)
            fallSpeed+=4;
    }

    public  void duckLeft(){

    }
    public  void moveLeft(){

        Boolean isBoxInPath = false;
        for (Box box : Screen.getAllBoxes()){
            if(this.xPos <= (box.getCoordinates()[0]- box.getSize() - 5) && this.xPos >= box.getCoordinates()[0] ){ //if in box
                isBoxInPath = true;
            }
        }
        if(!isBoxInPath) {
            moveTo(xPos - this.walkSpeed, yPos);
        }
        if(this.walkSpeed < this.maxSpeed)
            walkSpeed+=this.acceleration;

    }
    public  void moveRight(){
//        if(this.xPos > Screen.getDimensions()[1]/2){
//            Screen.rectXPos -= this.walkSpeed;//move everything else other than mario
//
//        }else{


        moveTo(xPos + this.walkSpeed, yPos); //move mario


//        }


        if(this.walkSpeed < this.maxSpeed)

            walkSpeed+=this.acceleration;
    }

    public void moveTo(int x, int y){
        //include animation here
        this.xPos = x;
        this.yPos = y;
    }

    public Boolean isFriendly(){
        return sprite.isFriendly();
    }

//    private Box boxInpath(){
////        Box boxBelow = null;
////        if(this.boxBelow() != null){
////            boxBelow = this.boxBelow();
////        }
//
//        for (Box box : Screen.getAllBoxes()){
//            int boxX = box.getCoordinates()[0];
//            int boxY = box.getCoordinates()[1];
//
//
////
//            if(xPos < boxX + box.getSize() && xPos + getCurrentImageDimensions()[1] > boxX && yPos < boxY + box.getSize() && yPos + getCurrentImageDimensions()[0] > boxY){ //if in box
//
//                return box;
//
//            }
//
//
//        }
//        return null;
//    }

    public Box boxToTheRight(){

        if(this.state.toString().equals("WalkingRight")){
            for (Box box : Screen.getAllBoxes()){
                int boxX = box.getCoordinates()[0];
                int boxY = box.getCoordinates()[1];

                //if character runs into left side of box
                if( (box.getState().equals(BoxState.FREE) || box.getState().equals(BoxState.TOTHERIGHT) )  && xPos < boxX + box.getSize()/2 && xPos + getCurrentImageDimensions()[1] > boxX && yPos < boxY + box.getSize() && yPos + getCurrentImageDimensions()[0] - collisionBuffer > boxY){ //if in box
                    System.out.println("box to the right");
                    return box;

                }


            }
        }
        return null;
    }
    public Box boxToTheLeft(){
        if(this.state.toString().equals("WalkingLeft")){
            for (Box box : Screen.getAllBoxes()){
                int boxX = box.getCoordinates()[0];
                int boxY = box.getCoordinates()[1];
//                System.out.println("in function");
                //if character runs into right side of box
//                 )
                if((box.getState().equals(BoxState.FREE) || box.getState().equals(BoxState.TOTHELEFT) ) && xPos > boxX + box.getSize()/2 && xPos < boxX + box.getSize() && yPos < boxY + box.getSize() && yPos + getCurrentImageDimensions()[0] > boxY){ //if in box

                    return box;

                }


            }
        }
        return null;

    }
    public Box boxAbove(){
        if( (this.state.toString().equals("JumpingLeft") || this.state.toString().equals("JumpingRight"))){
            for (Box box : Screen.getAllBoxes()){
                int boxX = box.getCoordinates()[0];
                int boxY = box.getCoordinates()[1];

                //if character runs into bottom side of box
                if( (box.getState().equals(BoxState.FREE) || box.getState().equals(BoxState.ABOVE) ) && xPos < boxX + box.getSize() && xPos + getCurrentImageDimensions()[1] > boxX && yPos - collisionBuffer < boxY + box.getSize() / 2 && yPos - collisionBuffer > boxY ){ //if in box

                    return box;

                }


            }
        }
        return null;

    }
    public Box boxBelow(){

        for (Box box : Screen.getAllBoxes()){
            int boxX = box.getCoordinates()[0];
            int boxY = box.getCoordinates()[1];

            //if character runs into bottom side of box
            if( (box.getState().equals(BoxState.FREE) || box.getState().equals(BoxState.BELOW) ) && (xPos  < boxX  + box.getSize() && xPos + getCurrentImageDimensions()[1]  > boxX) && yPos + getCurrentImageDimensions()[0] - collisionBuffer < boxY + box.getSize()/2 && yPos + getCurrentImageDimensions()[0] > boxY){ //if in box
                System.out.println("box below");
                return box;

            }



        }
        return null;
    }
    //
    public void setWalkSpeed(int speed){
        this.walkSpeed = speed;
    }

    public int getWalkSpeed(){
        return this.walkSpeed;
    }
    public int getAirTime(){//in frames
        return this.sprite.getAirTime();
    }



    public void setState(CharacterState state){
        //if character goes from not jumping to jumping. set maxJumpPos
        if(state != this.state){
            this.indexOfCurrentImage = 0;
        }

        if( !(this.state.toString().equals("JumpingLeft") || this.state.toString().equals("JumpingRight")) && state.toString().equals("JumpingLeft") || state.toString().equals("JumpingRight") ) {
            this.maxJumpPos = this.yPos - this.jumpHeight;
        }
        this.state = state;




    }
    public void setState2( CharacterState state){
        if(state != this.state2){
            this.indexOfCurrentImage = 0;
        }
        this.state2 = state;


    }
    public void setState(String stateAsString){
        //if character goes from not jumping to jumping. set maxJumpPos
        if( !stateAsString.equals(this.state.toString()) ){
            this.indexOfCurrentImage = 0;
        }
        if( !(this.state.toString().equals("JumpingLeft") || this.state.toString().equals("JumpingRight")) && CharacterState.toState(stateAsString).equals("JumpingLeft") || CharacterState.toState(stateAsString).equals("JumpingRight") ) {
            this.maxJumpPos = this.yPos - this.jumpHeight;
        }
        this.state = CharacterState.toState(stateAsString);



    }
    public CharacterState getState(){
        return state;
    }
    public CharacterState getState2(){
        return state2;
    }


    Image[] getCurrentAnimation(){
        String path = "/Users/h205p1/Desktop/coding/SuperMario/out/production/SuperMario/com/Sprites/";

        CharacterState state = this.state;
        if(this.state2 == CharacterState.JUMPINGLEFT || this.state2 == CharacterState.JUMPINGRIGHT){
            state = this.state2;
        }
        int numOfImages = state.getNumOfImages(this.sprite);

        Image[] images = new Image[numOfImages];
        // System.out.println(numOfImages);
        //System.out.println(getState().toString());
        try {

            for(int i = 0; i < numOfImages; i++){
                if(numOfImages != state.getNumOfImages(this.sprite)){
                    numOfImages = state.getNumOfImages(this.sprite);
                    images = new Image[numOfImages];
                    i = 0;
                }
                Image image = ImageIO.read(new File(path + this.charName + state + (i + 1) + ".png"));
                images[i] = image;


            }
            if(images.length == 0){
                System.out.println("numOfImages = 0");
            }
            return images;
        } catch (IOException ex) {
//            System.out.println("numOfImages = " + numOfImages + ", state = " + state);
            System.out.println("Exception throw: " + ex);


            return null;
        }

    }


    public Image getCurrentImage(){
//        System.out.println(this.getCurrentAnimation());
        int indexDiff = this.indexOfCurrentImage - this.getCurrentAnimation().length;
        if ( this.indexOfCurrentImage >= this.getCurrentAnimation().length) {
            System.out.println("index is past animation");
            this.indexOfCurrentImage = 0;
        }

        return this.getCurrentAnimation()[this.indexOfCurrentImage];


    }

    public void nextImage() { //10 frames per se
        this.indexOfCurrentImage++;
        if (this.indexOfCurrentImage >= this.getCurrentAnimation().length) {
            this.indexOfCurrentImage = 0;
        }


    }
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}