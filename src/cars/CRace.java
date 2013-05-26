/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Race holding map of laps and their lap times. By getting size of list gets laps laps. Unconfirmed is last time to do a correction.
 * @author jonny
 */
public class CRace {
    private EClass category;
    private Map<CRacer, SortedSet<Long> > laps;
    public Long raceStart;
    private boolean running;
    private Date paused;
    private Date resumed;
    public CRacer lastRacer;
    public LinkedList<CRacer> racers;
    public int totalLaps = 0;
    private long id = 0;
    /**
     * Constructor category
     * @param category
     * @param lapsList 
     */
    public CRace(long id,EClass category,LinkedList<CRacer> lapsList){
        this.category = category;
        laps = new HashMap<CRacer, SortedSet<Long>>();
        for(CRacer racer : lapsList){
            laps.put(racer, new TreeSet<Long>());
        }
        this.id = id;
    }
    
    /**
     *
     * @param id
     */
    public CRace(long id,EClass category,LinkedList<CRacer> racers,Map<CRacer, SortedSet<Long> > laps){
        this.id = id;
        this.category = category;
        this.laps = laps;
        this.racers = racers;
    }
    
    
    public void RaceStart(){
        raceStart = (new Date()).getTime();
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
    
    public long nextLap(CRacer racer,long lastLap){
        laps.get(racer).add(lastLap);
        if(laps.get(racer).size() > totalLaps){
            totalLaps = laps.get(racer).size();
        }
        return lastLap;
    }
    
    public String getLapTimes(CRacer racer){
        String ret = new String();
        long current_time = 0,beforeTime = raceStart;
        Iterator<Long> time = laps.get(racer).iterator();
        ret = ""+racer.id;
        ret += racer.Name() + "\t";
        while(time.hasNext()){
            current_time = time.next();
            ret += formatTime(current_time-beforeTime);
            beforeTime = current_time;
        }
        return ret;
    }
    
    public String formatTime(long  time){
        String t = new String();
        long ms;
        int min,hrs,sec;
        ms = time % 1000;
        time /= 1000;
        sec = (int) (time % 60);
        time /= 60;
        min = (int) (time % 60);
        time /= 60;
        hrs = (int) (time%60);
        time /= 60;
        if(hrs > 0){
            t += hrs + ":";
        }
        if(min > 0){
            t += min + ":";
        }
        if(sec > 0){
            t += sec + ".";
        }
        if(ms > 0){
            t += ms;
        }
        return t;
    }
    
    public void modify(CRacer racer,Long lastLap){
        laps.get(lastRacer).remove(lastLap);
        laps.get(racer).add(lastLap);
    }
    /**
     * Export csv string
     * @return 
     */
    public String exportRace(){
        String ret = new String();
        ret = id + raceStart + "\n\n";
        for(CRacer r : racers){
            SortedSet<Long> set = laps.get(r);
            Iterator<Long> it = set.iterator();
            while(it.hasNext()){
                ret += it.next() + ",";
            }
            ret += "\n";
        }
        ret += "\n";
        return ret;
    }
    
    
}
