/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import gnu.io.CommPortIdentifier;
import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.JCheckBoxMenuItem;
import sun.audio.*;
/**
 *
 * @author jonny
 */


public class Cars {
public static String[][] credentials;
private static String[][] racersReady = null;
private static String[][] tmpcsv=null;
private static String[] tmpStringArr=null;
private static int[] positions = null;
private static int[] ids = null;
private static long[][] laps = null;
private static racers racers = null;
private static mainFrame mainFrame = null;
private static String csv = new String();
public static String csvFile = "racers.csv";
private static serial serial = null;
private static String tmp = null;
private static int maxSize = 0x800;
private static boolean debug = true;
public static int cols=3;
private static int id=0;
private static Date date=null;
private static long start_time=0;
private static long stop_time=0;
private static long tmpTime = 0;
private static long endTime = 0;
private static long[][] times=null;
private static long now_time=0;
private static String cPort = "COM3";
private static JCheckBoxMenuItem[] com_ports;
private static long[][] results=null;//lap results +-
private static int[] places = null;//racers places
private static InputStream sound = null;
private static AudioStream asBreak=null;
private static int[] intLapLastIndex = null;


    public Cars() throws IOException{
        initRacers();
    }
    
    
    
    public static void putCredentials(String[] str) throws IOException{
        int len=0;
        if(credentials != null){
            len = credentials.length;
            tmpcsv = new String[len+1][cols];
            for(int i=0;i<credentials.length;i++){
                System.arraycopy(credentials[i],0 ,tmpcsv[i],0 ,credentials[i].length);
            }
            
        }
        else{
            tmpcsv = new String[1][cols];
        }
        
        credentials=tmpcsv;
        credentials[credentials.length-1][0] = str[0];
        credentials[credentials.length-1][1] = str[1];
        credentials[credentials.length-1][2] = str[2];
        writeCSV(credentials);
        mainFrame.addRacers(Cars.credentials);
    }
    /*
     * Last results
     */
    
    public static void setLaps(long[][] str){
        laps = null;
        laps = str;
    }
    
    
    public static void readCSV(String str,String[][] table) throws IOException{
        
        String line = new String();
        
        try {
            FileReader fstream = new FileReader(str);
            BufferedReader bReader = new BufferedReader(fstream);
            while((line=bReader.readLine()) != null){
                tmp+=line;
            }
            bReader.close();
        }
        
        catch (IOException e){
            e.printStackTrace();
            
        }
        int i=0;
        int p=0;
        
        String[] aSplited = tmp.split(",");
        table = new String[aSplited.length/cols][cols];
        while(i<aSplited.length-1){
            for(int u=0;u<cols;u++){
                table[p][u] = aSplited[i];
                i++;
            }
        p++;
        
        }
        putCredentialis(table);
    }
    
    public static void putCredentialis(String[][] table){
        int i=0;
        int u=0;
        if(credentials != null){
            tmpcsv = new String[table.length+credentials.length][cols];
            for(i=0;i<credentials.length;i++){
                System.arraycopy(credentials[i],0, tmpcsv[i],0,credentials[i].length);
                
            }
            for(u=0;u<table.length;u++){
                System.arraycopy(table[u],0,tmpcsv[i+u],0,table[u].length);
            }
            
        }
        else{
            tmpcsv = table;
        }
        
       
        credentials=tmpcsv;
        
    }
    
    
    /**
     * Find out if checkboxes with racers is check or not.
     */
    public static void determineRacers(int[] racInt){
       
       racersReady=new String[racInt.length][cols];
       for(int i=0;i<racInt.length;i++){
           racersReady[i]=credentials[racInt[i]];
           
           
       }
       Race r = new Race();
       Race.putCredentials(racersReady);
       
       
    }
    
    public static void writeCSV(String[][] str) throws IOException{
        
        try{
            FileWriter fstream = new FileWriter(csvFile);
            BufferedWriter bWriter = new BufferedWriter(fstream);
            
            for(int i=0;i<str.length;i++){
                for(int u=0;u<cols;u++){
                    if(str[i][u] != null){
                        csv += str[i][u]+",";
                    }
                }
            }
            
            
            bWriter.write(csv);
            bWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
    }
    public static void initRacers() throws IOException{
        readCSV(csvFile,credentials);
        mainFrame.addRacers(credentials);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        //serial.main(args);
        sound = new FileInputStream("sounds/breaksound.wav");
        mainFrame = new mainFrame();
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        
        int i=0;
         
         com_ports = new JCheckBoxMenuItem[3];
       
        while(e.hasMoreElements()){
          CommPortIdentifier cpi = (CommPortIdentifier)e.nextElement(); 
          String name=cpi.getName();
        com_ports[i] = new JCheckBoxMenuItem();
        com_ports[i].setSelected(false);
        com_ports[i].setText(name);
        com_ports[i].setActionCommand(name);
        
        
        com_ports[i].addActionListener(new java.awt.event.ActionListener() {
                @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cPort = evt.getActionCommand();
                for(int i=0;i<com_ports.length;i++){
                    if(com_ports[i] != evt.getSource()){
                        com_ports[i].setSelected(false);
                    }
                }
            }
        });
        mainFrame.getComMenu().add(com_ports[i]);
        i++;
        
        
        }
        //initRacers();
        
        
        /*
        if(debug){
            test t = new test();
        }
        */
        initRacers();  
        
    }
    public static void newRaces(){
        racers = new racers();
    }
    
    
    public static void breakLine() throws IOException, InterruptedException{
        asBreak = new AudioStream(sound);
        AudioPlayer.player.start(asBreak);
       
        Race.serial();
    }
    
    
    public static void positionDet(long[][] laps){
        
        /*
        now_time=(new Date()).getTime();
        times[id]=stop_time;
        long tmp =0;
        long tp = getMax(times);
        while(times[times.length-1] == tp)
        for(int i=0;i<ids.length;i++){
            tmp = now_time-times[i];
            if(tmp < now_time-times[ids[i]]){
                positions[i] = i;
                times[i] = stop_time;
            }
        } 
        */
        
    }
    
    
    public static long getMax(long[] data){
        long tmpik=0;
        int idik=0;
        int i=0;
            for(i=0;i<data.length;i++){
                if(tmpik < data[i]){
                    tmpik=data[i];
                    idik=i;
                }
            }
            
        return tmpik;
    }

}
