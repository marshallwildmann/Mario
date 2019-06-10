
package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Brick extends Box {

    public Brick(int x, int y){
        super(x, y);

    }

    private Image getImage(){
        String path = "/Users/h205p1/Desktop/coding/SuperMario/out/production/SuperMario/com/Sprites/BrickRegular.png";

//            int numOfImages = state.getNumOfImages(this.sprite);
//
//            Image[] images = new Image[numOfImages];
//            try {
//
//                for(int i = 0; i < numOfImages; i++){
//                    if(numOfImages != state.getNumOfImages(this.sprite)){
//                        numOfImages = state.getNumOfImages(this.sprite);
//                        images = new Image[numOfImages];
//                        i = 0;
//                    }
//                    Image image = ImageIO.read(new File(path + this.charName + state + (i + 1) + ".png"));
//                    images[i] = image;
//
//
//                }
//                if(images.length == 0){
//                    System.out.println("numOfImages = 0");
//                }
//                return images;
//            } catch (IOException ex) {
////            System.out.println("numOfImages = " + numOfImages + ", state = " + state);
//                System.out.println("Exception throw: " + ex);
//
//
//                return null;
//            }
        try {
            Image image = ImageIO.read(new File(path));
            Image newImage = image.getScaledInstance((int) ((double) this.getSize() * 1.15), this.getSize() , Image.SCALE_DEFAULT);
            return newImage;
        } catch (IOException ex) {
            System.out.println("Exception throw: " + ex);

        }
        return null;
    }

    public void draw(Graphics g){
        g.drawImage(this.getImage(), this.getCoordinates()[0], this.getCoordinates()[1] ,null);

    }

}
