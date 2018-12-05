/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author jan
 */
public class loadBackground extends JPanel{
    public static Image image;
    public static Image enemy=Toolkit.getDefaultToolkit().createImage("C:\\Users\\jan\\Documents\\NetBeansProjects\\hra\\res\\enemyship.png");
    public static Image player=Toolkit.getDefaultToolkit().createImage("C:\\Users\\jan\\Documents\\NetBeansProjects\\hra\\res\\spaceship.png");
    public static Image explosion=Toolkit.getDefaultToolkit().createImage("C:\\Users\\jan\\Documents\\NetBeansProjects\\hra\\res\\explosion.png");
    public static Image ammo=Toolkit.getDefaultToolkit().createImage("C:\\Users\\jan\\Documents\\NetBeansProjects\\hra\\res\\raketa.png");
    
    public static void init(){
        image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\jan\\Documents\\NetBeansProjects\\hra\\res\\background2.gif");
        
    }
    
    
    
}
