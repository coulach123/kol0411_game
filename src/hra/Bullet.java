/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import java.awt.Graphics;
import java.awt.*;

/**
 *
 * @author jan
 */
public class Bullet{
    private int x;
    private int y;
    private int speed;
    
    public Bullet(int x, int y){
        this.x=x;
        this.y=y;
        this.speed=10;
    }
    
    public void tick(){
       if(y>48)
        y-=speed;     
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public void render(Graphics g){
        g.drawImage(loadBackground.ammo, x, y,10,30,null);
    }
}
