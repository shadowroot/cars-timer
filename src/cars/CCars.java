/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author jonny
 */


public class CCars {
public ArrayList<CRacer> racers = new ArrayList<CRacer>();
public RaceInterface current_race;
public  String csvRacers = "racers.csv";
private String csvRaceHistory = "race.csv";
private ArrayList<CRace> history = new ArrayList<CRace>();
public long lastLap = 0;
public long racer_id=0;
public long race_id = 0;
public int keys = 0;
public CSerial serial;
public WMainFrame main_window;
public int race_type = 1;


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
        CRacer r = new CRacer(racer_id++, firstname, surname);
        String[] categories = params[2].split(":");
        for(String cat : categories){
            
            if(cat == "4x4"){
                r.classes.add(EClass.C4X4);
            }
            else if(cat == "Open"){
                r.classes.add(EClass.Open);
            }
            else if(cat == "F103"){
                r.classes.add(EClass.F103);
            }
        }
        boolean add = racers.add(r);
    }
    /**
     * 
     * @param racer 
     */
    public void addRacer(CRacer racer){
       racers.add(racer);
        
    }
    
    public void selectRace(RaceInterface r){
        current_race = r;
    }
    
    
    /**
     * Reading racers from csv db
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
        catch(FileNotFoundException ex){
            File f = new File(filename);
            f.createNewFile();
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
    /**
     * 
     * @param filename
     * @throws IOException 
     */
    public void readRaces(String filename) throws IOException{
        boolean head = false;
        String line="",header=null,table = new String();
        try {
            FileReader fstream = new FileReader(filename);
            BufferedReader bReader = new BufferedReader(fstream);
            while(line != null){
                if(!head){
                    header=bReader.readLine();
                    head = true;
                }
                line = bReader.readLine();
                if(line == ""){
                    head = false;
                    importRace(header, table);
                    table = new String();
                }
                table += line + "\n";
            }
            bReader.close();
        }
        catch(FileNotFoundException ex){
            File f = new File(filename);
            f.createNewFile();
        }
        catch (IOException e){
            e.printStackTrace();
            
        }
    }
    /**
     * Structure
     * 
     * RACE_NUMBER RACE_DATE CATEGORY\n
     * ID,time1,time2,time3,...............\n
     * \n
     * 
     * @param race 
     */
    public void importRace(String header,String race){
        LinkedList<CRacer> racers = new LinkedList<CRacer>();
        Map<CRacer, SortedSet<Long> > laps = new HashMap<CRacer, SortedSet<Long>>();
        EClass category = EClass.Open;
        String[] head = header.split(" ");
        String[] rows = race.split("\n");
        String[] row;
        if(head[2] == "F103"){
            category = EClass.F103;
        }
        else if(head[2] == "4x4"){
            category = EClass.C4X4;
        }
        
        
        for(String r : rows){
            row = r.split(",");
            racers.add(this.racers.get(Integer.parseInt(row[0])));
            SortedSet<Long> times = new TreeSet<Long>();
            for(int i = 1; i < row.length ; i++){
                times.add(Long.parseLong(row[i]));
            }
            laps.put(this.racers.get(Integer.parseInt(row[0])), times);
        }
        CRace crace = new CRace(Long.parseLong(head[0]),category ,racers,laps);
        crace.raceStart = Long.parseLong(head[1]);
        history.add(crace);
    }
    
    public void nextLap(){
        current_race.lapBreak();
    }
    
    public  void initRacers() throws IOException{
        readRacers(csvRacers);
        main_window = new WMainFrame((this));
    }
    
    /**
     * @param args the command line arguments
     */
    
    public  void newRaces(){
        WRacers Wracers = new WRacers(this);
    }
    
   

}
