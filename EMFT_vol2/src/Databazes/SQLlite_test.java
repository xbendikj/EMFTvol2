/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databazes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * template pre databazu, ktora bude v sebe uchovavať informacie o stožiary, a predhadzajucich nastaveniach
 * @author Jozef
 * vychadzame z https://www.youtube.com/watch?v=JPsWaI5Z3gs&t=479s
 */
public class SQLlite_test {
    
    private static Connection con;
    private static boolean hasData =false;
    
    public ResultSet displayUsers() throws ClassNotFoundException, SQLException{
        if (con == null) {
            getConnection();
        }
       
       Statement state = con.createStatement();
       ResultSet res = state.executeQuery("SELECT fname,lname FROM user");
       return res;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLliteTest1.db");
        inicialize();
    }

    private void inicialize() throws SQLException {
        if(!hasData ){
            hasData = true;
             Statement state = con.createStatement();
             ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
             if( !res.next()){
                 System.out.println("Building the user table with kokot values");
                 // Postavime tabulku
                 Statement state2 = con.createStatement();
                 state2.execute("CREATE TABLE user(id integer,"
                                + "fName varchar(60)," + "lName varchar (60)," + "primary key(id));");
                 
                 // vlozime data
                 PreparedStatement prep = con.prepareStatement("INSERT INTO user values( ?,?,?   );");
                  prep.setString(2,"John");
                  prep.setString(3,"Buch");
                  prep.execute();
                  
                  PreparedStatement prep2 = con.prepareStatement("INSERT INTO user values( ?,?,?   );");
                  prep2.setString(2,"Fero");
                  prep2.setString(3,"rohlik");
                  prep2.execute();
                  
      }                
     }
    }
    
    public void addUser(String firstname, String lastName) throws ClassNotFoundException, SQLException{
        
        if(con == null) {
            getConnection();
        }
        
        PreparedStatement prep = con.prepareStatement("INSERT INTO user values( ?,?,?   );");
        prep.setString(2, firstname);
        prep.setString(3, lastName);
        prep.execute();
    }
    
    
}
