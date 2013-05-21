/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Race holding map of racers and their lap times. By getting size of list gets racers laps. Unconfirmed is last time to do a correction.
 * @author jonny
 */
public class CRace {
    private EClass category;
    private Map<CRacer, SortedSet<Long> > racers;
    private Map<Long,CRacer> history;
    private Date raceDate;
    private boolean running;
    private Date paused;
    private Date resumed;
    /**
     * Constructor category
     * @param category
     * @param racersList 
     */
    public CRace(EClass category,LinkedList<CRacer> racersList){
        this.category = category;
        racers = new HashMap<CRacer, SortedSet<Long>>();
        history = new HashMap<Long, CRacer>();
        for(CRacer racer : racersList){
            racers.put(racer, new TreeSet<Long>());
        }
    }
    
    public void RaceStart(){
        raceDate = new Date();
        running = true;
    }
    
    public void pause(){
        if(running){
            paused = new Date();
            running = false;
        }
    }
    
    public void resumed(){
        if(!running){
            running = true;
            resumed = new Date();
        }
    }
    
    public long nextLap(CRacer racer){
        long lastLap = (new Date()).getTime();
        racers.get(racer).add(lastLap);
        history.put(lastLap, racer);
        return lastLap;
    }
    
    public void modify(CRacer racer,Long lastLap){
        racers.get(history.remove(lastLap)).remove(lastLap);
        racers.get(racer).add(lastLap);
    }
    
    
    
    
}
