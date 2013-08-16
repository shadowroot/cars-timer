/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;

/**
 *
 * @author jonny
 */
public class RunCars{
    
    
    private static JCheckBoxMenuItem[] com_ports;

    static private CCars cars;

    public RunCars(){
        
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //serial.main(args);
        
        
        try {
            cars = new CCars();
        } catch (IOException ex) {
            Logger.getLogger(RunCars.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //initRacers();
        
        
        /*
        if(debug){
            test t = new test();
        }
        */
       
        
    }
    
    
}
