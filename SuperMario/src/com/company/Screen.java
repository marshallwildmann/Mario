package com.company;
//import java.math.

import javax.swing.*;
import java.awt.*;
import java.awt.color.*;
import java.util.ArrayList;

public class Screen extends JPanel {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth = (int) screenSize.getWidth();
    private static int screenHeight = (int) screenSize.getHeight();
    private static int groundHeight = (int)(screenHeight / 2.5); //gdoun pos
    static int grassHeight = screenHeight - groundHeight - 30;

    private static int boxSize;
    static int rectXPos;

    private static boolean firstTime;
    //private static int gap = 1;//gap between cubes
    public static int randomNum;
    private static Boolean gameRunning;
    private static int gameSpeed; //milliseconds per frame
    static int counter;
    static int frame;
    private static Player currentPlayer;
    private static ArrayList<Brick> allBricks = new ArrayList();
    private static ArrayList<Box> allBoxes = new ArrayList();
    private static ArrayList<Character> allCharacters = new ArrayList(); //new character is added for each initialization
    public Screen(){
        boxSize = screenHeight/18;
        rectXPos = screenWidth;
        firstTime = true;
        gameRunning = true;
        gameSpeed = 500; //lower is faster
        counter = 0;
        randomNum = (int) (Math.random() * 10);
        frame = 0;
        currentPlayer = new Player(Sprite.MARIO);
        new Character(Sprite.GOOMBA);



        //create 1 box

    }
    public void paint(Graphics g) {
        //draw

        drawGround(g);//never changes
        drawAllBoxes(g);

//        drawRandomBoxes(g);
//        System.out.println(currentPlayer.getState());
        //draw all characters
        drawAllCharacters(g);


        firstTime = false;
    }


    public static void drawGround(Graphics g){
        g.setColor(new Color(139,69,19));// set color Brown
        g.fillRect(0, screenHeight - groundHeight, screenWidth, groundHeight);//Ground

        g.setColor(new Color(0,80,0));// set color green
        g.fillRect(0, grassHeight, screenWidth, 30);//Grass
        g.setColor(new Color(0, 0, 0));
    }


    public static int getGameSpeed(){
        return gameSpeed;
    } //returns milliseconds per frame
    public static int getGroundHeight(){
        return groundHeight;
    }

    public static Player getCurrentPlayer(){return currentPlayer;}

    public static int[] getDimensions(){

        int[] Dimension = {screenHeight, screenWidth};
        return Dimension;
    }

    public static Boolean gameRunning(){
        return gameRunning;
    }



    public static int getFrame(){
        return frame;
    }

    public static ArrayList<Brick> getAllBricks(){
        return allBricks;
    }
    public static ArrayList<Box> getAllBoxes(){
        return allBoxes;
    }

    public static ArrayList<Character> getAllCharacters(){
        return allCharacters;
    }
    public static void addCharacter(Character character){
        allCharacters.add(character);
    }

    public static void addBrick(int x, int y){
        Brick brick = new Brick(x, y); //create new box that automatticaly gets added to screen
        allBricks.add(brick);
        allBoxes.add(brick);
    }
    //    public static void addQBlock(int x, int y){
//        QBlock qBlock = new QBlock(x, y); //create new box that automatticaly gets added to screen
//        allQBlocks.add(qBlock);
//        allBoxes.add(qBlock);
//    }
    public void drawAllBoxes(Graphics g){
        for ( Box box : allBoxes) {
            box.draw(g);
        }
    }
    public void drawAllCharacters(Graphics g){
        for(Character character : allCharacters){
            g.drawImage(character.getCurrentImage(), character.getCoordinates()[0], character.getCoordinates()[1] , this);
        }
    }
    public static void moveAllBoxes(){

        for ( Box box : allBricks) {
            box.
                    move();
        }
    }
}
