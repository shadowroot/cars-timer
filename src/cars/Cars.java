/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author jonny
 */
public class Cars {
private static String[] credentials;
private static racers racers = null;
private static mainFrame mainFrame = null;
private static String csv = new String();
private static String csvFile = "racers.csv";
private static serial serial = null;
private static char[] tmp = null;
private static int maxSize = 0x800;
private static boolean debug = true;
    
    public static void putCredentials(String[] str) throws IOException{
        credentials = str;
        writeCSV(credentials);
    }
    
    public static void readCSV(String str) throws IOException{
        
        tmp = new char[maxSize];
        FileReader fstream = new FileReader(str);
        try (BufferedReader bReader = new BufferedReader(fstream)) {
            bReader.read(tmp);
        }
        csv = tmp.toString();
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
}
