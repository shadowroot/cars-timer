/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;
import gnu.io.CommPortIdentifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.Timer;

/**
 *
 * @author jonny
 */
public class Cars {
private static String[][] credentials;
private static String[][] racersReady = null;
private static String[][] tmpcsv=null;
private static int[] positions = null;
private static int[] ids = null;
private static int[] laps = null;
private static racers racers = null;
private static mainFrame mainFrame = null;
private static String csv = new String();
private static String csvFile = "racers.csv";
private static serial serial = null;
private static char[] tmp = null;
private static int maxSize = 0x800;
private static boolean debug = true;
private static String[] cols=null;
private static int id=0;
private static Date date=null;
private static long start_time=0;
private static long stop_time=0;
private static long[] times=null;
private static long now_time=0;
private static String cPort = "COM3";
private static JCheckBoxMenuItem[] com_ports;

    public Cars() throws IOException{
        initRacers();
    }
    
    public static void putCredentials(String[] str) throws IOException{
        tmpcsv = new String[credentials.length+1][3];
        credentials = tmpcsv;
        credentials[credentials.length-1] = str;
        writeCSV(str);
    }
    
    public static void readCSV(String str) throws IOException{
        
        tmp = new char[maxSize];
        FileReader fstream = new FileReader(str);
        try {
            BufferedReader bReader = new BufferedReader(fstream);
            bReader.read(tmp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        csv = tmp.toString();
        char[] buf = new char[csv.length()];
        buf=csv.toCharArray();
        int size = 0;
        for(int i=0;i<buf.length;i++){
            if(buf[i] == (char)','){
                size++;
            }
        }
        cols=new String[size];
        cols = csv.split(",");
        id=0;
        for(int i = 0;i<cols.length;i+=3){
           tmpcsv = new String[credentials.length+1][3];
            credentials = tmpcsv;
            for(int u=0;u<3;u++){
                credentials[id][u] = cols[i+u];
            }
            id++;
        }
    }
    
    
    /**
     * Find out if checkboxes with racers is check or not.
     */
    public static void determineRacers(int[] racInt){
       
       racersReady=new String[racInt.length][3];
       for(int i=0;i<racInt.length;i++){
           racersReady[i]=credentials[racInt[i]];
           ids[i]=racInt[i]; 
       }
    }
    
    public static void writeCSV(String[] str) throws IOException{
        FileWriter fstream = new FileWriter(csvFile);
        try (BufferedWriter bWriter = new BufferedWriter(fstream)) {
            for(int i=0;i<str.length;i++){
                csv += str[i]+",";
            }
            System.out.println(csv);
            bWriter.write(csv);
        }
        
    }
    public static void initRacers() throws IOException{
        date = new Date();
        System.out.println(date.toString());
        readCSV(csvFile);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        //serial.main(args);
        mainFrame = new mainFrame();
        Enumeration e = CommPortIdentifier.getPortIdentifiers();
        int i=0;
         String name="";
         com_ports = new JCheckBoxMenuItem[1];
        while(e.hasMoreElements()){
            JCheckBoxMenuItem[] temp = new JCheckBoxMenuItem[(i+1)];
            temp=com_ports; 
            com_ports=temp;
          CommPortIdentifier cpi = (CommPortIdentifier)e.nextElement(); 
          name=cpi.getName();
        com_ports[i] = new JCheckBoxMenuItem();
        com_ports[i].setSelected(true);
        com_ports[i].setText(name);
        com_ports[i].setActionCommand(name);
        
        
        com_ports[i].addActionListener(new java.awt.event.ActionListener() {
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
        
    }
    public static void newRaces(){
        racers = new racers();
    }
    public static void racerList(){
        
    }
    public static void newRace(){
        Race r = new Race();
        Timer t = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGraphics();
            }
        });
        t.start();
    }
    public static void breakLine(){
        date=new Date();
        stop_time = date.getTime();
    }
    public static void positionDet(int id){
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
    }
    public static long getMax(long[] data){
        long tmp=0;
        int id=0;
        int i=0;
            for(i=0;i<data.length;i++){
                if(tmp < data[i]){
                    tmp=data[i];
                    id=i;
                }
            }
            
        return tmp;
    }
    public static void updateGraphics(){
        Race.updatePanels(credentials, times);
    }
}
