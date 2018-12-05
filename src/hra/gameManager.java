/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author jan
 */
public class gameManager implements KeyListener{
    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;
    private long current;
    public static long delay;
    private int health;
    public static int score;
    private FileOutputStream fos;
    private PrintWriter pw;
    private String user;
    private DBConnection connection;
    private boolean start;
    
    public gameManager(){
        
    }
    
    public void init() throws FileNotFoundException{
        Display.frame.addKeyListener(this);
        player=new Player((Setup.gameWidth/2)+50,(Setup.gameHeight-30)+50);
        player.init();
        bullet = new ArrayList<Bullet>();
        enemies=new ArrayList<Enemy>();
        current = System.nanoTime();
        delay=1000;
        health=player.getHealth();
        score=0;
        fos=new FileOutputStream("scores.csv",true);
        pw=new PrintWriter(fos);
        connection=new DBConnection();
        
    }
    
    public void tick(){
        if(start){
        player.tick();
        for(int i=0;i<bullet.size();i++){
            bullet.get(i).tick();
        }
        
        
        long breaks = (System.nanoTime()-current)/1000000;
        if(breaks>delay){
            int randX=ThreadLocalRandom.current().nextInt(50, 425);
            int randY=ThreadLocalRandom.current().nextInt(50,80);
            if(health>0){
                enemies.add(new Enemy(randX,randY));
            }
            current=System.nanoTime();
        }
        
        for(int i=0;i<enemies.size();i++){
            enemies.get(i).tick();
        }
        
    }
    }
    
    public void render(Graphics g){
        if(start){
        player.render(g);
        for(int i=0;i<bullet.size();i++){
            bullet.get(i).render(g);
        }
        for(int i=0;i<bullet.size();i++){
            if(bullet.get(i).getY()<=50){
                bullet.remove(i);
                i--;
            }
        }
        
        for(int i=0;i<enemies.size();i++){
                enemies.get(i).render(g);
            
        }
        
        for(int i=0;i<enemies.size();i++){
            int ex=enemies.get(i).getX();
            int ey=enemies.get(i).getY();
            int px=player.getX();
            int py=player.getY();
            
            if(ey+25>=py+30){
                enemies.remove(i);
                health--;
                System.out.println("hp :"+health);
                if(health<=0){
                    enemies.removeAll(enemies);
                    player.setHealth(0);
                    user=JOptionPane.showInputDialog("Insert your name");
                    pw.println(user+";"+score+";"+java.time.LocalDateTime.now());
                    connection.InsertData(user, score);
                    //System.out.println(connection.getHS());
                    JOptionPane.showMessageDialog(Display.frame, "Your score is: "+score+", high score is: "+connection.getHS());
                    pw.close();
                }
            }
            
            for(int j=0;j<bullet.size();j++){
                int bx=bullet.get(j).getX();
                int by=bullet.get(j).getY();
                
                if(ex<bx+6 && ex+25>bx && ey<by+6 && ey+25>by){
                    enemies.remove(i);
                    i--;
                    bullet.remove(j);
                    j--;
                    g.drawImage(loadBackground.explosion, bx-25, by-25,40,40, null);
                    if(health>0){
                        score++;
                    }
                    
                    System.out.println(score);
                }
            }
        }
        g.setColor(Color.yellow);
        g.setFont(new Font("new century gothic",Font.BOLD,12));
        g.drawString("Score: "+score,50,60);
        g.drawString("HP: "+health,420,60);
    }
        else{
            g.setColor(Color.yellow);
            g.setFont(new Font("new century gothic",Font.BOLD,20));
            g.drawString("Press ENTER to start",Setup.gameWidth/2-50, Setup.gameHeight/2+50);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int source=e.getKeyCode();
        if(source==KeyEvent.VK_ENTER){
            start=true;
            try {
                init();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(gameManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
