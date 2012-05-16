/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cars;

/**
 *
 * @author jonny
 */
public class functions {
    
    private static String[] tmpStrings=null;
     private static String[] tmpStringArr=null;
     private static int[] size=null;
    
    public static void push(String[] arStr,String str){
        tmpStringArr=new String[arStr.length+1];
        tmpStringArr[tmpStringArr.length-1] = str;
        cpyArrays(arStr, tmpStringArr);
        arStr=tmpStringArr;
    }
    public static String pop(String[] arStr){
        String str;
        tmpStringArr=new String[arStr.length-1];
        str = arStr[arStr.length-1];
        cpyArrays(arStr, tmpStringArr);
        arStr=tmpStringArr;
        return str;
    }
    public static void cpyArrays(String[] src,String[] dest){
        System.arraycopy(src, 0, dest, 0, src.length);
    }
    public static void cpyArrays(int[] src,int[] dest){
        System.arraycopy(src, 0, dest, 0, src.length);
    }
    public static void cpyArrays(long[] src,long[] dest){
        System.arraycopy(src, 0, dest, 0, src.length);
    }
    
    
    public static int resize(String[] arr){
        String[] newArr = new String[arr.length*2];
        cpyArrays(arr, newArr);
        newArr[arr.length]=null;
        arr = newArr;
        return arr.length;
    }
    
    public static int[] equals (Object a,Object b){
        
        
        return size;
    }
    
}
