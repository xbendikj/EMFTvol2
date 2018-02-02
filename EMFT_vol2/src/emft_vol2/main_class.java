/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emft_vol2;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;


/**
 *
 * @author Jozef
 */
public class main_class {
public static String memory_path="none";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // language frame
         languageChooser_Frame.main(args);
        while (true) { //wait for information that user has choosen a language
            try {
                Thread.sleep(100);                 //1000 milliseconds is one second. wait to not get overcycled
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Boolean status = languageChooser_Frame.getStatus();
            if (status.equals(true)) {             //has user choosen the language in language Chooser frame ?
                break;
            }

        }
        
             String userhome = System.getProperty("user.dir");
             File subor = new File(userhome  + "\\memory.txt");
      try {
         Scanner input = new Scanner(subor);
         String EmptyLine;
         
         EmptyLine= input.nextLine(); // first empty line info line
         memory_path=input.nextLine();

          } catch (FileNotFoundException ex) {

        }
        
        
        // main frame
        main_Jframe.main(args); //spust main frame
        constants.loadConstants(); // nacitaj konštanty
        constants.setMu0(4*Math.PI*0.0000001);
        constants.setEpsi0(8.854187817620E-12);
        
     
          
       
        
        

        
            
        
        
       
        
        
        
    }
    
}
