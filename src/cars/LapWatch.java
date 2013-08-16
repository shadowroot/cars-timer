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

/**
 *
 * @author jonny
 */
public class LapWatch implements RaceInterface{
    private List<CRacer> racers;
    private Map<CRacer,CLaps > laps;
    private CRacer current_racer;
    private long lap_start;
    private boolean lap_started;
    private boolean race_ready = false;
    private boolean lap_end = false;
    private WRace wrace;

    public LapWatch(List<CRacer> racers,WRace wrace) {
        this.wrace = wrace;
        this.racers = racers;
        laps = new HashMap<CRacer, CLaps>();
        for(CRacer racer : this.racers){
            laps.put(racer, new CLaps());
        }
    }
    
    public void setActiveRacer(CRacer racer){
        current_racer = racer;
    }
    
    public void startRace(){
        race_ready = true;
    }
    
    public boolean isRacing(){
        return lap_started;
    }
    
    public long getStartTime(){
        return lap_start;
    }
    
    @Override
    public void lapBreak(){
        if(race_ready){
            long now = (new Date()).getTime();
            if(lap_started){
                insertLap(now - lap_start);
                lap_started = false;
                lap_end = true;
                wrace.revalidateValues();
            }
            else{
                lap_start = now;
                lap_started = true;
            }
            if(lap_end){
                race_ready = false;
                lap_started = false;
                lap_end = false;
            }
        }
    }
    
    private void insertLap(long lap){
        long min = Long.MAX_VALUE;
        laps.get(current_racer).addLap(lap);
        System.out.println(current_racer.Name() + " " + lap);
        for(long l : laps.get(current_racer).getArray()){
            if(l < min){
                min = l;
            }
        }
    }
    
    public HashMap<Integer,CRacer> getPositions(){
        long min = Long.MAX_VALUE;
        CRacer minRacer = null;
        List<CRacer> racers = this.racers;
        HashMap<Integer,CRacer> pos = new HashMap<Integer,CRacer>();
        for(int index = 1; index <= racers.size(); index++){
            for(CRacer racer : racers){
                if(laps.get(racer).getBest() < min){
                    min = laps.get(racer).getBest();
                    minRacer = racer;
                }
            }
            pos.put(index, minRacer);
            racers.remove(minRacer);
        }
        
        return pos;
    }
    
    
    public CLaps getLaps(CRacer racer){
        return laps.get(racer);
    }
    
    
}
