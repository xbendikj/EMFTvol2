/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;

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
public class SQLlite_constants {
    
    private static Connection con;
    private static boolean hasData =false;
    
    public ResultSet displayConstants() throws ClassNotFoundException, SQLException{
        if (con == null) {
            getConnection();
        }
       
       Statement state = con.createStatement();
       ResultSet res = state.executeQuery("SELECT Freq,Mu0,Epsi0,Mu1,Epsi1 FROM constants");
       return res;
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:SQLliteconstants.db");
        inicialize();
    }

    private void inicialize() throws SQLException {
        if(!hasData ){
            hasData = true;
             Statement state = con.createStatement();
             ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='constants'");
             if( !res.next()){
                 help.printl("Building the user table with setted values",true);
                 // Postavime tabulku
                 Statement state2 = con.createStatement();
                 state2.execute("CREATE TABLE constants(id integer,"
                                + "Freq varchar(60)," + "Mu0 varchar(60)," + "Epsi0 varchar(60),"+ "Mu1 varchar(60)," + "Epsi1 varchar(60),"+ "primary key(id));");
                 
                 // vlozime data
                 PreparedStatement prep = con.prepareStatement("INSERT INTO constants values( ?,?,?,?,?,?   );");
                  prep.setString(2,"50");
                  prep.setString(3,"0.000001");
                  prep.setString(4,"0.000002");
                  prep.setString(5,"0.000002");
                  prep.setString(6,"0.000002");
                  prep.execute();
                  

                  
      }                
     }
    }
    
    /**
     * NEPOUZIVAT
     * @param Freq
     * @throws ClassNotFoundException
     * @throws SQLException 
     */    
    public void addFreq(String Freq) throws ClassNotFoundException, SQLException{
        
        if(con == null) {
            getConnection();
        }
        
        PreparedStatement prep = con.prepareStatement("INSERT INTO user values( 1,?,?   );");
        prep.setString(2, Freq);
        prep.execute();
    }
    
     /**
      * update of CONSTANTS TABLE
      * @param name of parameter that you wanto to change
      * @param ID of paramater for constants it is always 1
      * @param value on witch we want to change
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     public void update(String name,int ID,double value) throws ClassNotFoundException, SQLException{
        
        if(con == null) {
            getConnection();
        }
         Statement stmt = null;
         stmt = con.createStatement();
        String sql = "UPDATE constants set "+name+" = "+String.valueOf(value)+" where ID="+String.valueOf(ID)+";";
        stmt.executeUpdate(sql);
        //con.commit();
    }
    
}
