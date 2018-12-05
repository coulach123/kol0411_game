/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jan
 */
public class Enemy {
    private int x;
    private int y;
    private int speed;
    
    public Enemy(int x, int y){
        this.x=x;
        this.y=y;
        this.speed=1;
    }
    
    public void tick(){
        if(gameManager.score<=25){
            speed=1;
        }
        
        if(gameManager.score>25 && gameManager.score<=50){
            speed=2;
            gameManager.delay=750;
        }
        
        if(gameManager.score>50){
            speed=3;
            gameManager.delay=500;
        }
        y+=speed;
    }
    
    public void render(Graphics g){
        g.drawImage(loadBackground.enemy,x, y, 25,25,null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void zeroSpeed(){
        speed=0;
    }
}
