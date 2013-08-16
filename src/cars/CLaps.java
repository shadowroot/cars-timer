/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jonny
 */
public class CLaps {
    private long last = 0;
    private long best = 0;
    private ArrayList<Long> lap_times;
    private Date date;
    
    CLaps(){
        lap_times = new ArrayList<Long>();
        date = new Date();
    }
    
    public void addLap(long lap_time){
        last = lap_time;
        lap_times.add(lap_time);
    }
    
    public long calculateBetween(long first,long second){
        return (first - second);
    }
    
    public long getBest(){
        long min = Long.MAX_VALUE;
        for(long lap : lap_times){
            if(lap < min){
                min = lap;
            }
        }
        return min;
    }
    
    public long getLast(){
        return last;
    }
    
    public String getLapTimes(){
        String ret = "<html>";
        ret += "\t|<i> " + getLast() + " </i>| " + "\t||<b> " + getBest() + " </b>||";
        for( long lap : lap_times ){
            if(lap == best){
                ret += "\t<b>" + lap + "</b>";
            }
            else{
                ret += "\t" + lap;
            }
            
        }
        ret += "</html>";
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
    
    public ArrayList<Long> getArray(){
        return lap_times;
    }
    
    public void save(String filename){
        
    }
    
}
