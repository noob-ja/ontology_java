/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author JA
 */
public class Concept {
    private Concept parent;
    private ArrayList<Concept> child;
    private String value;
    private HashMap<String,Integer> GUIData;
    public int ID;
    
    public Concept(String v, int ID){
        parent = null;
        child = new ArrayList<>();
        this.value = v;
        this.ID = ID;
        this.GUIData = new HashMap<>();
    }
    
    public void setGUIData(String variable, int value){
        this.GUIData.put(variable,value);
    }
            
    public int getGUIData(String data){
        return this.GUIData.get(data);
    }
    
    public void setParent(Concept p){
        this.parent = p;
    }
    
    public void setValue(String v){
        this.value = v;
    }
    
    public void addChild(Concept c){
        this.child.add(c);
    }
    
    public void removeChild(Concept c){
        this.child.remove(c);
    }
    
    public void clearChild(){
        this.child.removeAll(child);
    }
    
    public Concept getParent(){
        return this.parent;
    }
    
    public ArrayList<Concept> getChild(){
        return this.child;
    }
    
    public int getChildSize(){
        return this.child.size();
    }
    
    public String getValue(){
        return this.value;
    }
}
