/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jan
 */
public class Setup implements Runnable{
    private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;
    private int y;
    private gameManager manager;
    public static final int gameWidth=400;
    public static final int gameHeight=400;
    
    public Setup(String title,int width,int height){
        this.title=title;
        this.width=width;
        this.height=height;
    }
    
    public void init() throws FileNotFoundException{
        display=new Display(title,width,height);
        loadBackground.init();
        manager = new gameManager();
        manager.init();
    }
    
    public synchronized void start(){
        if(running)
            return;
        running=true;
        if(thread==null){
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public synchronized void stop() throws InterruptedException{
        if(!running)
            return;
        running=false;
        thread.join();
    }
    
    public void tick(){
        manager.tick();
    }
    
    public void render(){
        buffer=display.getCanvas().getBufferStrategy();
        if(buffer==null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g=buffer.getDrawGraphics();
        g.drawImage(loadBackground.image,50,50,gameWidth,gameHeight,null);
        manager.render(g);
        buffer.show();
        g.dispose();
    }
    public void run(){
        try {
            init();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        }
        int fps=60;
        double timePerTick = 1000000000/fps;
        double delta=0;
        long current=System.nanoTime();
        while(running){
            delta+=(System.nanoTime()-current)/timePerTick;
            current=System.nanoTime();
            if(delta>=1){
                tick();
                render();
                delta--;
        }
        
    }
}
}
