/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

import java.io.Serializable;

/**
 *
 * @author jonny
 */
public enum EClass implements Serializable{
    F103("F103",1),C4X4("4x4",2),Open("Open",3);
    private String name;
    private int type = 0;
    private EClass(String str,int type){
        name = str;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    
}
