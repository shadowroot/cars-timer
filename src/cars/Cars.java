/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.*;

/**
 *
 * @author jonny
 */
public class Cars {
private static String[][] credentials;
private static String[][] tmpcsv=null;
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
    
    public static void putCredentials(String[] str) throws IOException{
        tmpcsv = new String[credentials.length+1][3];
        credentials = tmpcsv;
        credentials[credentials.length-1] = str;
        writeCSV(str);
    }
    
    public static void readCSV(String str) throws IOException{
        
        tmp = new char[maxSize];
        FileReader fstream = new FileReader(str);
        try (BufferedReader bReader = new BufferedReader(fstream)) {
            bReader.read(tmp);
        }
        csv = tmp.toString();
        char[] buf = new char[csv.length()];
        buf=csv.toCharArray();
        int size = 0;
        for(int i=0;i<buf.length;i++){
            if(buf[i] == ','){
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
        readCSV(csvFile);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //serial = new serial();
        mainFrame = new mainFrame();
        mainFrame.show();
        initRacers();
        if(debug){
            test t = new test();
        }
        
    }
    public static void newRaces(){
        racers = new racers();
        
        racers.show();
    }
    public static void racerList(){
        
    }
    public static void newRace(){
        
    }
}
