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
    
    public void setGUIData(int level, int pos_x, int pos_y, int size_x, int size_y, int mid_x, int mid_y){
        this.GUIData.put("level",level);
        this.GUIData.put("pos_x",pos_x);
        this.GUIData.put("pos_y",pos_y);
        this.GUIData.put("size_x",size_x);
        this.GUIData.put("size_y",size_y);
        this.GUIData.put("mid_x",mid_x);
        this.GUIData.put("mid_y",mid_y);
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
