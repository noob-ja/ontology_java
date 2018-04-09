/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JA
 */
public class ConceptList {
    private Concept superConcept;
    private boolean debug = false;
    
    public void setSuper(String value){
        superConcept = new Concept(value);
        superConcept.setGUIData("level", 1);
    }
    
    public boolean setValue(String value, String newValue){
        Concept c = search(value);
        if(search(value)==null) return false;
        else{
            c.setValue(newValue);
            return true;
        }
    }
    
    public boolean addParent(String ID, String value){
        if(ID == superConcept.getValue()){
            JOptionPane.showMessageDialog(null,"Super concept cannot add parent");
            return false;
        }
        System.out.println("ssssss"+ID);
        System.out.println(value);
        Concept child = search(ID);
        Concept newParent;
        if(search(value)!=null){
            JOptionPane.showMessageDialog(null, value+" already exits");
            //newParent = search(value);
            return false;
        }else{
            newParent = new Concept(value);
            newParent.setParent(child.getParent());
            child.getParent().addChild(newParent);
        }
        newParent.addChild(child);
        child.getParent().removeChild(child);
        child.setParent(newParent);
        updateLevel();
        return true;
    }
    
    public boolean addChild(String ID, String value){
        Concept parent = search(ID);
        if(debug) System.out.println(value+" add to "+parent.getValue());
        if(search(value)!=null){
            JOptionPane.showMessageDialog(null, value+" already exits");
            return false;
        }
        Concept child = new Concept(value);
        child.setParent(parent);
        parent.addChild(child);
        updateLevel();
        return true;
    }
    
    public boolean removeSelf(String ID){
        if(ID==superConcept.getValue()) return false;
        Concept self = search(ID);
        for(Concept child : self.getChild()){
            child.setParent(self.getParent());
            self.getParent().addChild(child);
        }
        self.getParent().removeChild(self);
        self.setParent(null);
        self.clearChild();
        updateLevel();
        return true;
    }
    
    public boolean removeBranch(String ID){
        if(ID==superConcept.getValue()) return false;
        Concept self = search(ID);
        self.getParent().removeChild(self);
        self.setParent(null);
        return true;
    }
    
    public void printTreeGraphic(){
        if(this.superConcept==null) return;
        System.out.println("-------------------------");
        System.out.println(printTreeGraphic(this.superConcept,1));
        System.out.println("-------------------------");
    }
    
    public String printTreeGraphic(Concept c,int level){
        String dis = "";
        for (int i=0; i<c.getChild().size(); i++){
            dis += "\n" + addTab(level);
            dis += "|_" +printTreeGraphic(c.getChild().get(i),level+1);
        }
        return c.getValue()+dis;
    }
    
    public String addTab(int loop){
        String s = "";
        for(int i=0;i<loop;i++){
            s+="\t";
        }return s;
    }
    
    public Concept search(String id){
        if(this.superConcept==null) return null;
        return search(id,superConcept);
    }
    
    public Concept search(String id, Concept startC ){
        if (startC.getValue().equals(id)){
            return startC;
        }
        Concept res = null;
        for (int i=0; res == null && i<startC.getChildSize(); i++){
            res = search(id, startC.getChild().get(i));
        }
        return res;
    }
    
    public void updateLevel(){
        updateLevel(superConcept,1);
    }
    
    public void updateLevel(Concept c, int level){
        c.setGUIData("level", level);
        ArrayList<Concept> childConcept = c.getChild();
        for(int i=0;i<childConcept.size();i++){
            updateLevel(childConcept.get(i),level+1);
        }
    }
    
    public ArrayList<Concept> getAll(){
        if(this.superConcept==null) return new ArrayList<Concept>();
        return getAll(new ArrayList<Concept>(), this.superConcept);
    }
    
    public ArrayList<Concept> getAll(ArrayList<Concept> list, Concept c){
        list.add(c);
        for(int i=0; i<c.getChildSize();i++){
            list = getAll(list, c.getChild().get(i));
        }return list;
    }
    
}
