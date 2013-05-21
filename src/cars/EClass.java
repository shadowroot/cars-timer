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
    F103("F103"),C4X4("4x4"),Open("Open");
    private String name;
    private EClass(String str){
        name = str;
    }
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
