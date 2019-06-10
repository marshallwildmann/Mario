package com.company;


import java.awt.event.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;


public class Game implements KeyListener, ActionListener{
    private int startAction;
    private int lengthOfAction;
    private static Boolean firstKeyPressed = true;
    public Game(){

    }

    private void handleAirTime(){
//        Screen.getCurrentPlayer().getCoordinates()[1] <= Screen.getCurrentPlayer().getMaxJumpPos() + 10 ||
        if(lengthOfAction >= Screen.getCurrentPlayer().getAirTime()){
//            System.out.println("length of jump = " + lengthOfAction);
            lengthOfAction = 0;

            if( (Screen.getCurrentPlayer().getState().toString().equals("JumpingLeft") || Screen.getCurrentPlayer().getState2().toString().equals("JumpingLeft")) && Screen.getCurrentPlayer().getCoordinates()[1] <= Screen.getCurrentPlayer().getStartCoords()[1]){
//                Screen.getCurrentPlayer().setState(CharacterState.STANDINGLEFT);
                Screen.getCurrentPlayer().setState2(null);

                Screen.getCurrentPlayer().isFalling = true;
                Screen.getCurrentPlayer().isJumping = false;

            }else if(Screen.getCurrentPlayer().getState().toString().equals("JumpingRight") || Screen.getCurrentPlayer().getState2().toString().equals("JumpingRight") && Screen.getCurrentPlayer().getCoordinates()[1] <= Screen.getCurrentPlayer().getStartCoords()[1]){
//                Screen.getCurrentPlayer().setState(CharacterState.STANDINGRIGHT);
                Screen.getCurrentPlayer().setState2(null);
                Screen.getCurrentPlayer().isFalling = true;
                Screen.getCurrentPlayer().isJumping = false;


            }
        }
    }

    @Override

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();
        if(firstKeyPressed){//set start action to current frame if it is the firstKeyPressed
            startAction = Screen.frame;
        }

        lengthOfAction = Screen.frame - startAction;
        if(code == KeyEvent.VK_UP){
            if(firstKeyPressed){ //set to jumping if up key was just pressed
                if(!Screen.getCurrentPlayer().isFalling && Screen.getCurrentPlayer().getState().toString().equals("StandingLeft")){
                    Screen.getCurrentPlayer().setState(CharacterState.JUMPINGLEFT);
                    Screen.getCurrentPlayer().isJumping = true;
                }else if(!Screen.getCurrentPlayer().isFalling && Screen.getCurrentPlayer().getState().toString().equals("StandingRight")){
                    Screen.getCurrentPlayer().setState(CharacterState.JUMPINGRIGHT);
                    Screen.getCurrentPlayer().isJumping = true;

                }
            }
            //if pressing side key then start pressing up key
            if(Screen.getCurrentPlayer().getState().toString().equals("WalkingLeft") ){
                Screen.getCurrentPlayer().setState2(CharacterState.JUMPINGLEFT);
                Screen.getCurrentPlayer().isJumping = true;
                Screen.getCurrentPlayer().setWalkSpeed(Screen.getCurrentPlayer().jumpSpeed - 2);

            }else if(Screen.getCurrentPlayer().getState().toString().equals("WalkingRight")){
                Screen.getCurrentPlayer().setState2(CharacterState.JUMPINGRIGHT);
                Screen.getCurrentPlayer().isJumping = true;
                Screen.getCurrentPlayer().setWalkSpeed(Screen.getCurrentPlayer().jumpSpeed - 2);


            }


            //jump for length of action
        }

        if (code == KeyEvent.VK_DOWN){
            //if wasn't ducking before
            Screen.getCurrentPlayer().fallSpeed *= 3;
            Screen.getCurrentPlayer().setState("Ducking" + Screen.getCurrentPlayer().direction);

        }

        if (code == KeyEvent.VK_LEFT){
            Screen.getCurrentPlayer().direction = "Left";
            if(Screen.getCurrentPlayer().getState().toString().equals("JumpingLeft")){
                Screen.getCurrentPlayer().setState2(CharacterState.WALKINGLEFT);
            }else{
                Screen.getCurrentPlayer().setState(CharacterState.WALKINGLEFT);
            }


        }

