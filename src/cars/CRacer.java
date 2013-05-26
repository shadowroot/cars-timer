/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author jonny
 */
public class CRacer implements Serializable{
    public long  id;
    private String firstname;
    private String surname;
    public List<EClass> classes;
    public Map<Date,CRace> raceHistory;
    /**
     * 
     * @param id
     * @param firstname
     * @param surname 
     */
    public CRacer(long id,String firstname,String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        classes = new LinkedList<EClass>();
        raceHistory = new HashMap<Date, CRace>();
    }
    /**
     * Adding race
     * @param date
     * @param race 
     */
    public void addRace(Date date,CRace race){
        raceHistory.put(date, race);
    }
    /**
     * Adding classes
     * @param eclass 
     */
    public void addClass(EClass eclass){
        classes.add(eclass);
    }
    /**
     * Getter for race from racer.
     * @param date Date of race.
     * @return 
     */
    public CRace getRace(Date date){
        return raceHistory.get(date);
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public String Name(){
        return firstname + " " + surname;
    }
    
    public String exportRacer() throws IOException{
        String ret = new String();
        ret += firstname + ",";
        ret += surname + ",";
        for(EClass c : classes){
            ret += c.getName() + ":";
        }
        return ret;
    }

}
