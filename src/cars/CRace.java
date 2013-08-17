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
import java.util.Queue;

/**
 *
 * @author jonny
 */
public class CRace implements RaceInterface{
    private List<CRacer> racers;
    private Map<CRacer,CLaps > laps;
    private long race_start_time;
    private long race_stop_time;
    private boolean race_started = false;
    private boolean race_stopped = false;
    private boolean race_final_end = false;
    private WRace wrace;
    private Queue<Long> lap_queue;

    public CRace(List<CRacer> racers,WRace wrace) {
        this.wrace = wrace;
        this.racers = racers;
        laps = new HashMap<CRacer, CLaps>();
        lap_queue = new LinkedList<Long>();
        for(CRacer racer : racers){
            laps.put(racer, new CLaps());
        }
    }
    
    public void startRace(){
        race_start_time = (new Date()).getTime();
        race_started = true;
        for(CRacer racer : racers){
            laps.get(racer).setStartTime(race_start_time);
        }
    }
    
    public boolean isRacing(){
        return race_started;
    }
    
    public long getStartTime(){
        return race_start_time;
    }
    
    @Override
    public void lapBreak(){
        if(race_started && !race_final_end){
                long now = (new Date()).getTime();
                lap_queue.add(now);
        }
    }
    
    public void stopRace(){
        race_stopped = true;
        race_stop_time = (new Date()).getTime();
    }
    
    public void finish(){
        race_final_end = true;
    }
    
    public boolean isQueued(){
        if(lap_queue.size() > 0){
            return true;
        }
        return false;
    }
    
    public void chooseLap(CRacer racer){
        if(laps.get(racer).testLap(lap_queue.peek())){
            laps.get(racer).addLap(lap_queue.remove());
        }
        else{
            lap_queue.remove();
        }
    }
    
    public HashMap<Integer,CRacer> getPositions(){
        long min = Long.MAX_VALUE;
        int count = 0;
        CRacer minRacer = null;
        List<CRacer> rac = new LinkedList<CRacer>(this.racers);
        HashMap<Integer,CRacer> pos = new HashMap<Integer,CRacer>();
        for(int index = 1; index <= racers.size(); index++){
            for(CRacer racer : rac){
                if(laps.get(racer).getLast() > count){
                    count = laps.get(racer).getLapsCount();
                    minRacer = racer;
                    min = laps.get(racer).getLast();
                }
                else if(laps.get(racer).getLapsCount() == count && laps.get(racer).getLast() < min){
                    minRacer = racer;
                    min = laps.get(racer).getLast();
                }
                else{
                    minRacer = racer;
                    min = laps.get(racer).getLast();
                }
            }
            pos.put(index, minRacer);
            rac.remove(minRacer);
        }
        
        return pos;
    }
    
    
    public CLaps getLaps(CRacer racer){
        return laps.get(racer);
    }
    
    
}
