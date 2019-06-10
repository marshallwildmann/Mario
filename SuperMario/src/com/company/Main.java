package com.company;
import java.util.*;
import javax.swing.*;
import java.sql.Time;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import java.util.Timer;

public class Main extends JFrame {
    public static JFrame frame = new JFrame("Screen");
    public static Timer timer = new Timer();





    public static void main(String[] args) {




        frame.getContentPane().add(new Screen());// Create new screen/ start game
        frame.setSize(Screen.getDimensions()[1], Screen.getDimensions()[0]);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        long startTime = System.currentTimeMillis();
        int x = 0;

        if (Screen.gameRunning()){


            timer.schedule(new Frame(), 0, 83); //12 fps
            Game game = new Game();
            frame.addKeyListener(game);

//            while(true){
//
//
//            }

        }

        //MouseClicked checkClicked = new MouseClicked();
        //frame.addMouseListener(checkClicked);



    }
}