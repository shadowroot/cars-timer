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
    private ArrayList<Long> lap_times;
    private int laps = 0;
    private Date date;
    private long max = 0;
    
    
    CLaps(){
        lap_times = new ArrayList<Long>();
        date = new Date();
        last = date.getTime();
    }
    
    public void setStartTime(long time){
        last = time;
    }
    
    public int getLapsCount(){
        return laps;
    }
    
    public long getLapScore(){
        return max;
    }
    
    public void addLap(long lap_time){
        long time = lap_time - last;
        lap_times.add(time);
        last = lap_time;
        max += time;
        laps++;
    }
    
    public boolean testLap(long time){
        if((time - last) < 2000){
            return false;
        }
        return true;
    }

    public String getLapTimes(){
        String ret = "";
        for( long lap : lap_times ){
                ret += "\t" + formatTime(lap) + "";
        }
        return ret;
    }
    
    public boolean finished(long time){
        if(last >= time || last + 2 * 60 * 1000 >= time){
            return true;
        }
        return false;
    }
    
    public long getLast(){
        return last;
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
    
}
