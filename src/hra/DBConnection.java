/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;
import java.sql.*;
/**
 *
 * @author jan
 */
public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/projekt?useTimezone=true&serverTimezone=UTC","root","root");
            st=con.createStatement();
     
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void InsertData(String user, int score){
        try{
            String query="insert into high_score values('"+user+"',"+score+")";
            int insert=st.executeUpdate(query);
            System.out.println("insert: "+insert);
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String getHS(){
        try{
            String query="select max(score) from high_score";
            rs=st.executeQuery(query);
            while(rs.next()){
                String name=rs.getString(1);
                return name;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return "";
    }
}
