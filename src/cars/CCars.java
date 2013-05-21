/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jonny
 */


public class CCars {
private  WMainFrame mainFrame = null;
private  String csv = new String();
public ArrayList<CRacer> racers;
public CRace current_race;
public  String csvRacers = "racers.csv";
private String csvRaceHistory = "race.csv";
public long lastLap = 0;
private static long last_id;
public int keys = 0;


    public CCars() throws IOException{
        initRacers();
    }
    
    /**
     * Importing racers from CSV.
     * firstname,surname,F103:4x4
     * ...
     * @param s  table row
     */
    public void importRacer(String s){
        String firstname,surname;
        String[] params = s.split(",");
        firstname = params[0];
        surname = params[1];
        CRacer r = new CRacer(last_id, firstname, surname);
        String[] categories = params[2].split(":");
        for(Object cat : categories){
            r.classes.add((EClass) cat);
        }
        racers.add(r);
    }
    /**
     * 
     * @param racer 
     */
    public void addRacer(CRacer racer){
       racers.add(racer);
        
    }
    
    
    /**
     * Reading racers from csv db.
     * @param filename name of file .csv
     * @throws IOException 
     */
    public  void readRacers(String filename) throws IOException{
        
        String line = new String();
        try {
            FileReader fstream = new FileReader(filename);
            BufferedReader bReader = new BufferedReader(fstream);
            while((line=bReader.readLine()) != null){
                importRacer(line);
            }
            bReader.close();
        }
        
        catch (IOException e){
            e.printStackTrace();
            
        }
    }
    /**
     * Writing racers by exporting
     * @param filename 
     */
    public void writeRacers(String filename) throws IOException{
        String out = new String();
        FileWriter fstream = new FileWriter(filename);
        for(CRacer r : racers){
            out += r.exportRacer() + "\n";
        }
        fstream.write(out);
        fstream.close();
    }
    
    public void readRace(String race){
        
    }
    
    public void nextLap(){
        while(keys == 0){}
        lastLap = (new Date()).getTime();
        current_race.nextLap(racers.get(keys));
    }
    
    public  void initRacers() throws IOException{
        readRacers(csvRacers);
        mainFrame = new WMainFrame((this));
    }
    
    /**
     * @param args the command line arguments
     */
    
    public  void newRaces(){
        WRacers Wracers = new WRacers(this);
    }

    private void EClass(String cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   

}