        if (code == KeyEvent.VK_RIGHT){
            Screen.getCurrentPlayer().direction = "Right";
            if(Screen.getCurrentPlayer().getState().toString().equals("JumpingRight")){
                Screen.getCurrentPlayer().setState2(CharacterState.WALKINGRIGHT);
            }else{
                Screen.getCurrentPlayer().setState(CharacterState.WALKINGRIGHT);
            }
        }
//        if (code == KeyEvent.VK_W){
//
//        }
//
//        if (code == KeyEvent.VK_S){
//            Screen.getCurrentPlayer().setState(CharacterState.DUCKING);
//        }
//
//        if (code == KeyEvent.VK_A){
//            Screen.getCurrentPlayer().setState(CharacterState.WALKINGLEFT);
//        }
//
//        if (code == KeyEvent.VK_D){
//            Screen.getCurrentPlayer().setState(CharacterState.WALKINGRIGHT);
//        }
        if(code == KeyEvent.VK_SHIFT){
            if(Screen.getCurrentPlayer().getState().toString().equals("WalkingLeft") || Screen.getCurrentPlayer().getState().toString().equals("WalkingRight")){
                Screen.getCurrentPlayer().setWalkSpeed(Screen.getCurrentPlayer().getWalkSpeed() + 3);
            }
        }

        handleAirTime();
        firstKeyPressed = false;

    }

    @Override
    public void keyReleased(KeyEvent e){
        firstKeyPressed = true;

//        Screen.getCurrentPlayer().setState(CharacterState.STANDINGRIGHT);
        Screen.getCurrentPlayer().setWalkSpeed(0);
        Screen.getCurrentPlayer().fallSpeed = 0;
        Screen.getCurrentPlayer().jumpSpeed = 0;
        Screen.getCurrentPlayer().setState2(null);

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){
            Screen.getCurrentPlayer().isFalling = true;
            if(Screen.getCurrentPlayer().getState().toString().equals("JumpingLeft") && Screen.getCurrentPlayer().getCoordinates()[1] < Screen.getCurrentPlayer().getStartCoords()[1]){
                Screen.getCurrentPlayer().setState(CharacterState.STANDINGLEFT);
            }else if(Screen.getCurrentPlayer().getState().toString().equals("JumpingRight") && Screen.getCurrentPlayer().getCoordinates()[1] < Screen.getCurrentPlayer().getStartCoords()[1]){
                Screen.getCurrentPlayer().setState(CharacterState.STANDINGRIGHT);
            }


        }

        if (code == KeyEvent.VK_DOWN){
            if(Screen.getCurrentPlayer().getState().toString() == "DuckingLeft"){
                Screen.getCurrentPlayer().setState(CharacterState.STANDINGLEFT);
            }else{
                Screen.getCurrentPlayer().setState(CharacterState.STANDINGRIGHT);
            }

            //check when down key is released if y pos of mario is below ground set him back up
            Screen.getCurrentPlayer().fallSpeed = Screen.getCurrentPlayer().initialSpeed;

        }

        if (code == KeyEvent.VK_LEFT){
            Screen.getCurrentPlayer().setState(CharacterState.STANDINGLEFT);

        }

        if (code == KeyEvent.VK_RIGHT){
            Screen.getCurrentPlayer().setState(CharacterState.STANDINGRIGHT);
        }

        if(code == KeyEvent.VK_SHIFT){
            Screen.getCurrentPlayer().setWalkSpeed(Screen.getCurrentPlayer().initialSpeed);
        }
//        if (code == KeyEvent.VK_W){
//            Screen.getCurrentPlayer().setState(CharacterState.JUMPING);
//        }
//
//        if (code == KeyEvent.VK_S){
//            Screen.getCurrentPlayer().setState(CharacterState.DUCKING);
//        }
//
//        if (code == KeyEvent.VK_A){
//            Screen.getCurrentPlayer().setState(CharacterState.WALKINGLEFT);
//        }
//
//        if (code == KeyEvent.VK_D){
//            Screen.getCurrentPlayer().setState(CharacterState.WALKINGRIGHT);
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



}