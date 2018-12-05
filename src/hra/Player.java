/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jan
 */
public class Player implements KeyListener{
    private int x;
    private int y;
    private boolean right;
    private boolean left;
    private boolean fire;
    private long current;
    private long delay;
    private int health;
    
    public Player(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void init(){
        Display.frame.addKeyListener(this);
        current=System.nanoTime();
        delay=100;
        health=3;
    }
    
    public void tick(){
        if(!(health<=0)){
        if(left){
            if(x>53)
            x-=3;
        }
        if(right){
            if(x<450-43)
            x+=3;
        }
        if(fire){
            long breaks=(System.nanoTime()-current)/1000000;
            if(breaks>delay){
                gameManager.bullet.add(new Bullet(x+12,y));
            }
            current=System.nanoTime();
        }
        }
    }
    
    public void render(Graphics g){
        if(!(health<=0)){
            g.drawImage(loadBackground.player,x,y-20,40,40,null);
        }
    }
    
    public void keyPressed(KeyEvent e){
        int source = e.getKeyCode();
        if(source==KeyEvent.VK_LEFT){
            left=true;
        }
        if(source==KeyEvent.VK_RIGHT){
            right=true;
        }
        if(source==KeyEvent.VK_B){
            fire=true;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int source = e.getKeyCode();
        if(source==KeyEvent.VK_LEFT){
            left=false;
        }
        if(source==KeyEvent.VK_RIGHT){
            right=false;
        }
        if(source==KeyEvent.VK_B){
            fire=false;
        }
    }
    
    public void keyTyped(KeyEvent e){
        
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int h){
        this.health=h;
    }
   
    
}
