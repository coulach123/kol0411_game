/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author jan
 */
public class Display {
    private String title;
    private int width;
    private int height;
    public static JFrame frame;
    private Canvas canvas;
    
    public Display(String title, int width, int height){
        this.width=width;
        this.height=height;
        this.title=title;
        createDisplay();
    }
    
    public void createDisplay(){
        frame=new JFrame(title);
        frame.setSize(width,height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setBackground(Color.gray);
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
}
