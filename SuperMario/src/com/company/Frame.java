package com.company;

import java.util.TimerTask;

public class Frame extends TimerTask {
    @Override
    public void run() {
//        System.out.println("frame = " + Screen.frame);
        Screen.frame++;
//            System.out.println("frame = " + Screen.getFrame());
        for (Character character: Screen.getAllCharacters()) {
            character.nextImage();
        }


        //for i in all characters{
        //nextImage();
        //}


        checkState(Screen.getCurrentPlayer().getState());

        if(Screen.getCurrentPlayer().getState2() != null){
            checkState(Screen.getCurrentPlayer().getState2());
        }

        for(Box box : Screen.getAllBoxes()){
            box.updateState();
        }

        Main.frame.repaint();// repaints every x milliseconds

    }

    private void checkState(CharacterState state){
        switch (state){
            case STANDINGRIGHT: case STANDINGLEFT:
                if(Screen.getCurrentPlayer().getCoordinates()[1] < Screen.getCurrentPlayer().getStartCoords()[1]){
                    Screen.getCurrentPlayer().isFalling = true;
                }
//                Screen.getCurrentPlayer().boxBelow();
                break;
            case DUCKINGLEFT: case DUCKINGRIGHT:

                break;

            case JUMPINGLEFT: case JUMPINGRIGHT:
                if(Screen.getCurrentPlayer().isJumping && Screen.getCurrentPlayer().getCoordinates()[1] - Screen.getCurrentPlayer().jumpSpeed > Screen.getCurrentPlayer().getMaxJumpPos()) {
                    Screen.getCurrentPlayer().jump();
                }
                break;

            case WALKINGLEFT:
                if(Screen.getCurrentPlayer().boxToTheLeft() == null){ //move character if isn't box in character path
                    Screen.getCurrentPlayer().moveLeft();

                }else{ //make character bounce off if is box in path
                    Box boxIntPath = Screen.getCurrentPlayer().boxToTheLeft();
                    Screen.getCurrentPlayer().moveTo(boxIntPath.getCoordinates()[0] + boxIntPath.getSize() +  5, Screen.getCurrentPlayer().getCoordinates()[1]);

                }


                break;
            case WALKINGRIGHT:

                if(Screen.getCurrentPlayer().getCoordinates()[0] >= Screen.getDimensions()[1]/2){

//                    int rand20 = (int) ((Math.random() * 20) + 1);
//                    if(Screen.frame % rand20 == 0){
                    if(Screen.getAllBricks().size() == 0){
                        Screen.addBrick(Screen.getDimensions()[1], 400); //create new box that automatticaly gets added to screen

                    }

                    if(Screen.getCurrentPlayer().boxToTheRight() == null){ //move all boxes if isn't box in character path
                        Screen.moveAllBoxes();
                    }else{ //make character bounce off if is box in path
                        Box boxIntPath = Screen.getCurrentPlayer().boxToTheRight();
                        Screen.getCurrentPlayer().moveTo(boxIntPath.getCoordinates()[0] - boxIntPath.getSize() -  5, Screen.getCurrentPlayer().getCoordinates()[1]);

                    }


                }else{
                    if(Screen.getCurrentPlayer().boxToTheRight() == null){ //move character if isn't box in character path
                        Screen.getCurrentPlayer().moveRight();
                    }else{ //make character bounce off if is box in path
                        Box boxIntPath = Screen.getCurrentPlayer().boxToTheRight();
                        Screen.getCurrentPlayer().moveTo(boxIntPath.getCoordinates()[0] - boxIntPath.getSize() -  5, Screen.getCurrentPlayer().getCoordinates()[1]);

                    }

                }
                break;
            case ATTACKINGLEFT:
                break;
            case ATTACKINGRIGHT:
                break;
        }
        //addational state
        if(Screen.getCurrentPlayer().isFalling){
            Screen.getCurrentPlayer().fall();
        }
    }



}